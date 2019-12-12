import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.log4j.BasicConfigurator;

import java.net.UnknownHostException;

import static spark.Spark.get;

public class App {


    public static void main(String[] args) throws UnknownHostException {

    }

    public static void init(){
        BasicConfigurator.configure();
        get("/entrance", (req, res) -> "Hello World");

    }
}
