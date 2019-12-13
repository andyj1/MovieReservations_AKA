package model;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Food {
    String food_id();
    String food_desc();
}
