package click.taptap.wardrobeai.config;

import click.taptap.wardrobeai.vo.ClothingItem;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * 生成图片将描述添加到向量数据库
 * todo 直接将图片embedding添加到向量数据库
 *
 * @author Nideil
 */
// @Component
@RequiredArgsConstructor
public class ImageImportRunner implements ApplicationRunner {
    private final VectorStore clothingVectorStore;
    private final ChatClient chatClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:data/*");

        String template = """
            You will receive an image as input. Please focus on all **garments** shown in the image (including but not limited to tops, outerwear, pants, shoes, hats, and accessories), and extract the following attributes for each item of clothing:
              - category: the broad type of garment (e.g. “top,” “outerwear,” “bottom,” “footwear,” “accessory”)
              - subcategory: the specific style (e.g. “T-shirt,” “denim jacket,” “jeans,” “sneakers,” “baseball cap,” “scarf”)
              - season: the appropriate season(s) for wearing the item (e.g. “spring,” “summer,” “autumn,” “winter,” or combinations like “spring/summer”)
              - colors: a list of visible colors (e.g. [“light blue”, “white”, “gray”])
            Please output the results according to the following JSON template. All clothing-related descriptions should be mapped into the top-level `description` field.
            """;

        for (Resource resource : resources) {
            UserMessage userMessage =
                UserMessage.builder().text(template).media(new Media(MimeTypeUtils.IMAGE_PNG, resource)).build();
            ClothingItem entity = chatClient.prompt(new Prompt(userMessage)).call().entity(ClothingItem.class);
            if (entity == null) {
                continue;
            }
            // 将图片转换为Base64字符串并添加到描述中
            byte[] imageBytes = resource.getInputStream().readAllBytes();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            entity.setBase64Image(base64Image);
            entity.setFileName(resource.getFilename());

            Document document = new Document(entity.getDescription(),
                Map.ofEntries(Map.entry("category", entity.getCategory()),
                    Map.entry("subcategory", entity.getSubcategory()),
                    Map.entry("season", entity.getSeason()),
                    Map.entry("colors", entity.getColors()),
                    Map.entry("description", entity.getDescription()),
                    Map.entry("base64Image", entity.getBase64Image()),
                    Map.entry("fileName", entity.getFileName())));

            // 先删除向量数据库中相同文件名的向量
            clothingVectorStore.delete("fileName == '" + entity.getFileName() + "'");

            // 保存到clothingVectorStore
            clothingVectorStore.add(List.of(document));
        }
    }
}
