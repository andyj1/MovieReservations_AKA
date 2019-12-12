import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.log4j.BasicConfigurator;

import java.net.UnknownHostException;

import static spark.Spark.get;

public class main {


    public static void main(String[] args) throws UnknownHostException {
        BasicConfigurator.configure();
        get("/entrance", (req, res) -> "Hello World");
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB database = mongoClient.getDB("AKA");
        DBCollection collection = database.getCollection("Movies");
    }
}
