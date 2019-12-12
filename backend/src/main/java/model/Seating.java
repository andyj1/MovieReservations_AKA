package model;

import io.norberg.automatter.AutoMatter;

import java.util.List;

@AutoMatter
public interface Seating {
    String showtime_id();
    List<Integer> seat_num();
    String user_id();
}
