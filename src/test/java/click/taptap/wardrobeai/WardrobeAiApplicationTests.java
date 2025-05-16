package click.taptap.wardrobeai;

import click.taptap.wardrobeai.service.ClothingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WardrobeAiApplicationTests {

    @Autowired
    private ClothingService clothingService;

    @Test
    void contextLoads() {
    }

    @Test
    void testSearch() {
        String description = "t恤";
        var result = clothingService.search(description);
        System.out.println(result);
    }

}
