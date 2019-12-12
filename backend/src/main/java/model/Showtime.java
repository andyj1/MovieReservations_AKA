package model;

import jdk.internal.jline.internal.Nullable;

import java.util.List;

public interface Showtime {
    String showtime_id();
    String movie_name();
    String date();
    String time();
    String type();
    String theater_id();
    @Nullable List<Integer> seats();
}
