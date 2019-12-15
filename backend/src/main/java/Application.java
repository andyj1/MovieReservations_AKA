import com.google.gson.JsonObject;
import model.Showtime;
import model.Theater;
import model.User;
import model.UserBuilder;
import org.apache.log4j.BasicConfigurator;
import org.bson.Document;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONObject;
import store.Showtime.ShowtimeStore;
import store.Showtime.ShowtimeStoreController;
import store.Theater.TheaterStore;
import store.Theater.TheaterStoreController;
import store.User.UserStore;
import store.User.UserStoreController;
import utils.tokenizer;

import java.util.Date;
import java.util.List;

import static spark.Spark.*;
import static utils.JsonUtil.json;

public class Application {

    private static UserStore us = new UserStoreController();
    private static TheaterStore ts = new TheaterStoreController();
    private static ShowtimeStore ss = new ShowtimeStoreController();
    public static void main(String[] args) {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        //staticFiles.location("/public");
        port(1010);

        //System.err.println("TEST");
        BasicConfigurator.configure();

        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

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
            res.type("application/json");
            final String username = req.queryParams("username");
            final String password = req.queryParams("password");
            String token = us.login(username, password);
            return token;
        }, json());

        get("/theaters", "application/json", (req, res) -> {
            res.type("application/json");
            List<Theater> theaters = ts.getTheaters();
            return theaters;
        }, json());

        get("/movies", "application/json", (req, res) -> {
            List<String> movies = ss.getPopMovies();
            return movies;
        });

        get("/all_showtimes", "application/json", (req, res) -> {
            final String theater = req.queryParams("theater_name");
            final String date = req.queryParams("date");
            List<Showtime> showtimes = ss.getAllShowtimes(theater, date);
            return showtimes;
        });

        get("/showtimes", "application/json", (req, res) -> {
            final String theater = req.queryParams("theater_name");
            final String date = req.queryParams("date");
            final String movie = req.queryParams("movie_name");
            List<Showtime> showtimes = ss.getShowtimes(theater, date, movie);
            return showtimes;
        });

    }

}
