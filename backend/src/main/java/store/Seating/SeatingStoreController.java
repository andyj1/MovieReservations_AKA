package store.Seating;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import model.Seating;
import model.SeatingBuilder;
import model.Showtime;
import model.User;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import store.Showtime.ShowtimeStore;
import store.Showtime.ShowtimeStoreController;
import store.User.UserStore;
import store.User.UserStoreController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeatingStoreController implements SeatingStore{

    Config config = ConfigFactory.load("AKA.conf");
    MongoClient dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
    MongoDatabase database = dbClient.getDatabase(config.getString("mongo.database"));
    MongoCollection<Document> seatingCollection = database.getCollection(config.getString("mongo.collection_seating"));
    MongoCollection<Document> showtimeCollection = database.getCollection(config.getString("mongo.collection_showtime"));

    UserStore us = new UserStoreController();
    ShowtimeStore ss = new ShowtimeStoreController();

    public SeatingStoreController() {
        this.config = config;
    }

    @Override
    public Seating createSeating(String showtime_id, List<Integer> req_seats, String user_id) {
        Seating seating = null;
        List<Integer> res_seats = new ArrayList<>();
        List<Integer> avail_seats, updated_seats;
        try{
            User user = us.getUser(user_id);
            Showtime showtime = ss.getShowtimeById(showtime_id);
            avail_seats = new ArrayList<>(showtime.seats());
            updated_seats = new ArrayList<>(showtime.seats());
            for(Integer i : req_seats){
                for(Integer j : avail_seats){
                    if(i == j){
                        updated_seats.remove(new Integer(i));
                        res_seats.add(j);
                    }
                }
            }

            seating = new SeatingBuilder()
                    .showtime_id(showtime_id)
                    .seat_num(res_seats)
                    .user_id(user.username())
                    .build();

            Document seat = new Document()
                    .append("showtime_id", showtime_id)
                    .append("seat_num", res_seats)
                    .append("username", user.username());

            seatingCollection.insertOne(seat);

            Bson filter = new Document("_id", new ObjectId(showtime_id));
            Bson newSeats = new Document("seats", updated_seats);
            Bson updateOperationDoc = new Document("$set", newSeats);
            showtimeCollection.updateOne(filter, updateOperationDoc);

        } catch (Exception e){
            e.printStackTrace();
            seating = null;
        }
        return seating;
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
