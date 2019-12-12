package store.Showtime;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bson.Document;

import java.util.List;

public class ShowtimeStoreController implements ShowtimeStore{

    private final Config config;
    private MongoClient dbClient;
    private MongoDatabase database;
    private MongoCollection<Document> showtimeCollection;

    public ShowtimeStoreController(Config config) {
        this.config = ConfigFactory.load("AKA.conf");
        dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
        database = dbClient.getDatabase("mongo.database");
        showtimeCollection = database.getCollection(this.config.getString("mongo.collection_showtime"));
    }

    @Override
    public String getShowtimeId(String time, String name, String theater_id) {
        return null;
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
