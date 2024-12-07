import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ShapeDAO {
    public <T extends Shape> void save(T shape) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(shape);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public <T extends Shape> T findById(Class<T> clazz, Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(clazz, id);
        }
    }

    public <T extends Shape> List<T> findAll(Class<T> clazz) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM " + clazz.getSimpleName();
            return session.createQuery(hql, clazz).list();
        }
    }

    public <T extends Shape> void delete(T shape) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(shape);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
