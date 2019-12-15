package store.Food;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bson.Document;
import utils.tokenizer;

import static com.mongodb.client.model.Filters.eq;

public class FoodStoreController implements FoodStore {

    Config config = ConfigFactory.load("AKA.conf");
    MongoClient dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
    MongoDatabase database = dbClient.getDatabase(config.getString("mongo.database"));
    MongoCollection<Document> foodCollection = database.getCollection(this.config.getString("mongo.collection_food"));

    private static tokenizer tokenGenerator;

    public FoodStoreController() {
        this.config = config;
    }

    @Override
    public String getFoodId(String food) {
        try{
            Document doc = foodCollection.find(eq("food_desc", food)).first();
            if (!doc.isEmpty()) {
                return doc.getObjectId("_id").toHexString();
            }
            return null;
        } catch (MongoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getFoodDesc(String id) {
        try{
            Document doc = foodCollection.find(eq("_id", id)).first();
            if (!doc.isEmpty()) {
                return doc.getString("food_desc");
            }
            return null;
        } catch (MongoException e) {
            e.printStackTrace();
            return null;
        }
    }
}
