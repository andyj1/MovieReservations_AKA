import model.User;
import model.UserBuilder;
import org.apache.log4j.BasicConfigurator;
import org.bson.Document;
import org.eclipse.jetty.http.HttpStatus;
import store.User.UserStore;
import store.User.UserStoreController;
import utils.tokenizer;

import java.util.Date;

import static spark.Spark.*;

public class Application {

    private static UserStore us = new UserStoreController();
    public static void main(String[] args) {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        //staticFiles.location("/public");
        port(1010);

        //System.err.println("TEST");
        BasicConfigurator.configure();

        get("/start", (req, res) -> "Hello, World!");
        // parameter from request -- username --> returns corresponding user

        get("/signup", "application/json", (req, res) -> {
            final String username = req.queryParams("username");
            final String password = req.queryParams("password");
            final String name = req.queryParams("name");
            final String email = req.queryParams("email");
            final String genre = req.queryParams("genre");
            final String zipcode = req.queryParams("zip_code");
            final boolean admin = Boolean.parseBoolean(req.queryParams("admin"));

            User user = new UserBuilder()
                    .user_id("1")
                    .name(name)
                    .username(username)
                    .password(password)
                    .email(email)
                    .admin(admin)
                    .created_at(new Date())
                    .favorite_genre(genre)
                    .zip_code(zipcode)
                    .build();

            us.addUser(user);

            return HttpStatus.OK_200;
        });

        get("/login", "application/json", (req, res) -> {
            final String username = req.queryParams("username");
            final String password = req.queryParams("password");
            String token = us.login(username, password);
            return token;
        });
    }

}
