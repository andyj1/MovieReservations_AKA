package store.Theater;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bson.Document;

import java.util.List;

public class TheaterStoreController implements TheaterStore {

    private final Config config;
    private MongoClient dbClient;
    private MongoDatabase database;
    private MongoCollection<Document> theaterCollection;

    public TheaterStoreController(Config config) {
        this.config = ConfigFactory.load("AKA.conf");
        dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
        database = dbClient.getDatabase("mongo.database");
        theaterCollection = database.getCollection(this.config.getString("mongo.collection_theaters"));
    }

    @Override
    public List<String> getTheaterAddress(String id) {
        return null;
    }

    @Override
    public String getTheaterPhone(String id) {
        return null;
    }

    @Override
    public String getAdminId(String id) {
        return null;
    }
}
