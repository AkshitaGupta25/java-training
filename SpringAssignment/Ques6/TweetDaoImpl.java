package SpringAssignment.Ques6;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component
public class TweetDaoImpl implements TweetDao {
    private Session session;
    TweetDaoImpl(){
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Tweet.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }
    @Override
    public List<Object[]> readAll() {
        List<Object[]> list = session.createQuery("select name,email,tweet,localDateTime from Tweet ",Object[].class ).getResultList();
        return list;
    }

    @Override
    public List<Tweet> readByEmail(String email) {
        String hql = "from Tweet where email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        return query.getResultList();
    }

    @Override
    public void create(Tweet tweet) {
        Transaction transaction = session.beginTransaction();
        session.persist(tweet);
        transaction.commit();
    }
}
