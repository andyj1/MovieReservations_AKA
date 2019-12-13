package model;

import com.mongodb.lang.Nullable;
import io.norberg.automatter.AutoMatter;
import java.util.Date;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class UserBuilder {
  private String user_id;

  private String name;

  private String username;

  private String password;

  private String email;

  private Boolean admin;

  private Date created_at;

  private String favorite_genre;

  private String zip_code;

  public UserBuilder() {
  }

  private UserBuilder(User v) {
    this.user_id = v.user_id();
    this.name = v.name();
    this.username = v.username();
    this.password = v.password();
    this.email = v.email();
    this.admin = v.admin();
    this.created_at = v.created_at();
    this.favorite_genre = v.favorite_genre();
    this.zip_code = v.zip_code();
  }

  private UserBuilder(UserBuilder v) {
    this.user_id = v.user_id;
    this.name = v.name;
    this.username = v.username;
    this.password = v.password;
    this.email = v.email;
    this.admin = v.admin;
    this.created_at = v.created_at;
    this.favorite_genre = v.favorite_genre;
    this.zip_code = v.zip_code;
  }

  public String user_id() {
    return user_id;
  }

  public UserBuilder user_id(String user_id) {
    if (user_id == null) {
      throw new NullPointerException("user_id");
    }
    this.user_id = user_id;
    return this;
  }

  public String name() {
    return name;
  }

  public UserBuilder name(String name) {
    if (name == null) {
      throw new NullPointerException("name");
    }
    this.name = name;
    return this;
  }

  public String username() {
    return username;
  }

  public UserBuilder username(String username) {
    if (username == null) {
      throw new NullPointerException("username");
    }
    this.username = username;
    return this;
  }

  public String password() {
    return password;
  }

  public UserBuilder password(String password) {
    if (password == null) {
      throw new NullPointerException("password");
    }
    this.password = password;
    return this;
  }

  public String email() {
    return email;
  }

  public UserBuilder email(String email) {
    if (email == null) {
      throw new NullPointerException("email");
    }
    this.email = email;
    return this;
  }

  public Boolean admin() {
    return admin;
  }

  public UserBuilder admin(Boolean admin) {
    if (admin == null) {
      throw new NullPointerException("admin");
    }
    this.admin = admin;
    return this;
  }

  public Date created_at() {
    return created_at;
  }

  public UserBuilder created_at(Date created_at) {
    if (created_at == null) {
      throw new NullPointerException("created_at");
    }
    this.created_at = created_at;
    return this;
  }

  public String favorite_genre() {
    return favorite_genre;
  }

  public UserBuilder favorite_genre(@Nullable String favorite_genre) {
    this.favorite_genre = favorite_genre;
    return this;
  }

  public String zip_code() {
    return zip_code;
  }

  public UserBuilder zip_code(@Nullable String zip_code) {
    this.zip_code = zip_code;
    return this;
  }

  public User build() {
    return new Value(user_id, name, username, password, email, admin, created_at, favorite_genre, zip_code);
  }

  public static UserBuilder from(User v) {
    return new UserBuilder(v);
  }

  public static UserBuilder from(UserBuilder v) {
    return new UserBuilder(v);
  }

  private static final class Value implements User {
    private final String user_id;

    private final String name;

    private final String username;

    private final String password;

    private final String email;

    private final Boolean admin;

    private final Date created_at;

    private final String favorite_genre;

    private final String zip_code;

    private Value(@AutoMatter.Field("user_id") String user_id,
        @AutoMatter.Field("name") String name, @AutoMatter.Field("username") String username,
        @AutoMatter.Field("password") String password, @AutoMatter.Field("email") String email,
        @AutoMatter.Field("admin") Boolean admin, @AutoMatter.Field("created_at") Date created_at,
        @AutoMatter.Field("favorite_genre") String favorite_genre,
        @AutoMatter.Field("zip_code") String zip_code) {
      if (user_id == null) {
        throw new NullPointerException("user_id");
      }
      if (name == null) {
        throw new NullPointerException("name");
      }
      if (username == null) {
        throw new NullPointerException("username");
      }
      if (password == null) {
        throw new NullPointerException("password");
      }
      if (email == null) {
        throw new NullPointerException("email");
      }
      if (admin == null) {
        throw new NullPointerException("admin");
      }
      if (created_at == null) {
        throw new NullPointerException("created_at");
      }
      this.user_id = user_id;
      this.name = name;
      this.username = username;
      this.password = password;
      this.email = email;
      this.admin = admin;
      this.created_at = created_at;
      this.favorite_genre = favorite_genre;
      this.zip_code = zip_code;
    }

    @AutoMatter.Field
    @Override
    public String user_id() {
      return user_id;
    }

    @AutoMatter.Field
    @Override
    public String name() {
      return name;
    }

    @AutoMatter.Field
    @Override
    public String username() {
      return username;
    }

    @AutoMatter.Field
    @Override
    public String password() {
      return password;
    }

    @AutoMatter.Field
    @Override
    public String email() {
      return email;
    }

    @AutoMatter.Field
    @Override
    public Boolean admin() {
      return admin;
    }

    @AutoMatter.Field
    @Override
    public Date created_at() {
      return created_at;
    }

    @AutoMatter.Field
    @Override
    public String favorite_genre() {
      return favorite_genre;
    }

    @AutoMatter.Field
    @Override
    public String zip_code() {
      return zip_code;
    }

    public UserBuilder builder() {
      return new UserBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof User)) {
        return false;
      }
      final User that = (User) o;
      if (user_id != null ? !user_id.equals(that.user_id()) : that.user_id() != null) {
        return false;
      }
      if (name != null ? !name.equals(that.name()) : that.name() != null) {
        return false;
      }
      if (username != null ? !username.equals(that.username()) : that.username() != null) {
        return false;
      }
      if (password != null ? !password.equals(that.password()) : that.password() != null) {
        return false;
      }
      if (email != null ? !email.equals(that.email()) : that.email() != null) {
        return false;
      }
      if (admin != null ? !admin.equals(that.admin()) : that.admin() != null) {
        return false;
      }
      if (created_at != null ? !created_at.equals(that.created_at()) : that.created_at() != null) {
        return false;
      }
      if (favorite_genre != null ? !favorite_genre.equals(that.favorite_genre()) : that.favorite_genre() != null) {
        return false;
      }
      if (zip_code != null ? !zip_code.equals(that.zip_code()) : that.zip_code() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.user_id != null ? this.user_id.hashCode() : 0);
      result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
      result = 31 * result + (this.username != null ? this.username.hashCode() : 0);
      result = 31 * result + (this.password != null ? this.password.hashCode() : 0);
      result = 31 * result + (this.email != null ? this.email.hashCode() : 0);
      result = 31 * result + (this.admin != null ? this.admin.hashCode() : 0);
      result = 31 * result + (this.created_at != null ? this.created_at.hashCode() : 0);
      result = 31 * result + (this.favorite_genre != null ? this.favorite_genre.hashCode() : 0);
      result = 31 * result + (this.zip_code != null ? this.zip_code.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "User{" +
      "user_id=" + user_id +
      ", name=" + name +
      ", username=" + username +
      ", password=" + password +
      ", email=" + email +
      ", admin=" + admin +
      ", created_at=" + created_at +
      ", favorite_genre=" + favorite_genre +
      ", zip_code=" + zip_code +
      '}';
    }
  }
}
