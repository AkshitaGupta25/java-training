package assessment.Ques4;
//CRUD -- CREATE, READ, UPDATE, DELETE
import assessment.Ques4.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductMain {
    public static void main(String[] args) {
        //insertData();
        //readData();
        //updateData();
        //deleteData();
    }

    // C
    private static void insertData() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Product p1 = new Product("product-1",-2);
        Product p2 = new Product("product-1",200);
        Product p3 = new Product("product-1",1);
        Product p4 = new Product("product-1",3);
        Product p5 = new Product("product-1",-30);
        session.persist(p1);
        session.persist(p2);
        session.persist(p3);
        session.persist(p4);
        session.persist(p5);
        transaction.commit();
    }

    // R
    private static void readData() {
        Session session = getSession();
        List<Product> productList = session.createQuery("from Product", Product.class).getResultList();
        for (Product p : productList){
                System.out.println("id: " + p.getId());
                System.out.println("name: " + p.getName());
                System.out.println("amount: " + p.getAmount());
        }
    }

    // U
    private static void updateData() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        List<Product> productList = session.createQuery("from Product", Product.class).getResultList();
        for (Product p : productList){
            if(p.getAmount() < 0){
                p.setAmount(10);

            }
        }
        transaction.commit();
        session.close();
    }

    // D
    private static void deleteData() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        List<Product> productList = session.createQuery("from Product", Product.class).getResultList();
        for (Product p : productList){
            if(p.getAmount() < 10){
                session.remove(p);
            }
        }
        transaction.commit();
    }

    private static Session getSession() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addResource("Product.hbm.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }
}