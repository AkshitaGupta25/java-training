package day18;

import java.util.List;

/*
UserDAO -> USer Data access Object
Basically this interface is used to access data from the USer table

 */
public interface UserDao {
    List<User> readAll();
    void create(User user);
    User readById(int id);
    void update(User user);
    void delete(User user);
}