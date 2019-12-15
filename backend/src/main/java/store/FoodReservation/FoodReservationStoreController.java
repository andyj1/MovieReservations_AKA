package store.FoodReservation;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import model.*;
import org.bson.Document;
import store.Food.FoodStore;
import store.Food.FoodStoreController;
import store.Showtime.ShowtimeStore;
import store.Showtime.ShowtimeStoreController;
import store.User.UserStore;
import store.User.UserStoreController;

import static com.mongodb.client.model.Filters.eq;

public class FoodReservationStoreController implements FoodReservationStore {

    Config config = ConfigFactory.load("AKA.conf");
    MongoClient dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
    MongoDatabase database = dbClient.getDatabase(config.getString("mongo.database"));
    MongoCollection<Document> foodReservationCollection = database.getCollection(config.getString("mongo.collection_food_reservations"));

    UserStore us = new UserStoreController();
    ShowtimeStore ss = new ShowtimeStoreController();
    FoodStore fs = new FoodStoreController();

    public FoodReservationStoreController() {
        this.config = config;
    }

    @Override
    public FoodReservation createFoodReservation(String showtime_id, Integer count, String username, String foodId) {
        FoodReservation foodReservation = null;
        try{
            User user = us.getUser(username);
            Showtime showtime = ss.getShowtimeById(showtime_id);
            String foodDesc = fs.getFoodDesc(foodId);
            Food food = new FoodBuilder()
                    .food_id(foodId)
                    .food_desc(foodDesc)
                    .build();

            Document doc = new Document()
                    .append("count", count)
                    .append("username", username)
                    .append("showtime_id", showtime_id)
                    .append("food_id", foodId);

            foodReservationCollection.insertOne(doc);

            foodReservation = new FoodReservationBuilder()
                    .food_res_id("")
                    .count(count)
                    .user_id(username)
                    .showtime_id(showtime_id)
                    .food_id(foodId)
                    .build();

        } catch (Exception e){
            e.printStackTrace();
            foodReservation = null;
        }
        return foodReservation;
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