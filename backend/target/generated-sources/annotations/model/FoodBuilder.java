package model;

import io.norberg.automatter.AutoMatter;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class FoodBuilder {
  private String food_id;

  private String food_desc;

  public FoodBuilder() {
  }

  private FoodBuilder(Food v) {
    this.food_id = v.food_id();
    this.food_desc = v.food_desc();
  }

  private FoodBuilder(FoodBuilder v) {
    this.food_id = v.food_id;
    this.food_desc = v.food_desc;
  }

  public String food_id() {
    return food_id;
  }

  public FoodBuilder food_id(String food_id) {
    if (food_id == null) {
      throw new NullPointerException("food_id");
    }
    this.food_id = food_id;
    return this;
  }

  public String food_desc() {
    return food_desc;
  }

  public FoodBuilder food_desc(String food_desc) {
    if (food_desc == null) {
      throw new NullPointerException("food_desc");
    }
    this.food_desc = food_desc;
    return this;
  }

  public Food build() {
    return new Value(food_id, food_desc);
  }

  public static FoodBuilder from(Food v) {
    return new FoodBuilder(v);
  }

  public static FoodBuilder from(FoodBuilder v) {
    return new FoodBuilder(v);
  }

  private static final class Value implements Food {
    private final String food_id;

    private final String food_desc;

    private Value(@AutoMatter.Field("food_id") String food_id,
        @AutoMatter.Field("food_desc") String food_desc) {
      if (food_id == null) {
        throw new NullPointerException("food_id");
      }
      if (food_desc == null) {
        throw new NullPointerException("food_desc");
      }
      this.food_id = food_id;
      this.food_desc = food_desc;
    }

    @AutoMatter.Field
    @Override
    public String food_id() {
      return food_id;
    }

    @AutoMatter.Field
    @Override
    public String food_desc() {
      return food_desc;
    }

    public FoodBuilder builder() {
      return new FoodBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Food)) {
        return false;
      }
      final Food that = (Food) o;
      if (food_id != null ? !food_id.equals(that.food_id()) : that.food_id() != null) {
        return false;
      }
      if (food_desc != null ? !food_desc.equals(that.food_desc()) : that.food_desc() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.food_id != null ? this.food_id.hashCode() : 0);
      result = 31 * result + (this.food_desc != null ? this.food_desc.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "Food{" +
      "food_id=" + food_id +
      ", food_desc=" + food_desc +
      '}';
    }
  }
}
