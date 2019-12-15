package store.Food;

import model.Food;

public interface FoodStore {
    Food addFood(String food_id, String food_desc);
    String getFoodId(String food);
    String getFoodDesc(String id);
}
