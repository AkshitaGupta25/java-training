package Twitter;

import java.util.List;

public interface FollowDao {
    void follow(Twitter.Follow f);

    List<String> getFollowing(String email);

    List<Object[]> following();
}
