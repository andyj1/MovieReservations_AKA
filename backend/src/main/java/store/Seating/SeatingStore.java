package store.Seating;

import model.Seating;

import java.util.List;

public interface SeatingStore {
    Seating createSeating(String showtime_id, List<Integer> req_seats, String user_id);
    List<Integer> getSeatNum(String id);
    String getUserId(String id);
}
