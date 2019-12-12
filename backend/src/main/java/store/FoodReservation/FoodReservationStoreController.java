package store.FoodReservation;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bson.Document;

public class FoodReservationStoreController implements FoodReservationStore {

    private final Config config;
    private MongoClient dbClient;
    private MongoDatabase database;
    private MongoCollection<Document> foodResCollection;

    public FoodReservationStoreController(Config config) {
        this.config = ConfigFactory.load("AKA.conf");
        dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
        database = dbClient.getDatabase("mongo.database");
        foodResCollection = database.getCollection(this.config.getString("mongo.collection_food_reservations"));
    }

    @Override
    public Integer getCount(String id) {
        return null;
    }

    @Override
    public String getUserId(String id) {
        return null;
    }

    @Override
    public String getShowtimeId(String id) {
        return null;
    }

    @Override
    public String getFoodId(String id) {
        return null;
    }
}
