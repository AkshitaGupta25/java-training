package Twitter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component
public class UserDaoImpl implements Twitter.UserDao {
    private Session session;

    UserDaoImpl(){
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Twitter.User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    @Override
    public List<Twitter.User> readAll() {
        return session.createQuery("from User", Twitter.User.class).getResultList();
    }

    @Override
    public void create(Twitter.User user) {
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
    }

    @Override
    public Twitter.User readById(int id) {
        Query query = session.createQuery("from User where id=:id", Twitter.User.class);
        query.setParameter("id", id);
        return (Twitter.User) query.getSingleResult();

    }

    @Override
    public void update(Twitter.User user) {
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Twitter.User user) {
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }
}