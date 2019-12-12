package store.Seating;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bson.Document;

import java.util.List;

public class SeatingStoreController implements SeatingStore{

    private final Config config;
    private MongoClient dbClient;
    private MongoDatabase database;
    private MongoCollection<Document> seatCollection;

    public SeatingStoreController(Config config) {
        this.config = ConfigFactory.load("AKA.conf");
        dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
        database = dbClient.getDatabase("mongo.database");
        seatCollection = database.getCollection(this.config.getString("mongo.collection_seating"));
    }

    @Override
    public List<Integer> getSeatNum(String id) {
        return null;
    }

    @Override
    public String getUserId(String id) {
        return null;
    }
}
