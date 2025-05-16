package click.taptap.wardrobeai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nideil
 */
@Configuration
@EnableConfigurationProperties({ModelClientAutoConfiguration.EmbeddingProperties.class, ModelClientAutoConfiguration.ChatProperties.class})
public class ModelClientAutoConfiguration {
    @ConfigurationProperties(prefix = "wardrobeai.embedding")
    public record EmbeddingProperties(String model, String url) {}
    @ConfigurationProperties(prefix = "wardrobeai.chat")
    public record ChatProperties(String model, String url) {}


    @Bean
    public ChatClient chatClient(ChatModel chatModel) {
        ChatClient chatClient = ChatClient.builder(chatModel).build();
        return null;
    }
}
