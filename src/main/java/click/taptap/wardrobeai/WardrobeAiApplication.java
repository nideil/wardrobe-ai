package click.taptap.wardrobeai;

import click.taptap.wardrobeai.vo.ClothingItem;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.Map;

@SpringBootApplication
public class WardrobeAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WardrobeAiApplication.class, args);
    }

    @Bean
    public CommandLineRunner predefinedQuestions(VectorStore userPreferenceVectorStore, ChatClient chatClient,
            ConfigurableApplicationContext context) {

        return args -> {

            BeanOutputConverter<ClothingItem> outputConverter =
                    new BeanOutputConverter<>(ClothingItem.class);
            String format = outputConverter.getFormat();

            String template = """
                    Generate the attributes of the clothes in the picture.
                    {format}
                    """;


            // 加载本地图片
            File file = new File("d://Desktop//man_clothing.jpg");
            FileSystemResource resource = new FileSystemResource(file);
            System.out.println("resource exists:" + resource.exists());
            PromptTemplate prompt =
                    PromptTemplate.builder().template(template).resource(resource).variables(Map.of("format", format)).build();
            // UserMessage userMessage =
            //         UserMessage.builder().text(template).media(new Media(MimeTypeUtils.IMAGE_PNG, resource)).build();
            ClothingItem entity = chatClient.prompt(prompt.create()).call().entity(ClothingItem.class);
            System.out.println(entity.toString());

            // List<Document> rocket = userPreferenceVectorStore.similaritySearch(
            //         SearchRequest.builder().query("rocket").filterExpression("meta1 == 'meta1'").topK(3).build());
            //
            // rocket.forEach(item -> System.out.println("rag context: " + item.getFormattedContent()));

            context.close();
        };
    }
}
