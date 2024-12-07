import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void testGetArea() {
        Color color = new Color(255, 0, 0, 1);
        Rectangle rectangle = new Rectangle(2.0, 3.0, color);
        assertEquals(6.0, rectangle.getArea());
    }

    @Test
    void testGetPerimeter() {
        Color color = new Color(255, 0, 0, 1);
        Rectangle rectangle = new Rectangle(2.0, 3.0, color);
        assertEquals(10.0, rectangle.getPerimeter());
    }
}
