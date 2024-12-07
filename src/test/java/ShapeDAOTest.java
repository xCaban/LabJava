import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ShapeDAOTest {
    private static SessionFactory sessionFactory;
    private ShapeDAO shapeDAO;

    @BeforeAll
    static void setup() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @BeforeEach
    void init() {
        shapeDAO = new ShapeDAO();
    }

    @Test
    void testSaveAndRetrieveRectangle() {
        Rectangle rectangle = new Rectangle(4, 5, new Color(255, 0, 0));
        shapeDAO.save(rectangle);

        List<Rectangle> rectangles = shapeDAO.findAll(Rectangle.class);
        assertEquals(2, rectangles.size(), "Rectangle should be saved.");
        Rectangle retrieved = rectangles.get(0);
        assertEquals(50, retrieved.getArea(), "Area should match.");
    }

    @Test
    void testUpdateRectangle() {
        Rectangle rectangle = new Rectangle(4, 5, new Color(255, 0, 0));
        shapeDAO.save(rectangle);

        List<Rectangle> rectangles = shapeDAO.findAll(Rectangle.class);
        Rectangle retrieved = rectangles.get(0);

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            retrieved = session.get(Rectangle.class, retrieved.getId());
            retrieved.setA(10);
            session.update(retrieved);
            session.getTransaction().commit();
        }

        Rectangle updated = shapeDAO.findById(Rectangle.class, retrieved.getId());
        assertEquals(50, updated.getArea(), "Updated rectangle should have correct area.");
    }

    @Test
    void testDeleteRectangle() {
        Rectangle rectangle = new Rectangle(4, 5, new Color(255, 0, 0));
        shapeDAO.save(rectangle);

        List<Rectangle> rectangles = shapeDAO.findAll(Rectangle.class);
        assertEquals(3, rectangles.size(), "Rectangle should be saved.");

        shapeDAO.delete(rectangle);
        rectangles = shapeDAO.findAll(Rectangle.class);
        assertEquals(2, rectangles.size(), "Rectangle should be deleted.");
    }
    @Test
    void createTriangleTable(){
        Triangle triangle = new Triangle(3,4,5, new Color(222,111,212));
        shapeDAO.save(triangle);
        List<Triangle> triangles = shapeDAO.findAll(Triangle.class);
        assertEquals(1, triangles.size(), "Triangle should be saved.");
        Triangle retrieved = triangles.get(0);
        assertEquals(6, retrieved.getArea(), "Area should match.");
    }
    @Test
    void testTableCreation() {
        try (Session session = sessionFactory.openSession()) {
            // Sprawdzenie, czy tabela została utworzona
            List<Object[]> results = session.createNativeQuery(
                    "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_name IN ('RECTANGLES', 'TRIANGLES')"
            ).list();
            assertEquals(2, results.size(), "Both tables (rectangles, triangles) should exist.");
        }
    }

    @AfterAll
    static void tearDown() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE rectangles").executeUpdate();
            session.createNativeQuery("TRUNCATE TABLE triangles").executeUpdate();
            session.getTransaction().commit();
        }
        HibernateUtil.shutdown();
    }

}
