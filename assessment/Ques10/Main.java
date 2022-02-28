package assessment.Ques10;

import Utils.Utility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Store.class);
        configuration.addAnnotatedClass(Product.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        /*Transaction transaction = session.beginTransaction();
        Store store = new Store();
        store.setName("name-2");
        store.setAddress("address-2");

        Product p = new Product();
        p.setName("Product-2");

        p.setStore(store);

        session.persist(store);
        session.persist(p);
        transaction.commit();
        insertNewProduct(session);*/
        deleteStore(session);
        session.close();
    }

    private static void insertNewProduct(Session session) {
        Transaction transaction = session.beginTransaction();
        Store s = session.get(Store.class, 1);
        Product p = new Product();
        p.setName("Product-3");
        p.setStore(s);
        session.persist(s);
        session.persist(p);
        transaction.commit();
        session.close();
    }

    private static void deleteStore(Session session) {
        Transaction transaction = session.beginTransaction();
        Store s=session.get(Store.class,2);
        session.remove(s);
        transaction.commit();
        session.close();
    }
}

