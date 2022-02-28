package assessment.Ques9;

import day9.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Cat.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        //insertData(session);
        CriteriaQuery<Cat> criteriaQuery = query(session);
        List<Cat> list = session.createQuery(criteriaQuery).getResultList();
        for (Cat c : list) {
            System.out.print(c.getName() + " ");
            System.out.print(c.getAge() + " ");
            System.out.println(c.getWeight());
        }
        session.close();
    }

    public static void insertData(Session session) {
        Transaction transaction = session.beginTransaction();
        Cat cat = new Cat("Kitty",4.5f,3);
        session.persist(cat);
        transaction.commit();
    }

    public static CriteriaQuery<assessment.Ques9.Cat> query(Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<assessment.Ques9.Cat> criteriaQuery = criteriaBuilder.createQuery(assessment.Ques9.Cat.class);
        Root<assessment.Ques9.Cat> root = criteriaQuery.from(assessment.Ques9.Cat.class);
        Predicate predicate1 = criteriaBuilder.like(root.get("name"), "m%");
        Predicate predicate2 = criteriaBuilder.gt(root.get("weight"), 2);
        criteriaQuery.select(root).where(criteriaBuilder.and(predicate1, predicate2));
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("age")));
        return criteriaQuery;
    }
}
