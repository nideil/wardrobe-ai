package click.taptap.wardrobeai.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Nideil
 */
@Data
public class ClothingItem {

    // 基础属性
    private String category;              // 类别，例如：上衣、裤子
    private String subcategory;           // 子类别，例如：T恤、牛仔裤
    private String gender;                // 性别：男、女、中性
    private String season;                // 适用季节：春、夏、秋、冬、四季
    private String occasion;             // 场合：休闲、职场等

    // 视觉属性
    private List<String> colors;          // 颜色（主色和辅色）
    private String pattern;               // 图案：纯色、条纹等
    private String material;              // 材质：棉、麻等
    private String style;                 // 风格：街头、简约等
    private String fit;                   // 版型：修身、宽松等
    private List<String> designDetails;   // 细节设计：V领、拉链等

    // 功能属性
    private boolean isSet;                // 是否为套装
    private List<String> suggestedPairing;// 推荐搭配的品类
    private String brand;                 // 品牌（可选）
    private String trendiness;            // 流行度：经典、新潮等

    // 技术属性
    private float[] imageEmbedding;       // 图像向量（用于相似检索）
    private List<String> tags;            // 标签关键词
    private String description;           // LLM生成的描述文本
}
