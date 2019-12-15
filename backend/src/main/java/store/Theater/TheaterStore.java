package store.Theater;

import model.Theater;

import java.util.List;

public interface TheaterStore {

    List<Theater> getTheaters();
    List<String> getTheaterAddress(String id);
    String getTheaterPhone(String id);
    String getAdminId(String id);
}
