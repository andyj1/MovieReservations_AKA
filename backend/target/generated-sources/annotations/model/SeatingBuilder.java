package model;

import io.norberg.automatter.AutoMatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class SeatingBuilder {
  private String showtime_id;

  private List<Integer> seat_num;

  private String user_id;

  public SeatingBuilder() {
  }

  private SeatingBuilder(Seating v) {
    this.showtime_id = v.showtime_id();
    List<Integer> _seat_num = v.seat_num();
    this.seat_num = (_seat_num == null) ? null : new ArrayList<Integer>(_seat_num);
    this.user_id = v.user_id();
  }

  private SeatingBuilder(SeatingBuilder v) {
    this.showtime_id = v.showtime_id;
    this.seat_num = (v.seat_num == null) ? null : new ArrayList<Integer>(v.seat_num);
    this.user_id = v.user_id;
  }

  public String showtime_id() {
    return showtime_id;
  }

  public SeatingBuilder showtime_id(String showtime_id) {
    if (showtime_id == null) {
      throw new NullPointerException("showtime_id");
    }
    this.showtime_id = showtime_id;
    return this;
  }

  public List<Integer> seat_num() {
    if (this.seat_num == null) {
      this.seat_num = new ArrayList<Integer>();
    }
    return seat_num;
  }

  public SeatingBuilder seat_num(List<? extends Integer> seat_num) {
    return seat_num((Collection<? extends Integer>) seat_num);
  }

  public SeatingBuilder seat_num(Collection<? extends Integer> seat_num) {
    if (seat_num == null) {
      throw new NullPointerException("seat_num");
    }
    for (Integer item : seat_num) {
      if (item == null) {
        throw new NullPointerException("seat_num: null item");
      }
    }
    this.seat_num = new ArrayList<Integer>(seat_num);
    return this;
  }

  public SeatingBuilder seat_num(Iterable<? extends Integer> seat_num) {
    if (seat_num == null) {
      throw new NullPointerException("seat_num");
    }
    if (seat_num instanceof Collection) {
      return seat_num((Collection<? extends Integer>) seat_num);
    }
    return seat_num(seat_num.iterator());
  }

  public SeatingBuilder seat_num(Iterator<? extends Integer> seat_num) {
    if (seat_num == null) {
      throw new NullPointerException("seat_num");
    }
    this.seat_num = new ArrayList<Integer>();
    while (seat_num.hasNext()) {
      Integer item = seat_num.next();
      if (item == null) {
        throw new NullPointerException("seat_num: null item");
      }
      this.seat_num.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final SeatingBuilder seat_num(Integer... seat_num) {
    if (seat_num == null) {
      throw new NullPointerException("seat_num");
    }
    return seat_num(Arrays.asList(seat_num));
  }

  public String user_id() {
    return user_id;
  }

  public SeatingBuilder user_id(String user_id) {
    if (user_id == null) {
      throw new NullPointerException("user_id");
    }
    this.user_id = user_id;
    return this;
  }

  public Seating build() {
    List<Integer> _seat_num = (seat_num != null) ? Collections.unmodifiableList(new ArrayList<Integer>(seat_num)) : Collections.<Integer>emptyList();
    return new Value(showtime_id, _seat_num, user_id);
  }

  public static SeatingBuilder from(Seating v) {
    return new SeatingBuilder(v);
  }

  public static SeatingBuilder from(SeatingBuilder v) {
    return new SeatingBuilder(v);
  }

  private static final class Value implements Seating {
    private final String showtime_id;

    private final List<Integer> seat_num;

    private final String user_id;

    private Value(@AutoMatter.Field("showtime_id") String showtime_id,
        @AutoMatter.Field("seat_num") List<Integer> seat_num,
        @AutoMatter.Field("user_id") String user_id) {
      if (showtime_id == null) {
        throw new NullPointerException("showtime_id");
      }
      if (user_id == null) {
        throw new NullPointerException("user_id");
      }
      this.showtime_id = showtime_id;
      this.seat_num = (seat_num != null) ? seat_num : Collections.<Integer>emptyList();
      this.user_id = user_id;
    }

    @AutoMatter.Field
    @Override
    public String showtime_id() {
      return showtime_id;
    }

    @AutoMatter.Field
    @Override
    public List<Integer> seat_num() {
      return seat_num;
    }

    @AutoMatter.Field
    @Override
    public String user_id() {
      return user_id;
    }

    public SeatingBuilder builder() {
      return new SeatingBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Seating)) {
        return false;
      }
      final Seating that = (Seating) o;
      if (showtime_id != null ? !showtime_id.equals(that.showtime_id()) : that.showtime_id() != null) {
        return false;
      }
      if (seat_num != null ? !seat_num.equals(that.seat_num()) : that.seat_num() != null) {
        return false;
      }
      if (user_id != null ? !user_id.equals(that.user_id()) : that.user_id() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.showtime_id != null ? this.showtime_id.hashCode() : 0);
      result = 31 * result + (this.seat_num != null ? this.seat_num.hashCode() : 0);
      result = 31 * result + (this.user_id != null ? this.user_id.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "Seating{" +
      "showtime_id=" + showtime_id +
      ", seat_num=" + seat_num +
      ", user_id=" + user_id +
      '}';
    }
  }
}
