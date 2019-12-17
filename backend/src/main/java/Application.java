import model.*;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.http.HttpStatus;
import store.Food.FoodStore;
import store.Food.FoodStoreController;
import store.FoodReservation.FoodReservationStore;
import store.FoodReservation.FoodReservationStoreController;
import store.Movie.MovieStore;
import store.Movie.MovieStoreController;
import store.Seating.SeatingStore;
import store.Seating.SeatingStoreController;
import store.Showtime.ShowtimeStore;
import store.Showtime.ShowtimeStoreController;
import store.Theater.TheaterStore;
import store.Theater.TheaterStoreController;
import store.User.UserStore;
import store.User.UserStoreController;
import utils.tokenizer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static spark.Spark.*;
import static utils.JsonUtil.json;

public class Application {

    private static UserStore us = new UserStoreController();
    private static TheaterStore ts = new TheaterStoreController();
    private static ShowtimeStore ss = new ShowtimeStoreController();
    private static SeatingStore seatings = new SeatingStoreController();
    private static FoodReservationStore frs = new FoodReservationStoreController();
    private static FoodStore fs = new FoodStoreController();
    private static MovieStore ms = new MovieStoreController();
    private static tokenizer tk = new tokenizer();

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

        get("/signup", "application/json", (req, res) -> {
            res.type("application/json");
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

            return HttpStatus.OK_200; // directly deliver status code since not using handler for setting response status
        }, json());

        // if token is present in the header, use it to
        get("/login", "application/json", (req, res) -> {
            res.type("application/json");
            String token = req.headers("token");
            final String username = req.queryParams("username");
            final String password = req.queryParams("password");
            if (token != null && tk.verifyToken(token).equals(username)) {
                return HttpStatus.OK_200;
            } else {
                // generateToken generate a string token after authenticating username and password
                token = us.generateToken(username, password);
            }
            return token;
        }, json());

        get("/theaters", "application/json", (req, res) -> {
            res.type("application/json");
            List<Theater> theaters = ts.getTheaters();
            return theaters;
        }, json());

        get("/movies", "application/json", (req, res) -> {
            res.type("application/json");
            List<String> movies = ss.getPopMovies();
            return movies;
        }, json());

        get("/all_showtimes", "application/json", (req, res) -> {
            res.type("application/json");
            final String theater = req.queryParams("theater_name");
            final String date = req.queryParams("date");
            List<Showtime> showtimes = ss.getAllShowtimes(theater, date);
            return showtimes;
        }, json());

        get("/movie_showtimes", "application/json", (req, res) -> {
            res.type("application/json");
            final String movie = req.queryParams("movie_name");
            final String date = req.queryParams("date");
            List<Showtime> showtimes = ss.getShowtimesByMovie(movie, date);
            return showtimes;
        }, json());

        get("/showtimes", "application/json", (req, res) -> {
            res.type("application/json");
            final String theater = req.queryParams("theater_name");
            final String date = req.queryParams("date");
            final String movie = req.queryParams("movie_name");
            List<Showtime> showtimes = ss.getShowtimes(theater, date, movie);
            return showtimes;
        }, json());

        get("/seats", "application/json", (req, res) -> {
            res.type("application/json");
            final String theater = req.queryParams("theater_name");
            final String date = req.queryParams("date");
            final String movie = req.queryParams("movie_name");
            final String time = req.queryParams("time");
            final String seats = req.queryParams("seats");
            final String username = req.queryParams("username");
            String[] val = seats.split(",");
            List<Integer> seat = new ArrayList<>();
            for(int i = 0; i < val.length; i++){
                seat.add(Integer.parseInt(val[i]));
            }
            if(seat.size() == 0){
                return "No seats specified";
            }
            String id = ss.getShowtimeId(time, movie, theater, date);
            if(id != null){
                Seating seating = seatings.createSeating(id, seat, username);
                return seating;
            } else{
                return "Invalid Showtime Selection";
            }
        }, json());

        get("/get_seats", "application/json", (req, res) -> {
            res.type("application/json");
            final String username = req.queryParams("username");
            List<Seating> seating = seatings.getSeating(username);
            return seating;
        }, json());

        get("/get_food", "application/json", (req, res) -> {
            res.type("application/json");
            final String username = req.queryParams("username");
            List<FoodReservation> foodReservations = frs.getFoodReservations(username);
            return foodReservations;
        }, json());

        get("/movie_info", "application/json", (req, res) -> {
            res.type("application/json");
            final String movie_name = req.queryParams("movie_name");
            Movie movie = ms.getMovieInfo(movie_name);
            if(movie == null){
                return "Movie Info Not Available";
            } else{
                return movie;
            }
        }, json());

        get("/food", "application/json", (req, res) -> {
            res.type("application/json");
            final String theater = req.queryParams("theater_name");
            final String date = req.queryParams("date");
            final String movie = req.queryParams("movie_name");
            final String time = req.queryParams("time");
            final String food = req.queryParams("food");
            final Integer quantity = Integer.parseInt(req.queryParams("quantity"));
            final String username = req.queryParams("username");
            String id = ss.getShowtimeId(time, movie, theater, date);
            if(id != null){
                FoodReservation foodReservation = frs.createFoodReservation(id, quantity, username, food);
                return foodReservation;
            } else{
                return "Invalid Showtime Selection";
            }
        }, json());

        get("/user", "application/json", (req, res) -> {
            res.type("application/json");
            final String username = req.queryParams("username");
            User user = us.getUserProfile(username);
            return user;
        }, json());

        get("/add_food", "application/json", (req, res) -> {
            res.type("application/json");
            final String username = req.queryParams("username");
            final String foodId = req.queryParams("food_id");
            final String foodDesc = req.queryParams("food_desc");
            User user = us.getUserProfile(username);
            if(user.admin()){
                Food food = fs.addFood(foodId, foodDesc);
                return food;
            } else{
                return "User is not an Admin";
            }
        }, json());

        get("/add_showtime", "application/json", (req, res) -> {
            res.type("application/json");
            final String username = req.queryParams("username");
            final String movie_name = req.queryParams("movie_name");
            final String theater_name = req.queryParams("theater_name");
            final String date = req.queryParams("date");
            final String time = req.queryParams("time");
            final String type = req.queryParams("type");
            User user = us.getUserProfile(username);
            if(user.admin()){
                Showtime showtime = ss.addShowtime(movie_name,theater_name, date, time, type);
                if(showtime == null){
                    return "Unable to add showtime";
                }
                return showtime;
            } else{
                return "User is not an Admin";
            }
        }, json());

        get("/showtime_info", "application/json", (req, res) -> {
            res.type("application/json");
            final String showtime_id = req.queryParams("showtime_id");
            Showtime showtime = ss.getShowtimeById(showtime_id);
            if(showtime == null){
                return "Movie Info Not Available";
            } else{
                return showtime;
            }
        }, json());

        get("/food_info", "application/json", (req, res) -> {
            res.type("application/json");
            List<Food> food = fs.getAllFood();
            return food;
        }, json());
    }

}
