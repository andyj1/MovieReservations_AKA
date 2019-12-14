package store.User;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import model.User;
import model.UserBuilder;
import org.bson.Document;
import org.bson.conversions.Bson;
import utils.tokenizer;

import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

public class UserStoreController implements UserStore {

    Config config = ConfigFactory.load("AKA.conf");
    MongoClient dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
    MongoDatabase database = dbClient.getDatabase("mongo.database");
    MongoCollection<Document> userCollection = database.getCollection(config.getString("mongo.collection_user"));

    private static tokenizer tokenGenerator;

    public UserStoreController() {
        this.config = config;
    }

    // TODO: add a user to database given a User object
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
        // check for duplicate username
        Document dupUsername = userCollection.find(eq("username", username)).first();
        if(dupUsername != null){
            return 2;
        }
        // check for duplicate email
        Document dupEmail = userCollection.find(eq("email", email)).first();
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

    // TODO: verifies Y/N whether user exists and password is valid
    public boolean authenticate(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        User user = getUser(username);
        if (user == null) {
            return false;
        }
        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
    }

    // TODO: get user object given username
    public User getUser(String user_name) {
        Document doc;
        doc = userCollection.find(eq("username", new String(user_name))).first();
        String user_id = doc.getString("_id");
        String name = doc.getString("name");
        String username = doc.getString("username");
        String password = doc.getString("password");
        String email = doc.getString("email");
        Boolean admin = doc.getBoolean("admin");
        Date created_at = doc.getDate("created_at");
        String favorite_genre = doc.getString("favorite_genre");
        String zip_code = doc.getString("zip_code");
        // build a user object (auto-matter)
        User user = new UserBuilder()
                .user_id(user_id)
                .name(name)
                .username(username)
                .password(password)
                .email(email)
                .admin(admin)
                .created_at(created_at)
                .favorite_genre(favorite_genre)
                .zip_code(zip_code)
                .build();
        return user;
    }

    // TODO: set password given username and old password
    public void setPassword(String username, String oldPassword, String newPassword) {
        Document doc = userCollection.find(eq("username", new String(username))).first();
        if (doc != null && authenticate(username, oldPassword)) {
            // TODO: update oldPassword to newPassword in the database
            Bson filter = new Document("username", username);
            Bson newPW = new Document("password", newPassword);
            Bson updateOperationDoc = new Document("$set", newPW);
            userCollection.updateOne(filter, updateOperationDoc);
        }
    }

    // TODO: login with username and password
    public String login(String username, String password){
        Document userDoc;
        String token = null;

        try{
            userDoc = userCollection.find(eq("username", username)).first();
            String pwd = userDoc.getString("password");
            if(password.equals(pwd)){
                String uname = userDoc.getString("username");
                token = tokenGenerator.makeToken(username);
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

    public User updateUser(String user_id, String name, String username, String password, String email) {

        return null;
    }

}
