package store.FoodReservation;

import model.FoodReservation;

public interface FoodReservationStore {
    FoodReservation createFoodReservation(String showtime_id, Integer count, String username, String foodId);
    Integer getCount(String id);
    String getUserId(String id);
    String getShowtimeId(String id);
    String getFoodId(String id);
}
