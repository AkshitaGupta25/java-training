package Twitter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component
public class FollowDaoImpl implements Twitter.FollowDao {

    private Session session;
    FollowDaoImpl(){
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Twitter.Follow.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }
    @Override
    public void follow(Twitter.Follow f) {
        session.beginTransaction();
        session.persist(f);
        session.getTransaction().commit();
    }

    @Override
    public List<String> getFollowing(String email) {
        String hql = "from Follow where email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        return query.getResultList();

    }

    @Override
    public List<Object[]> following() {
        List<Object[]> list = session.createQuery("select email,following from Follow ",Object[].class ).getResultList();
        return list;
    }
}
