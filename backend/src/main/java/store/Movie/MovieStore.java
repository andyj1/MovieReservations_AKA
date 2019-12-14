package store.Movie;

import java.util.List;

public interface MovieStore {
    String getMovieId(String title);
    String getMovieTitle(String id);
    String getConsensus(String id);
    String getCriticRating(String id);
    String getCriticCount(String id);
    String getAudienceCount(String id);
    String getMovieDescription(String id);
    String getMovieRating(String id);
    List<String> getMovieGenre(String id);
    List<String> getMovieDirector(String id);
    List<String> getMovieWriter(String id);
    String getMovieAirDate(String id);
    String getMovieRuntime(String id);
    String getMovieStudio(String id);
}
