package click.taptap.wardrobeai.controller;

import click.taptap.wardrobeai.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nideil
 */
@RestController
public class ClothingController {

    @Autowired
    private ClothingService clothingService;

    @GetMapping("/clothing/search")
    public String search(String desc) {
        clothingService.search(desc);
        return "ok";
    }

}
