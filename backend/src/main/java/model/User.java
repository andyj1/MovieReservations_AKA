package model;

import com.mongodb.lang.Nullable;
import io.norberg.automatter.AutoMatter;

import java.util.Date;

@AutoMatter
public interface User {
    String user_id();
    String name();
    String username();
    String password();
    String email();
    Boolean admin();
    Date created_at();
    @Nullable
    String favorite_genre();
    @Nullable String zip_code();
}
