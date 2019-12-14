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
public final class ShowtimeBuilder {
  private String showtime_id;

  private String movie_id;

  private String date;

  private String time;

  private String type;

  private String theater_id;

  private List<Integer> seats;

  public ShowtimeBuilder() {
  }

  private ShowtimeBuilder(Showtime v) {
    this.showtime_id = v.showtime_id();
    this.movie_id = v.movie_id();
    this.date = v.date();
    this.time = v.time();
    this.type = v.type();
    this.theater_id = v.theater_id();
    List<Integer> _seats = v.seats();
    this.seats = (_seats == null) ? null : new ArrayList<Integer>(_seats);
  }

  private ShowtimeBuilder(ShowtimeBuilder v) {
    this.showtime_id = v.showtime_id;
    this.movie_id = v.movie_id;
    this.date = v.date;
    this.time = v.time;
    this.type = v.type;
    this.theater_id = v.theater_id;
    this.seats = (v.seats == null) ? null : new ArrayList<Integer>(v.seats);
  }

  public String showtime_id() {
    return showtime_id;
  }

  public ShowtimeBuilder showtime_id(String showtime_id) {
    if (showtime_id == null) {
      throw new NullPointerException("showtime_id");
    }
    this.showtime_id = showtime_id;
    return this;
  }

  public String movie_id() {
    return movie_id;
  }

  public ShowtimeBuilder movie_id(String movie_id) {
    if (movie_id == null) {
      throw new NullPointerException("movie_id");
    }
    this.movie_id = movie_id;
    return this;
  }

  public String date() {
    return date;
  }

  public ShowtimeBuilder date(String date) {
    if (date == null) {
      throw new NullPointerException("date");
    }
    this.date = date;
    return this;
  }

  public String time() {
    return time;
  }

  public ShowtimeBuilder time(String time) {
    if (time == null) {
      throw new NullPointerException("time");
    }
    this.time = time;
    return this;
  }

  public String type() {
    return type;
  }

  public ShowtimeBuilder type(String type) {
    if (type == null) {
      throw new NullPointerException("type");
    }
    this.type = type;
    return this;
  }

  public String theater_id() {
    return theater_id;
  }

  public ShowtimeBuilder theater_id(String theater_id) {
    if (theater_id == null) {
      throw new NullPointerException("theater_id");
    }
    this.theater_id = theater_id;
    return this;
  }

  public List<Integer> seats() {
    return seats;
  }

  public ShowtimeBuilder seats(List<? extends Integer> seats) {
    return seats((Collection<? extends Integer>) seats);
  }

  public ShowtimeBuilder seats(Collection<? extends Integer> seats) {
    if (seats == null) {
      this.seats = null;
      return this;
    }
    this.seats = new ArrayList<Integer>(seats);
    return this;
  }

  public ShowtimeBuilder seats(Iterable<? extends Integer> seats) {
    if (seats == null) {
      this.seats = null;
      return this;
    }
    if (seats instanceof Collection) {
      return seats((Collection<? extends Integer>) seats);
    }
    return seats(seats.iterator());
  }

  public ShowtimeBuilder seats(Iterator<? extends Integer> seats) {
    if (seats == null) {
      this.seats = null;
      return this;
    }
    this.seats = new ArrayList<Integer>();
    while (seats.hasNext()) {
      Integer item = seats.next();
      this.seats.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final ShowtimeBuilder seats(Integer... seats) {
    if (seats == null) {
      this.seats = null;
      return this;
    }
    return seats(Arrays.asList(seats));
  }

  public ShowtimeBuilder addSeat(Integer seat) {
    if (this.seats == null) {
      this.seats = new ArrayList<Integer>();
    }
    seats.add(seat);
    return this;
  }

  public Showtime build() {
    List<Integer> _seats = (seats != null) ? Collections.unmodifiableList(new ArrayList<Integer>(seats)) : null;
    return new Value(showtime_id, movie_id, date, time, type, theater_id, _seats);
  }

  public static ShowtimeBuilder from(Showtime v) {
    return new ShowtimeBuilder(v);
  }

  public static ShowtimeBuilder from(ShowtimeBuilder v) {
    return new ShowtimeBuilder(v);
  }

  private static final class Value implements Showtime {
    private final String showtime_id;

    private final String movie_id;

    private final String date;

    private final String time;

    private final String type;

    private final String theater_id;

    private final List<Integer> seats;

    private Value(@AutoMatter.Field("showtime_id") String showtime_id,
        @AutoMatter.Field("movie_id") String movie_id, @AutoMatter.Field("date") String date,
        @AutoMatter.Field("time") String time, @AutoMatter.Field("type") String type,
        @AutoMatter.Field("theater_id") String theater_id,
        @AutoMatter.Field("seats") List<Integer> seats) {
      if (showtime_id == null) {
        throw new NullPointerException("showtime_id");
      }
      if (movie_id == null) {
        throw new NullPointerException("movie_id");
      }
      if (date == null) {
        throw new NullPointerException("date");
      }
      if (time == null) {
        throw new NullPointerException("time");
      }
      if (type == null) {
        throw new NullPointerException("type");
      }
      if (theater_id == null) {
        throw new NullPointerException("theater_id");
      }
      this.showtime_id = showtime_id;
      this.movie_id = movie_id;
      this.date = date;
      this.time = time;
      this.type = type;
      this.theater_id = theater_id;
      this.seats = seats;
    }

    @AutoMatter.Field
    @Override
    public String showtime_id() {
      return showtime_id;
    }

    @AutoMatter.Field
    @Override
    public String movie_id() {
      return movie_id;
    }

    @AutoMatter.Field
    @Override
    public String date() {
      return date;
    }

    @AutoMatter.Field
    @Override
    public String time() {
      return time;
    }

    @AutoMatter.Field
    @Override
    public String type() {
      return type;
    }

    @AutoMatter.Field
    @Override
    public String theater_id() {
      return theater_id;
    }

    @AutoMatter.Field
    @Override
    public List<Integer> seats() {
      return seats;
    }

    public ShowtimeBuilder builder() {
      return new ShowtimeBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Showtime)) {
        return false;
      }
      final Showtime that = (Showtime) o;
      if (showtime_id != null ? !showtime_id.equals(that.showtime_id()) : that.showtime_id() != null) {
        return false;
      }
      if (movie_id != null ? !movie_id.equals(that.movie_id()) : that.movie_id() != null) {
        return false;
      }
      if (date != null ? !date.equals(that.date()) : that.date() != null) {
        return false;
      }
      if (time != null ? !time.equals(that.time()) : that.time() != null) {
        return false;
      }
      if (type != null ? !type.equals(that.type()) : that.type() != null) {
        return false;
      }
      if (theater_id != null ? !theater_id.equals(that.theater_id()) : that.theater_id() != null) {
        return false;
      }
      if (seats != null ? !seats.equals(that.seats()) : that.seats() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.showtime_id != null ? this.showtime_id.hashCode() : 0);
      result = 31 * result + (this.movie_id != null ? this.movie_id.hashCode() : 0);
      result = 31 * result + (this.date != null ? this.date.hashCode() : 0);
      result = 31 * result + (this.time != null ? this.time.hashCode() : 0);
      result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
      result = 31 * result + (this.theater_id != null ? this.theater_id.hashCode() : 0);
      result = 31 * result + (this.seats != null ? this.seats.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "Showtime{" +
      "showtime_id=" + showtime_id +
      ", movie_id=" + movie_id +
      ", date=" + date +
      ", time=" + time +
      ", type=" + type +
      ", theater_id=" + theater_id +
      ", seats=" + seats +
      '}';
    }
  }
}
