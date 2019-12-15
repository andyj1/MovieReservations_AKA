package model;

import io.norberg.automatter.AutoMatter;

import java.util.List;

@AutoMatter
public interface Theater {
    String theater_id();
    List<String> theater_address();
    String theater_phone();
    String admin_id();
}
