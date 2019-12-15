package store.FoodReservation;

import model.FoodReservation;

import java.util.List;

public interface FoodReservationStore {
    FoodReservation createFoodReservation(String showtime_id, Integer count, String username, String foodId);
    List<FoodReservation> getFoodReservations(String username);
    Integer getCount(String id);
    String getUserId(String id);
    String getShowtimeId(String id);
    String getFoodId(String id);
}
