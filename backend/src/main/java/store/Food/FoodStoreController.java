package store.Food;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bson.Document;

public class FoodStoreController implements FoodStore {

    private final Config config;
    private MongoClient dbClient;
    private MongoDatabase database;
    private MongoCollection<Document> foodCollection;

    public FoodStoreController(Config config) {
        this.config = ConfigFactory.load("AKA.conf");
        dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
        database = dbClient.getDatabase("mongo.database");
        foodCollection = database.getCollection(this.config.getString("mongo.collection_food"));
    }

    @Override
    public String getFoodId(String food) {
        return null;
    }

    @Override
    public String getFoodDesc(String id) {
        return null;
    }
}
