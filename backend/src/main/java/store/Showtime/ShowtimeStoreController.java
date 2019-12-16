package store.Showtime;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import model.Showtime;
import model.ShowtimeBuilder;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import java.util.*;

public class ShowtimeStoreController implements ShowtimeStore{

    Config config = ConfigFactory.load("AKA.conf");
    MongoClient dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
    MongoDatabase database = dbClient.getDatabase(config.getString("mongo.database"));
    MongoCollection<Document> showtimeCollection = database.getCollection(config.getString("mongo.collection_showtime"));
    MongoCollection<Document> theaterCollection = database.getCollection(config.getString("mongo.collection_theaters"));

    public ShowtimeStoreController() {
        this.config = config;
    }

    @Override
    public List<String> getPopMovies() {

        List<String> movies = showtimeCollection.distinct("movie_name", String.class).into(new ArrayList<>());
        Collections.shuffle(movies);
        List<String> featured = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            featured.add(movies.get(i));
        }
        return featured;
    }

    @Override
    public List<Showtime> getAllShowtimes(String theater, String date) {
        FindIterable<Document> iterable = showtimeCollection.find(and(eq("theater_name", theater), eq("date", date) ));
        List<Showtime> showtimes = new ArrayList<>();
        for(Document doc : iterable){
            String movie = doc.getString("movie_name");
            String time = doc.getString("time");
            String type = doc.getString("type");
            List<Integer> seats = (List<Integer>)doc.get("seats");

            Showtime showtime = new ShowtimeBuilder()
                    .showtime_id("")
                    .movie_id(movie)
                    .theater_id(theater)
                    .date(date)
                    .time(time)
                    .type(type)
                    .seats(seats)
                    .build();
            showtimes.add(showtime);
        }
        return showtimes;
    }

    @Override
    public List<Showtime> getShowtimes(String theater, String date, String movie) {
        FindIterable<Document> iterable = showtimeCollection.find(and(eq("theater_name", theater), eq("date", date), eq("movie_name", movie) ));
        List<Showtime> showtimes = new ArrayList<>();
        for(Document doc : iterable){
            String time = doc.getString("time");
            String type = doc.getString("type");
            List<Integer> seats = (List<Integer>)doc.get("seats");

            Showtime showtime = new ShowtimeBuilder()
                    .showtime_id("")
                    .movie_id(movie)
                    .theater_id(theater)
                    .date(date)
                    .time(time)
                    .type(type)
                    .seats(seats)
                    .build();
            showtimes.add(showtime);
        }
        return showtimes;
    }

    @Override
    public List<Showtime> getShowtimesByMovie(String movie, String date) {
        FindIterable<Document> iterable = showtimeCollection.find(and(eq("movie_name", movie), eq("date", date) ));
        List<Showtime> showtimes = new ArrayList<>();
        for(Document doc : iterable){
            String theater = doc.getString("theater_name");
            String time = doc.getString("time");
            String type = doc.getString("type");
            List<Integer> seats = (List<Integer>)doc.get("seats");

            Showtime showtime = new ShowtimeBuilder()
                    .showtime_id("")
                    .movie_id(movie)
                    .theater_id(theater)
                    .date(date)
                    .time(time)
                    .type(type)
                    .seats(seats)
                    .build();
            showtimes.add(showtime);
        }
        return showtimes;
    }

    @Override
    public String getShowtimeId(String time, String name, String theater_id, String date) {
        String id = null;
        try{
            FindIterable<Document> iterable = showtimeCollection.find(and(eq("theater_name", theater_id), eq("date", date), eq("movie_name", name), eq("time", time) ));
            for(Document doc : iterable){
                id = doc.getObjectId("_id").toHexString();
                break;
            }
        } catch(Exception e){
            e.printStackTrace();
            id = null;
        }
        return id;
    }

    @Override
    public Showtime getShowtimeById(String id) {
        Showtime showtime = null;
        try{
            FindIterable<Document> iterable = showtimeCollection.find(eq("_id", new ObjectId(id)));
            for(Document doc : iterable){
                String movie = doc.getString("movie_name");
                String theater = doc.getString("theater_name");
                String date = doc.getString("date");
                String time = doc.getString("time");
                String type = doc.getString("type");
                List<Integer> seats = (List<Integer>)doc.get("seats");

                showtime = new ShowtimeBuilder()
                        .showtime_id(id)
                        .movie_id(movie)
                        .theater_id(theater)
                        .date(date)
                        .time(time)
                        .type(type)
                        .seats(seats)
                        .build();
                break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return showtime;
    }

    @Override
    public Showtime addShowtime(String movie_name, String theater_name, String date, String time, String type) {
        Showtime showtime;
        Document doc = theaterCollection.find(eq("_id", theater_name)).first();
        try{
            if(doc.isEmpty()){
                return null;
            }
            List<Integer> seats = new ArrayList<>();
            for(int i = 1; i < 31; i++){
                seats.add(i);
            }
            Document newShowtime = new Document()
                    .append("movie_name", movie_name)
                    .append("theater_name", theater_name)
                    .append("date", date)
                    .append("time", time)
                    .append("type", type)
                    .append("seats", seats);
            showtimeCollection.insertOne(newShowtime);

            showtime = new ShowtimeBuilder()
                    .showtime_id("")
                    .movie_id(movie_name)
                    .theater_id(theater_name)
                    .date(date)
                    .time(time)
                    .type(type)
                    .seats(seats)
                    .build();
        } catch(Exception e){
            e.printStackTrace();
            showtime = null;
        }
        return showtime;
    }


    @Override
    public String getMovieName(String id) {
        return null;
    }

    @Override
    public String getDate(String id) {
        return null;
    }

    @Override
    public String getTime(String id) {
        return null;
    }

    @Override
    public String getType(String id) {
        return null;
    }

    @Override
    public String getTheaterId(String id) {
        return null;
    }

    @Override
    public List<Integer> getSeats(String id) {
        return null;
    }
}
