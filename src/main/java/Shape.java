import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@MappedSuperclass
abstract class Shape {
    @Embedded
    private Color color;

    void print(String name){
        System.out.println(name);
    }

    public Shape(Color color){
        String className = this.getClass().getSimpleName();
        this.color = color;

        print(className);
    }
    abstract double getArea();
    abstract double getPerimeter();
    protected Shape(){}
    public void getColorDescription(){
        System.out.println("Red: " + color.red() + " Green: " + color.green() + " Blue: " + color.blue() + " Alpha: " + color.alpha());
    }
}
@Entity
@Table(name = "triangles")
class Triangle extends Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double a, b, c;
    Triangle(double a, double b, double c, Color color){
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }
    protected Triangle(){}
    @Override
    double getPerimeter() {
        return a+b+c;
    }
    @Override
    double getArea() {
        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p-a) * (p-b) * (p-c));
    }
}
@Entity
@Table(name = "rectangles")
class Rectangle extends Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double a;
    private double b;
    Rectangle(double a, double b, Color color){
        super(color);
        this.a = a;
        this.b = b;
    }
    protected Rectangle(){}

    @Override
    double getPerimeter() {
        return 2*(a+b);
    }
    @Override
    double getArea() {
        return a*b;
    }

    public void setA(int i) {
        this.a = i;
    }

    public Long getId() {
        return id;
    }
}

class ShapeDecriber {
    private static final Logger log = LoggerFactory.getLogger(ShapeDecriber.class);

    public static void describe(Shape shape) {
        String area = String.valueOf(shape.getArea());
        String perimeter = String.valueOf(shape.getPerimeter());
        //log.info("Przedstawiona figura to: {} o kolorze {}", String.valueOf(shape.getClass().getSimpleName()), shape.color.toString());
        log.info("Pole figury wynosi {},\n a obwod {}", area, perimeter);
        System.out.println("Pole: " + area);
        System.out.println("Obwod: " + perimeter);
    }
}