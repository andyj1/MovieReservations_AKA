package model;

import com.mongodb.lang.Nullable;
import io.norberg.automatter.AutoMatter;

import java.util.List;

@AutoMatter
public interface Showtime {
    String showtime_id();
    String movie_id();
    String date();
    String time();
    String type();
    String theater_id();
    @Nullable
    List<Integer> seats();
}
