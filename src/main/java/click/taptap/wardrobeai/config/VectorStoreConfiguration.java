package click.taptap.wardrobeai.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class VectorStoreConfiguration {

    // 用户偏好 vector database
    @Bean(name = "userPreferenceVectorStore")
    public PgVectorStore userPreferenceVectorStore(DataSource dataSource, EmbeddingModel embeddingModel) {
        return PgVectorStore.builder(new JdbcTemplate(dataSource), embeddingModel).dimensions(1024).schemaName("public")
                .vectorTableName("user_preference").build();
    }

    /**
     * 穿搭模板 vector database
     * content: 穿搭模板描述
     * metadata:
     *      gender: male/female
     *      style: casual/sporty/office/formal
     *      season: spring/summer/autumn/winter
     *      occasion: daily/work/party
     *      color: red/blue/green/yellow/orange/purple/pink/brown/black/white
     *      pattern: solid/striped/checked/printed
     *      fabric: cotton/wool/silk/denim/leather
     *      fit: slim/regular/loose
     *      length: short/medium/long
     *      accessory: hat/belt/scarf/jewelry
     *      footwear: sneakers/boots/sandals/loafers
     *      jewelry: necklace/earrings/bracelet/ring
     *      outerwear: jacket/coat/blazer/cardigan
     *      bottom: pants/jeans/shorts/skirts/dresses
     */
    @Bean(name = "outfitTemplateVectorStore")
    public PgVectorStore outfitTemplateVectorStore(DataSource dataSource, EmbeddingModel embeddingModel) {
        return PgVectorStore.builder(new JdbcTemplate(dataSource), embeddingModel).dimensions(1024).schemaName("public")
                .vectorTableName("outfit_template").build();
    }

}
