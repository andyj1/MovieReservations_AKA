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
    MongoDatabase database = dbClient.getDatabase(config.getString("mongo.database"));
    MongoCollection<Document> userCollection = database.getCollection(config.getString("mongo.collection_users"));

    private static tokenizer tokenGenerator;

    public UserStoreController() {
        this.config = config;
        this.tokenGenerator = new tokenizer();
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
        String genre = newUser.favorite_genre();
        String zip = newUser.zip_code();

        Document userDoc = new Document()
                .append("name", name)
                .append("username", username)
                .append("password", password)
                .append("email", email)
                .append("admin", admin)
                .append("created_at", date)
                .append("favorite_genre", genre)
                .append("zip_code", zip);
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
        User user = null;
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        try {
            user = getUser(username);
            if (user == null) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        if(!user.password().equals(password)){
            return false;
        }
        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
    }

    // TODO: get user object given username
    public User getUser(String user_name) {
        Document doc;
        User user;
        try{
            doc = userCollection.find(eq("username", new String(user_name))).first();
            String user_id = doc.getObjectId("_id").toHexString();
            String name = doc.getString("name");
            String username = doc.getString("username");
            String password = doc.getString("password");
            String email = doc.getString("email");
            Boolean admin = doc.getBoolean("admin");
            Date created_at = doc.getDate("created_at");
            String favorite_genre = doc.getString("favorite_genre");
            String zip_code = doc.getString("zip_code");
            // build a user object (auto-matter)
            user = new UserBuilder()
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
        } catch (Exception e){
            e.printStackTrace();
            user = null;
        }
        return user;
    }

    public User getUserProfile(String user_name) {
        Document doc;
        User user;
        try{
            doc = userCollection.find(eq("username", new String(user_name))).first();
            String user_id = doc.getObjectId("_id").toHexString();
            String name = doc.getString("name");
            String username = doc.getString("username");
            String email = doc.getString("email");
            Boolean admin = doc.getBoolean("admin");
            Date created_at = doc.getDate("created_at");
            String favorite_genre = doc.getString("favorite_genre");
            String zip_code = doc.getString("zip_code");
            // build a user object (auto-matter)
            user = new UserBuilder()
                    .user_id(user_id)
                    .name(name)
                    .username(username)
                    .password("")
                    .email(email)
                    .admin(admin)
                    .created_at(created_at)
                    .favorite_genre(favorite_genre)
                    .zip_code(zip_code)
                    .build();
        } catch (Exception e){
            e.printStackTrace();
            user = null;
        }
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

    // TODO: return token upon login with valid username and password
    public String generateToken(String username, String password){
        String token = null;
        try{
            if(authenticate(username, password)){
                token = this.tokenGenerator.makeToken(username);
            }
        } catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        if(token != null){
            return token;
        } else{
            return "login failed.";
        }
    }

    public User updateUser(String user_id, String name, String username, String password, String email) {

        return null;
    }

}
