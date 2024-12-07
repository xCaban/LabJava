import org.junit.jupiter.api.Test;
import java.util.List;

class ShapeDescriberTest {

    @Test
    void testRenderShapes() {
        Color redColor = new Color(255, 0, 0, 1);
        Color blueColor = new Color(0, 0, 255, 1);

        Shape rectangle = new Rectangle(2.0, 3.0, redColor);
        Shape triangle = new Triangle(3.0, 4.0, 5.0, blueColor);

        List<Shape> shapes = List.of(rectangle, triangle);

        for (Shape shape : shapes) {
            ShapeDecriber.describe(shape);
        }
    }
}
