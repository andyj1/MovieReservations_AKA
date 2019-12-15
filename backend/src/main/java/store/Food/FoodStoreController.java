package store.Food;

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

public class FoodStoreController implements FoodStore {

    Config config = ConfigFactory.load("AKA.conf");
    MongoClient dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
    MongoDatabase database = dbClient.getDatabase(config.getString("mongo.database"));
    MongoCollection<Document> foodCollection = database.getCollection(config.getString("mongo.collection_food"));

    UserStore us = new UserStoreController();
    ShowtimeStore ss = new ShowtimeStoreController();

    public FoodStoreController() {
        this.config = config;
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
