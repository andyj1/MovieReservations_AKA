package store.Food;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import model.Food;
import model.FoodBuilder;
import org.bson.Document;
import utils.tokenizer;

import static com.mongodb.client.model.Filters.eq;

public class FoodStoreController implements FoodStore {

    Config config = ConfigFactory.load("AKA.conf");
    MongoClient dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
    MongoDatabase database = dbClient.getDatabase(config.getString("mongo.database"));
    MongoCollection<Document> foodCollection = database.getCollection(this.config.getString("mongo.collection_food"));

    public FoodStoreController() {
        this.config = config;
    }

    @Override
    public Food addFood(String food_id, String food_desc) {
        Food food = null;
        try{
            food = new FoodBuilder()
                    .food_id(food_id)
                    .food_desc(food_desc)
                    .build();
            Document foodDoc = new Document()
                    .append("food_id", food_id)
                    .append("food_desc", food_desc);
            foodCollection.insertOne(foodDoc);
        } catch (Exception e){
            e.printStackTrace();
            food = null;
        }
        return food;
    }

    @Override
    public String getFoodId(String food) {
        try{
            Document doc = foodCollection.find(eq("food_desc", food)).first();
            if (!doc.isEmpty()) {
                return doc.getString("food_id");
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
            Document doc = foodCollection.find(eq("food_id", id)).first();
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