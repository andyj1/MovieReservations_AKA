package store.Seating;

import java.util.List;

public interface SeatingStore {

    List<Integer> getSeatNum(String id);
    String getUserId(String id);
}
