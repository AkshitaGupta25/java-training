package Twitter;

import java.util.List;

public interface TweetDao {
    List<Object[]> readAll();
    void create(Twitter.Tweet tweet);
    List<Twitter.Tweet> readByEmail(String email);
}
