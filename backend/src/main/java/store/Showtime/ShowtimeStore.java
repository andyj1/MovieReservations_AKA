package store.Showtime;

import model.Showtime;

import java.util.List;

public interface ShowtimeStore {
    List<String> getPopMovies();
    List<Showtime> getAllShowtimes(String theater, String date);
    List<Showtime> getShowtimes(String theater, String date, String movie);
    List<Showtime> getShowtimesByMovie(String movie, String date);
    String getShowtimeId(String time, String name, String theater_id, String date);
    Showtime getShowtimeById(String id);
    Showtime addShowtime(String movie_name, String theater_name, String date, String time, String type);
    String getMovieName(String id);
    String getDate(String id);
    String getTime(String id);
    String getType(String id);
    String getTheaterId(String id);
    List<Integer> getSeats(String id);
}
