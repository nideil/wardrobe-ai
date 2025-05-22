package click.taptap.wardrobeai.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nideil
 */
@Service
public class ClothingService {
    @Autowired
    private VectorStore clothingVectorStore;

    /**
     * Search for similar clothing by description text
     * @param description description text
     */
    public void search(String description) {
        SearchRequest searchRequest = SearchRequest.builder().query(description).topK(3).build();
        List<Document> documents = clothingVectorStore.similaritySearch(searchRequest);

    }

    /**
     * Import clothing data
     */
    public void importClothing() {
        throw new UnsupportedOperationException();
    }
}
