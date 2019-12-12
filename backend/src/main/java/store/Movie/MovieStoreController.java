package store.Movie;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bson.Document;

import java.util.List;

public class MovieStoreController implements MovieStore {

    private final Config config;
    private MongoClient dbClient;
    private MongoDatabase database;
    private MongoCollection<Document> movieCollection;

    public MovieStoreController(Config config) {
        this.config = ConfigFactory.load("AKA.conf");
        dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
        database = dbClient.getDatabase("mongo.database");
        movieCollection = database.getCollection(this.config.getString("mongo.collection_movies"));
    }

    @Override
    public String getMovieId(String title) {
        return null;
    }

    @Override
    public String getMovieTitle(String id) {
        return null;
    }

    @Override
    public String getConsensus(String id) {
        return null;
    }

    @Override
    public String getCriticRating(String id) {
        return null;
    }

    @Override
    public String getCriticCount(String id) {
        return null;
    }

    @Override
    public String getAudienceCount(String id) {
        return null;
    }

    @Override
    public String getMovieDescription(String id) {
        return null;
    }

    @Override
    public String getMovieRating(String id) {
        return null;
    }

    @Override
    public List<String> getMovieGenre(String id) {
        return null;
    }

    @Override
    public List<String> getMovieDirector(String id) {
        return null;
    }

    @Override
    public List<String> getMovieWriter(String id) {
        return null;
    }

    @Override
    public String getMovieAirDate(String id) {
        return null;
    }

    @Override
    public String getMovieRuntime(String id) {
        return null;
    }

    @Override
    public String getMovieStudio(String id) {
        return null;
    }

}
