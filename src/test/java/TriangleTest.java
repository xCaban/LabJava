import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void testGetArea() {
        Color color = new Color(0, 0, 255, 1);
        Triangle triangle = new Triangle(3.0, 4.0, 5.0, color);
        assertEquals(6.0, triangle.getArea());
    }

    @Test
    void testGetPerimeter() {
        Color color = new Color(0, 0, 255, 1);
        Triangle triangle = new Triangle(3.0, 4.0, 5.0, color);
        assertEquals(12.0, triangle.getPerimeter());
    }
}
