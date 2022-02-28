package assessment.Ques8;
import Utils.Utility;
import day9.Tweet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        insertMessages();
        maxMessages();
    }
    private static void insertMessages() {
        Session session = Utility.getAnnotatedSession(assessment.Ques8.User.class);
        String[] userId = new String[]{"userID-1","userID-2","userID-3","userID-4"};
        String[] Messages = new String[]{"Msg-1","Msg-2","Msg-3","Msg-4"};
        Transaction transaction = session.beginTransaction();
        for(int i=0;i<100;i++){
            User user = new User();
            user.setUserId(userId[(int)(Math.random()*4)]);
            user.setMessage(Messages[(int)(Math.random()*4)]);
            session.persist(user);
        }
        transaction.commit();
        session.close();
    }
    private static void maxMessages(){
        Session session = Utility.getAnnotatedSession(assessment.Ques8.User.class);
        List<Object[]> object=session.createQuery("select userId, count(message) from User group by userId order by userId",Object[].class).getResultList();
        Long max = 0l;
        Object userId = "";
        for (int i = 0 ; i < object.size() ; i++){
            Object[] arr = object.get(i);
            Long messages = (Long) arr[1];
            if(max < messages) {
                max = messages;
                userId = arr[0];
            }
        }
        System.out.println(max);
        System.out.println(userId);
    }
}
