package click.taptap.wardrobeai.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class VectorStoreConfiguration {

    /**
     * 服装数据库
     * @param dataSource
     * @param embeddingModel
     * @return
     */
    @Bean(name = "clothingVectorStore")
    public PgVectorStore clothingVectorStore(DataSource dataSource, EmbeddingModel embeddingModel) {
        return PgVectorStore.builder(new JdbcTemplate(dataSource), embeddingModel).dimensions(1024).schemaName("public")
                .vectorTableName("clothing").build();
    }

    // 用户偏好 vector database
    @Bean(name = "userPreferenceVectorStore")
    public PgVectorStore userPreferenceVectorStore(DataSource dataSource, EmbeddingModel embeddingModel) {
        return PgVectorStore.builder(new JdbcTemplate(dataSource), embeddingModel).dimensions(1024).schemaName("public")
                .vectorTableName("user_preference").build();
    }

    /**
     * 穿搭模板 vector database
     * content: 穿搭模板描述
     */
    @Bean(name = "outfitTemplateVectorStore")
    public PgVectorStore outfitTemplateVectorStore(DataSource dataSource, EmbeddingModel embeddingModel) {
        return PgVectorStore.builder(new JdbcTemplate(dataSource), embeddingModel).dimensions(1024).schemaName("public")
                .vectorTableName("outfit_template").build();
    }

}
