package store.FoodReservation;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class FoodReservationStoreController implements FoodReservationStore {

    Config config = ConfigFactory.load("AKA.conf");
    MongoClient dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
    MongoDatabase database = dbClient.getDatabase(config.getString("mongo.database"));
    MongoCollection<Document> foodReservationCollection = database.getCollection(this.config.getString("mongo.collection_collection_food_reservations"));

    public FoodReservationStoreController() {
        this.config = config;
    }

    @Override
    public Integer getCount(String id) {
        try{
            Document doc = foodReservationCollection.find(eq("_id", id)).first();
            if (!doc.isEmpty()) {
                return doc.getInteger("count");
            }
            return 0;
        } catch (MongoException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getUserId(String id) {
        try{
            Document doc = foodReservationCollection.find(eq("user_id", id)).first();
            if (!doc.isEmpty()) {
                return doc.getString("user_id");
            }
            return null;
        } catch (MongoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getShowtimeId(String id) {
        try{
            Document doc = foodReservationCollection.find(eq("showtime_id", id)).first();
            if (!doc.isEmpty()) {
                return doc.getString("showtime_id");
            }
            return null;
        } catch (MongoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getFoodId(String id) {
        try{
            Document doc = foodReservationCollection.find(eq("food_id", id)).first();
            if (!doc.isEmpty()) {
                return doc.getString("food_id");
            }
            return null;
        } catch (MongoException e) {
            e.printStackTrace();
            return null;
        }
    }
}