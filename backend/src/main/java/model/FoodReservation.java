package model;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface FoodReservation {
    String food_res_id();
    Integer count();
    String user_id();       // username
    String showtime_id();
    String food_id();
}
