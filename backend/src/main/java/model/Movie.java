package model;

import com.mongodb.lang.Nullable;
import io.norberg.automatter.AutoMatter;

import java.util.List;
@AutoMatter
public interface Movie {
    String movie_id();
    String title();
    @Nullable
    String consensus();
    @Nullable String critic_rating();
    @Nullable String critic_count();
    @Nullable String audience_count();
    @Nullable String description();
    @Nullable String rating();
    @Nullable List<String> genre();
    @Nullable List<String> director();
    @Nullable List<String> writer();
    @Nullable String air_date();
    @Nullable String runtime();
    @Nullable String studio();
}
