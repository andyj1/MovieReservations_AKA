import model.User;
import org.apache.log4j.BasicConfigurator;
import store.User.UserStore;
import store.User.UserStoreController;

import static spark.Spark.*;

public class Application {

    private static UserStore us = new UserStoreController();
    public static void main(String[] args) {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFiles.location("/public");
        port(1010);

        BasicConfigurator.configure();

        // example route endpoints
        get("/start", (req, res) -> "Hello, World!");
        // parameter from request -- username --> returns corresponding user
        get("/users/:username", (req, res) -> {
            String username = req.params(":username");
            User user = us.getUser(username);
            return user;
        });
    }

}
