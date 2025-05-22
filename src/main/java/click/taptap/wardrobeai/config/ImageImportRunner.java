package click.taptap.wardrobeai.config;

import click.taptap.wardrobeai.vo.ClothingItem;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.util.Map;

/**
 * image import to vector database
 * @author Nideil
 */
@Component
public class ImageImportRunner implements ApplicationRunner {
    @Autowired
    private VectorStore vectorStore;
    @Autowired
    private ChatClient chatClient;
    @Autowired
    private EmbeddingModel embeddingModel;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 注意路径格式，classpath*:表示搜索所有类路径
        Resource[] resources = resolver.getResources("classpath:data/*");

        String template = """
                    Generate the attributes of the clothes in the picture.
                    """;

        for (int i = 0; i < resources.length; i++) {
            Resource resource = resources[i];

            UserMessage userMessage =
                    UserMessage.builder().text(template).media(new Media(MimeTypeUtils.IMAGE_PNG, resource)).build();
            ClothingItem entity = chatClient.prompt(new Prompt(userMessage)).call().entity(ClothingItem.class);

        }

    }
}
