package store.User;

import model.User;

import java.util.List;

public interface UserStore {
    Integer addUser(User newUser);
    List<User> getUser(String name, String id);
    String login(String username, String password);
}
