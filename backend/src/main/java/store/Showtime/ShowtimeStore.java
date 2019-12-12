package store.Showtime;

import jdk.internal.jline.internal.Nullable;

import java.util.List;

public interface ShowtimeStore {
    String getShowtimeId(String time, String name, String theater_id);
    String getMovieName(String id);
    String getDate(String id);
    String getTime(String id);
    String getType(String id);
    String getTheaterId(String id);
    List<Integer> getSeats(String id);
}
