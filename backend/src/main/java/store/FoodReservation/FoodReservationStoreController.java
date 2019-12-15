package store.FoodReservation;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bson.Document;
import store.Showtime.ShowtimeStore;
import store.Showtime.ShowtimeStoreController;
import store.User.UserStore;
import store.User.UserStoreController;

public class FoodReservationStoreController implements FoodReservationStore {

    Config config = ConfigFactory.load("AKA.conf");
    MongoClient dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
    MongoDatabase database = dbClient.getDatabase(config.getString("mongo.database"));
    MongoCollection<Document> foodReservationCollection = database.getCollection(config.getString("mongo.collection_food_reservations"));

    UserStore us = new UserStoreController();
    ShowtimeStore ss = new ShowtimeStoreController();

    public FoodReservationStoreController() {
        this.config = config;
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
