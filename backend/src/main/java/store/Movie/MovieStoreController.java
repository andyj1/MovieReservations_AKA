package store.Movie;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import model.Movie;
import model.MovieBuilder;
import org.bson.Document;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MovieStoreController implements MovieStore {

    Config config = ConfigFactory.load("AKA.conf");
    MongoClient dbClient = new MongoClient(new MongoClientURI(config.getString("mongo.uri")));
    MongoDatabase database = dbClient.getDatabase(config.getString("mongo.database"));
    MongoCollection<Document> movieCollection = database.getCollection(config.getString("mongo.collection_movies"));

    public MovieStoreController() {
        this.config = config;
    }

    @Override
    public Movie getMovieInfo(String movie_name) {
        Movie movie = null;
        try{
            Document doc = movieCollection.find(eq("title", movie_name)).first();
            String consensus = doc.getString("consensus");
            String critic_rating = doc.getString("critic_rating");
            Integer critic_count = (Integer) doc.get("critic_count");
            Integer audience_count = (Integer) doc.get("audience_count");
            String description = doc.getString("description");
            String rating = doc.getString("rating");
            List<String> genre = (List<String>)doc.get("genre");
            List<String> director = (List<String>)doc.get("director");
            List<String> writer = (List<String>)doc.get("writer");
            String air_date = doc.getString("air_date");
            String run_time = doc.getString("run_time");
            String studio = doc.getString("studio");

            movie = new MovieBuilder()
                    .movie_id("")
                    .title(movie_name)
                    .consensus(consensus)
                    .critic_rating(critic_rating)
                    .critic_count(critic_count)
                    .audience_count(audience_count)
                    .description(description)
                    .rating(rating)
                    .genre(genre)
                    .director(director)
                    .writer(writer)
                    .air_date(air_date)
                    .runtime(run_time)
                    .studio(studio)
                    .build();
        } catch(Exception e){
            e.printStackTrace();
            movie = null;
        }
        return movie;
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
