package store.User;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import model.User;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

import java.util.Date;
import java.util.List;

public class UserStoreController implements UserStore {

    private final Config config;
    private MongoClient dbClient;
    private MongoDatabase database;
    private MongoCollection<Document> userCollection;

    public UserStoreController(Config config) {

        this.config = ConfigFactory.load("AKA.conf");
        dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
        database = dbClient.getDatabase("mongo.database");
        userCollection = database.getCollection(this.config.getString("mongo.collection_user"));

    }

    @Override
    public Integer addUser(User newUser){
        String name = newUser.name();
        String username = newUser.username();
        String password = newUser.password();
        String email = newUser.email();
        Boolean admin = newUser.admin();
        Date date = new Date();

        Document userDoc = new Document()
                .append("name", name)
                .append("username", username)
                .append("name", password)
                .append("email", email)
                .append("admin", admin)
                .append("date", date);


        Document dupUsername = userCollection.find(eq("username", username)).first();
        if(dupUsername != null){
            return 2;
        }

        Document dupEmail = userCollection.find(eq("username", username)).first();
        if(dupEmail != null){
            return 3;
        }

        try {
            userCollection.insertOne(userDoc);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<User> getUser(String name, String id){
        return null;
    }

    @Override
    public String login(String username, String password){
        Document userDoc;
        String token = null;

        try{
            userDoc = userCollection.find(eq("username", username)).first();
            String pwd = userDoc.getString("password");
            if(password.equals(pwd)){
                String uid = userDoc.getObjectId("_id").toHexString();
                // token = authUtils.createToken(uid);
            }
        } catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        if(token != null){
            return token;
        } else{
            return "invalid";
        }
    }

}
