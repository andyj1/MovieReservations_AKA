package model;

import jdk.internal.jline.internal.Nullable;

import java.util.Date;

public interface User {
    String user_id();
    String name();
    String username();
    String password();
    String email();
    Boolean admin();
    Date created_at();
    @Nullable String favorite_genre();
    @Nullable String zip_code();
}
