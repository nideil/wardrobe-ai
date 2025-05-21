package click.taptap.wardrobeai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nideil
 */
@Configuration
// @EnableConfigurationProperties({ModelClientConfiguration.EmbeddingProperties.class, ModelClientConfiguration.ChatProperties.class})
public class ModelClientConfiguration {
    // @ConfigurationProperties(prefix = "wardrobeai.embedding")
    // public record EmbeddingProperties(String model, String url) {}
    // @ConfigurationProperties(prefix = "wardrobeai.chat")
    // public record ChatProperties(String model, String url) {}


    @Bean
    public ChatClient chatClient(ChatModel chatModel, ChatMemory chatMemory) {
        return ChatClient.builder(chatModel)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
}
