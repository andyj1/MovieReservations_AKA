package store.FoodReservation;

public interface FoodReservationStore {

    Integer getCount(String id);
    String getUserId(String id);
    String getShowtimeId(String id);
    String getFoodId(String id);
}
