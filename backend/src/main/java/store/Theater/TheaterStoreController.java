package store.Theater;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import model.Theater;
import model.TheaterBuilder;
import model.User;
import model.UserBuilder;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TheaterStoreController implements TheaterStore {

    Config config = ConfigFactory.load("AKA.conf");
    MongoClient dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
    MongoDatabase database = dbClient.getDatabase(config.getString("mongo.database"));
    MongoCollection<Document> theaterCollection = database.getCollection(config.getString("mongo.collection_theaters"));

    public TheaterStoreController() {
        this.config = config;
    }

    @Override
    public List<Theater> getTheaters() {
        FindIterable<Document> iterable = theaterCollection.find();
        List<Theater> theaters = new ArrayList<>();

        for(Document doc : iterable){
            String theater_name = doc.getString("_id");
            List<String> theater_address = (List<String>)doc.get("theater_address");
            String theater_phone = doc.getString("theater_phone");
            Theater theater = new TheaterBuilder()
                    .theater_id(theater_name)
                    .theater_address(theater_address)
                    .theater_phone(theater_phone)
                    .admin_id("")
                    .build();
            theaters.add(theater);
        }
        return theaters;
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
