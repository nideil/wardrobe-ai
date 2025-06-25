package click.taptap.wardrobeai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nideil
 */
@Service
@RequiredArgsConstructor
public class ClothingService {
    private final VectorStore clothingVectorStore;

    /**
     * Search for similar clothing by description text
     * @param description description text
     */
    public List<Document> search(String description) {
        SearchRequest searchRequest = SearchRequest.builder().query(description).topK(3).build();
        return clothingVectorStore.similaritySearch(searchRequest);
    }

    /**
     * Import clothing data
     */
    public void importClothing() {
        throw new UnsupportedOperationException();
    }
}
