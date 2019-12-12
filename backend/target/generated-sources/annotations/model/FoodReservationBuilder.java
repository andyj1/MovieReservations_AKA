package model;

import io.norberg.automatter.AutoMatter;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class FoodReservationBuilder {
  private String food_res_id;

  private Integer count;

  private String user_id;

  private String showtime_id;

  private String food_id;

  public FoodReservationBuilder() {
  }

  private FoodReservationBuilder(FoodReservation v) {
    this.food_res_id = v.food_res_id();
    this.count = v.count();
    this.user_id = v.user_id();
    this.showtime_id = v.showtime_id();
    this.food_id = v.food_id();
  }

  private FoodReservationBuilder(FoodReservationBuilder v) {
    this.food_res_id = v.food_res_id;
    this.count = v.count;
    this.user_id = v.user_id;
    this.showtime_id = v.showtime_id;
    this.food_id = v.food_id;
  }

  public String food_res_id() {
    return food_res_id;
  }

  public FoodReservationBuilder food_res_id(String food_res_id) {
    if (food_res_id == null) {
      throw new NullPointerException("food_res_id");
    }
    this.food_res_id = food_res_id;
    return this;
  }

  public Integer count() {
    return count;
  }

  public FoodReservationBuilder count(Integer count) {
    if (count == null) {
      throw new NullPointerException("count");
    }
    this.count = count;
    return this;
  }

  public String user_id() {
    return user_id;
  }

  public FoodReservationBuilder user_id(String user_id) {
    if (user_id == null) {
      throw new NullPointerException("user_id");
    }
    this.user_id = user_id;
    return this;
  }

  public String showtime_id() {
    return showtime_id;
  }

  public FoodReservationBuilder showtime_id(String showtime_id) {
    if (showtime_id == null) {
      throw new NullPointerException("showtime_id");
    }
    this.showtime_id = showtime_id;
    return this;
  }

  public String food_id() {
    return food_id;
  }

  public FoodReservationBuilder food_id(String food_id) {
    if (food_id == null) {
      throw new NullPointerException("food_id");
    }
    this.food_id = food_id;
    return this;
  }

  public FoodReservation build() {
    return new Value(food_res_id, count, user_id, showtime_id, food_id);
  }

  public static FoodReservationBuilder from(FoodReservation v) {
    return new FoodReservationBuilder(v);
  }

  public static FoodReservationBuilder from(FoodReservationBuilder v) {
    return new FoodReservationBuilder(v);
  }

  private static final class Value implements FoodReservation {
    private final String food_res_id;

    private final Integer count;

    private final String user_id;

    private final String showtime_id;

    private final String food_id;

    private Value(@AutoMatter.Field("food_res_id") String food_res_id,
        @AutoMatter.Field("count") Integer count, @AutoMatter.Field("user_id") String user_id,
        @AutoMatter.Field("showtime_id") String showtime_id,
        @AutoMatter.Field("food_id") String food_id) {
      if (food_res_id == null) {
        throw new NullPointerException("food_res_id");
      }
      if (count == null) {
        throw new NullPointerException("count");
      }
      if (user_id == null) {
        throw new NullPointerException("user_id");
      }
      if (showtime_id == null) {
        throw new NullPointerException("showtime_id");
      }
      if (food_id == null) {
        throw new NullPointerException("food_id");
      }
      this.food_res_id = food_res_id;
      this.count = count;
      this.user_id = user_id;
      this.showtime_id = showtime_id;
      this.food_id = food_id;
    }

    @AutoMatter.Field
    @Override
    public String food_res_id() {
      return food_res_id;
    }

    @AutoMatter.Field
    @Override
    public Integer count() {
      return count;
    }

    @AutoMatter.Field
    @Override
    public String user_id() {
      return user_id;
    }

    @AutoMatter.Field
    @Override
    public String showtime_id() {
      return showtime_id;
    }

    @AutoMatter.Field
    @Override
    public String food_id() {
      return food_id;
    }

    public FoodReservationBuilder builder() {
      return new FoodReservationBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof FoodReservation)) {
        return false;
      }
      final FoodReservation that = (FoodReservation) o;
      if (food_res_id != null ? !food_res_id.equals(that.food_res_id()) : that.food_res_id() != null) {
        return false;
      }
      if (count != null ? !count.equals(that.count()) : that.count() != null) {
        return false;
      }
      if (user_id != null ? !user_id.equals(that.user_id()) : that.user_id() != null) {
        return false;
      }
      if (showtime_id != null ? !showtime_id.equals(that.showtime_id()) : that.showtime_id() != null) {
        return false;
      }
      if (food_id != null ? !food_id.equals(that.food_id()) : that.food_id() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.food_res_id != null ? this.food_res_id.hashCode() : 0);
      result = 31 * result + (this.count != null ? this.count.hashCode() : 0);
      result = 31 * result + (this.user_id != null ? this.user_id.hashCode() : 0);
      result = 31 * result + (this.showtime_id != null ? this.showtime_id.hashCode() : 0);
      result = 31 * result + (this.food_id != null ? this.food_id.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "FoodReservation{" +
      "food_res_id=" + food_res_id +
      ", count=" + count +
      ", user_id=" + user_id +
      ", showtime_id=" + showtime_id +
      ", food_id=" + food_id +
      '}';
    }
  }
}
