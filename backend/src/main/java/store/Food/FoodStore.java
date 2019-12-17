package store.Food;

import model.Food;

import java.util.List;

public interface FoodStore {
    List<Food> getAllFood();
    Food addFood(String food_id, String food_desc);
    String getFoodId(String food);
    String getFoodDesc(String id);
}
