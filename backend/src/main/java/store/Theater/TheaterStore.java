package store.Theater;

import java.util.List;

public interface TheaterStore {

    List<String> getTheaterAddress(String id);
    String getTheaterPhone(String id);
    String getAdminId(String id);
}
