package click.taptap.wardrobeai.controller;

import click.taptap.wardrobeai.service.ClothingService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Nideil
 */
@RestController
@RequiredArgsConstructor
public class ClothingController {

    private final ClothingService clothingService;

    @GetMapping("/clothing/search")
    public List<Document> search(String desc) {
        return clothingService.search(desc);
    }

}
