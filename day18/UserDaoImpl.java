package day18;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    //@Autowired
    private Session session;

    UserDaoImpl(){
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    @Override
    public List<User> readAll() {
        return session.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void create(User user) {
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
    }

    @Override
    public User readById(int id) {
        Query query = session.createQuery("from User where id=:id", User.class);
        query.setParameter("id", id);
        return (User) query.getSingleResult();

    }

    @Override
    public void update(User user) {
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
    }

    @Override
    public void delete(User user) {
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }
}