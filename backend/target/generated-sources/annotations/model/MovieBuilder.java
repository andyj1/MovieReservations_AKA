package model;

import com.mongodb.lang.Nullable;
import io.norberg.automatter.AutoMatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class MovieBuilder {
  private String movie_id;

  private String title;

  private String consensus;

  private String critic_rating;

  private Integer critic_count;

  private Integer audience_count;

  private String description;

  private String rating;

  private List<String> genre;

  private List<String> director;

  private List<String> writer;

  private String air_date;

  private String runtime;

  private String studio;

  public MovieBuilder() {
  }

  private MovieBuilder(Movie v) {
    this.movie_id = v.movie_id();
    this.title = v.title();
    this.consensus = v.consensus();
    this.critic_rating = v.critic_rating();
    this.critic_count = v.critic_count();
    this.audience_count = v.audience_count();
    this.description = v.description();
    this.rating = v.rating();
    List<String> _genre = v.genre();
    this.genre = (_genre == null) ? null : new ArrayList<String>(_genre);
    List<String> _director = v.director();
    this.director = (_director == null) ? null : new ArrayList<String>(_director);
    List<String> _writer = v.writer();
    this.writer = (_writer == null) ? null : new ArrayList<String>(_writer);
    this.air_date = v.air_date();
    this.runtime = v.runtime();
    this.studio = v.studio();
  }

  private MovieBuilder(MovieBuilder v) {
    this.movie_id = v.movie_id;
    this.title = v.title;
    this.consensus = v.consensus;
    this.critic_rating = v.critic_rating;
    this.critic_count = v.critic_count;
    this.audience_count = v.audience_count;
    this.description = v.description;
    this.rating = v.rating;
    this.genre = (v.genre == null) ? null : new ArrayList<String>(v.genre);
    this.director = (v.director == null) ? null : new ArrayList<String>(v.director);
    this.writer = (v.writer == null) ? null : new ArrayList<String>(v.writer);
    this.air_date = v.air_date;
    this.runtime = v.runtime;
    this.studio = v.studio;
  }

  public String movie_id() {
    return movie_id;
  }

  public MovieBuilder movie_id(String movie_id) {
    if (movie_id == null) {
      throw new NullPointerException("movie_id");
    }
    this.movie_id = movie_id;
    return this;
  }

  public String title() {
    return title;
  }

  public MovieBuilder title(String title) {
    if (title == null) {
      throw new NullPointerException("title");
    }
    this.title = title;
    return this;
  }

  public String consensus() {
    return consensus;
  }

  public MovieBuilder consensus(@Nullable String consensus) {
    this.consensus = consensus;
    return this;
  }

  public String critic_rating() {
    return critic_rating;
  }

  public MovieBuilder critic_rating(@Nullable String critic_rating) {
    this.critic_rating = critic_rating;
    return this;
  }

  public Integer critic_count() {
    return critic_count;
  }

  public MovieBuilder critic_count(@Nullable Integer critic_count) {
    this.critic_count = critic_count;
    return this;
  }

  public Integer audience_count() {
    return audience_count;
  }

  public MovieBuilder audience_count(@Nullable Integer audience_count) {
    this.audience_count = audience_count;
    return this;
  }

  public String description() {
    return description;
  }

  public MovieBuilder description(@Nullable String description) {
    this.description = description;
    return this;
  }

  public String rating() {
    return rating;
  }

  public MovieBuilder rating(@Nullable String rating) {
    this.rating = rating;
    return this;
  }

  public List<String> genre() {
    return genre;
  }

  public MovieBuilder genre(List<? extends String> genre) {
    return genre((Collection<? extends String>) genre);
  }

  public MovieBuilder genre(Collection<? extends String> genre) {
    if (genre == null) {
      this.genre = null;
      return this;
    }
    this.genre = new ArrayList<String>(genre);
    return this;
  }

  public MovieBuilder genre(Iterable<? extends String> genre) {
    if (genre == null) {
      this.genre = null;
      return this;
    }
    if (genre instanceof Collection) {
      return genre((Collection<? extends String>) genre);
    }
    return genre(genre.iterator());
  }

  public MovieBuilder genre(Iterator<? extends String> genre) {
    if (genre == null) {
      this.genre = null;
      return this;
    }
    this.genre = new ArrayList<String>();
    while (genre.hasNext()) {
      String item = genre.next();
      this.genre.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final MovieBuilder genre(String... genre) {
    if (genre == null) {
      this.genre = null;
      return this;
    }
    return genre(Arrays.asList(genre));
  }

  public List<String> director() {
    return director;
  }

  public MovieBuilder director(List<? extends String> director) {
    return director((Collection<? extends String>) director);
  }

  public MovieBuilder director(Collection<? extends String> director) {
    if (director == null) {
      this.director = null;
      return this;
    }
    this.director = new ArrayList<String>(director);
    return this;
  }

  public MovieBuilder director(Iterable<? extends String> director) {
    if (director == null) {
      this.director = null;
      return this;
    }
    if (director instanceof Collection) {
      return director((Collection<? extends String>) director);
    }
    return director(director.iterator());
  }

  public MovieBuilder director(Iterator<? extends String> director) {
    if (director == null) {
      this.director = null;
      return this;
    }
    this.director = new ArrayList<String>();
    while (director.hasNext()) {
      String item = director.next();
      this.director.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final MovieBuilder director(String... director) {
    if (director == null) {
      this.director = null;
      return this;
    }
    return director(Arrays.asList(director));
  }

  public List<String> writer() {
    return writer;
  }

  public MovieBuilder writer(List<? extends String> writer) {
    return writer((Collection<? extends String>) writer);
  }

  public MovieBuilder writer(Collection<? extends String> writer) {
    if (writer == null) {
      this.writer = null;
      return this;
    }
    this.writer = new ArrayList<String>(writer);
    return this;
  }

  public MovieBuilder writer(Iterable<? extends String> writer) {
    if (writer == null) {
      this.writer = null;
      return this;
    }
    if (writer instanceof Collection) {
      return writer((Collection<? extends String>) writer);
    }
    return writer(writer.iterator());
  }

  public MovieBuilder writer(Iterator<? extends String> writer) {
    if (writer == null) {
      this.writer = null;
      return this;
    }
    this.writer = new ArrayList<String>();
    while (writer.hasNext()) {
      String item = writer.next();
      this.writer.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final MovieBuilder writer(String... writer) {
    if (writer == null) {
      this.writer = null;
      return this;
    }
    return writer(Arrays.asList(writer));
  }

  public String air_date() {
    return air_date;
  }

  public MovieBuilder air_date(@Nullable String air_date) {
    this.air_date = air_date;
    return this;
  }

  public String runtime() {
    return runtime;
  }

  public MovieBuilder runtime(@Nullable String runtime) {
    this.runtime = runtime;
    return this;
  }

  public String studio() {
    return studio;
  }

  public MovieBuilder studio(@Nullable String studio) {
    this.studio = studio;
    return this;
  }

  public Movie build() {
    List<String> _genre = (genre != null) ? Collections.unmodifiableList(new ArrayList<String>(genre)) : null;
    List<String> _director = (director != null) ? Collections.unmodifiableList(new ArrayList<String>(director)) : null;
    List<String> _writer = (writer != null) ? Collections.unmodifiableList(new ArrayList<String>(writer)) : null;
    return new Value(movie_id, title, consensus, critic_rating, critic_count, audience_count, description, rating, _genre, _director, _writer, air_date, runtime, studio);
  }

  public static MovieBuilder from(Movie v) {
    return new MovieBuilder(v);
  }

  public static MovieBuilder from(MovieBuilder v) {
    return new MovieBuilder(v);
  }

  private static final class Value implements Movie {
    private final String movie_id;

    private final String title;

    private final String consensus;

    private final String critic_rating;

    private final Integer critic_count;

    private final Integer audience_count;

    private final String description;

    private final String rating;

    private final List<String> genre;

    private final List<String> director;

    private final List<String> writer;

    private final String air_date;

    private final String runtime;

    private final String studio;

    private Value(@AutoMatter.Field("movie_id") String movie_id,
        @AutoMatter.Field("title") String title, @AutoMatter.Field("consensus") String consensus,
        @AutoMatter.Field("critic_rating") String critic_rating,
        @AutoMatter.Field("critic_count") Integer critic_count,
        @AutoMatter.Field("audience_count") Integer audience_count,
        @AutoMatter.Field("description") String description,
        @AutoMatter.Field("rating") String rating, @AutoMatter.Field("genre") List<String> genre,
        @AutoMatter.Field("director") List<String> director,
        @AutoMatter.Field("writer") List<String> writer,
        @AutoMatter.Field("air_date") String air_date, @AutoMatter.Field("runtime") String runtime,
        @AutoMatter.Field("studio") String studio) {
      if (movie_id == null) {
        throw new NullPointerException("movie_id");
      }
      if (title == null) {
        throw new NullPointerException("title");
      }
      this.movie_id = movie_id;
      this.title = title;
      this.consensus = consensus;
      this.critic_rating = critic_rating;
      this.critic_count = critic_count;
      this.audience_count = audience_count;
      this.description = description;
      this.rating = rating;
      this.genre = genre;
      this.director = director;
      this.writer = writer;
      this.air_date = air_date;
      this.runtime = runtime;
      this.studio = studio;
    }

    @AutoMatter.Field
    @Override
    public String movie_id() {
      return movie_id;
    }

    @AutoMatter.Field
    @Override
    public String title() {
      return title;
    }

    @AutoMatter.Field
    @Override
    public String consensus() {
      return consensus;
    }

    @AutoMatter.Field
    @Override
    public String critic_rating() {
      return critic_rating;
    }

    @AutoMatter.Field
    @Override
    public Integer critic_count() {
      return critic_count;
    }

    @AutoMatter.Field
    @Override
    public Integer audience_count() {
      return audience_count;
    }

    @AutoMatter.Field
    @Override
    public String description() {
      return description;
    }

    @AutoMatter.Field
    @Override
    public String rating() {
      return rating;
    }

    @AutoMatter.Field
    @Override
    public List<String> genre() {
      return genre;
    }

    @AutoMatter.Field
    @Override
    public List<String> director() {
      return director;
    }

    @AutoMatter.Field
    @Override
    public List<String> writer() {
      return writer;
    }

    @AutoMatter.Field
    @Override
    public String air_date() {
      return air_date;
    }

    @AutoMatter.Field
    @Override
    public String runtime() {
      return runtime;
    }

    @AutoMatter.Field
    @Override
    public String studio() {
      return studio;
    }

    public MovieBuilder builder() {
      return new MovieBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Movie)) {
        return false;
      }
      final Movie that = (Movie) o;
      if (movie_id != null ? !movie_id.equals(that.movie_id()) : that.movie_id() != null) {
        return false;
      }
      if (title != null ? !title.equals(that.title()) : that.title() != null) {
        return false;
      }
      if (consensus != null ? !consensus.equals(that.consensus()) : that.consensus() != null) {
        return false;
      }
      if (critic_rating != null ? !critic_rating.equals(that.critic_rating()) : that.critic_rating() != null) {
        return false;
      }
      if (critic_count != null ? !critic_count.equals(that.critic_count()) : that.critic_count() != null) {
        return false;
      }
      if (audience_count != null ? !audience_count.equals(that.audience_count()) : that.audience_count() != null) {
        return false;
      }
      if (description != null ? !description.equals(that.description()) : that.description() != null) {
        return false;
      }
      if (rating != null ? !rating.equals(that.rating()) : that.rating() != null) {
        return false;
      }
      if (genre != null ? !genre.equals(that.genre()) : that.genre() != null) {
        return false;
      }
      if (director != null ? !director.equals(that.director()) : that.director() != null) {
        return false;
      }
      if (writer != null ? !writer.equals(that.writer()) : that.writer() != null) {
        return false;
      }
      if (air_date != null ? !air_date.equals(that.air_date()) : that.air_date() != null) {
        return false;
      }
      if (runtime != null ? !runtime.equals(that.runtime()) : that.runtime() != null) {
        return false;
      }
      if (studio != null ? !studio.equals(that.studio()) : that.studio() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.movie_id != null ? this.movie_id.hashCode() : 0);
      result = 31 * result + (this.title != null ? this.title.hashCode() : 0);
      result = 31 * result + (this.consensus != null ? this.consensus.hashCode() : 0);
      result = 31 * result + (this.critic_rating != null ? this.critic_rating.hashCode() : 0);
      result = 31 * result + (this.critic_count != null ? this.critic_count.hashCode() : 0);
      result = 31 * result + (this.audience_count != null ? this.audience_count.hashCode() : 0);
      result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
      result = 31 * result + (this.rating != null ? this.rating.hashCode() : 0);
      result = 31 * result + (this.genre != null ? this.genre.hashCode() : 0);
      result = 31 * result + (this.director != null ? this.director.hashCode() : 0);
      result = 31 * result + (this.writer != null ? this.writer.hashCode() : 0);
      result = 31 * result + (this.air_date != null ? this.air_date.hashCode() : 0);
      result = 31 * result + (this.runtime != null ? this.runtime.hashCode() : 0);
      result = 31 * result + (this.studio != null ? this.studio.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "Movie{" +
      "movie_id=" + movie_id +
      ", title=" + title +
      ", consensus=" + consensus +
      ", critic_rating=" + critic_rating +
      ", critic_count=" + critic_count +
      ", audience_count=" + audience_count +
      ", description=" + description +
      ", rating=" + rating +
      ", genre=" + genre +
      ", director=" + director +
      ", writer=" + writer +
      ", air_date=" + air_date +
      ", runtime=" + runtime +
      ", studio=" + studio +
      '}';
    }
  }
}
