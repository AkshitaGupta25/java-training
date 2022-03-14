package SpringAssignment.Ques6;

import java.util.List;

public interface TweetDao {
    List<Object[]> readAll();
    void create(Tweet tweet);
    List<Tweet> readByEmail(String email);


}
