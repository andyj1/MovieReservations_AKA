package store.User;

import model.User;

public interface UserStore {
    Integer addUser(User newUser);

    boolean authenticate(String username, String password);

    User getUser(String user_name);
    User getUserProfile(String user_name);

    String generateToken(String username, String password);

    void setPassword(String username, String oldPassword, String newPassword);

    User updateUser(String user_id, String name, String username, String password, String email);

}
