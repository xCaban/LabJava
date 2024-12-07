import org.slf4j.*;
import jakarta.persistence.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*ShapeDecriber shapeDecriber = new ShapeDecriber();
        //
        Color rzulty = new Color(2,1,3,7);
        Rectangle rectangle = new Rectangle(2.1,3.7, rzulty);
        //
        Color blue = new Color(0,0,255);
        Triangle triangle = new Triangle(3,4,5, blue);
        //
        shapeDecriber.describe(rectangle);
        shapeDecriber.describe(triangle);*/
        ShapeDAO shapeDAO = new ShapeDAO();

        Color red = new Color(255, 0, 0);
        Rectangle rectangle = new Rectangle(4, 5, red);
        shapeDAO.save(rectangle);

        Triangle triangle = new Triangle(3, 4, 5, new Color(0, 255, 0));
        shapeDAO.save(triangle);

        List<Rectangle> rectangles = shapeDAO.findAll(Rectangle.class);
        rectangles.forEach(r -> System.out.println("Rectangle area: " + r.getArea()));

        HibernateUtil.shutdown();

    }
}