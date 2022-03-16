package Twitter;

import java.util.List;

/*
UserDAO -> USer Data access Object
Basically this interface is used to access data from the User table


 */
public interface UserDao {
    List<Twitter.User> readAll();
    void create(Twitter.User user);
    Twitter.User readById(int id);
    void update(Twitter.User user);
    void delete(Twitter.User user);
}