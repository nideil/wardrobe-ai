package click.taptap.wardrobeai;

import click.taptap.wardrobeai.vo.ClothingItem;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
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


            // List<Document> rocket = userPreferenceVectorStore.similaritySearch(
            //         SearchRequest.builder().query("rocket").filterExpression("meta1 == 'meta1'").topK(3).build());
            //
            // rocket.forEach(item -> System.out.println("rag context: " + item.getFormattedContent()));

            context.close();
        };
    }
}
