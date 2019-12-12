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
public final class TheaterBuilder {
  private String theater_id;

  private List<String> theater_address;

  private String theater_phone;

  private String admin_id;

  public TheaterBuilder() {
  }

  private TheaterBuilder(Theater v) {
    this.theater_id = v.theater_id();
    List<String> _theater_address = v.theater_address();
    this.theater_address = (_theater_address == null) ? null : new ArrayList<String>(_theater_address);
    this.theater_phone = v.theater_phone();
    this.admin_id = v.admin_id();
  }

  private TheaterBuilder(TheaterBuilder v) {
    this.theater_id = v.theater_id;
    this.theater_address = (v.theater_address == null) ? null : new ArrayList<String>(v.theater_address);
    this.theater_phone = v.theater_phone;
    this.admin_id = v.admin_id;
  }

  public String theater_id() {
    return theater_id;
  }

  public TheaterBuilder theater_id(String theater_id) {
    if (theater_id == null) {
      throw new NullPointerException("theater_id");
    }
    this.theater_id = theater_id;
    return this;
  }

  public List<String> theater_address() {
    if (this.theater_address == null) {
      this.theater_address = new ArrayList<String>();
    }
    return theater_address;
  }

  public TheaterBuilder theater_address(List<? extends String> theater_address) {
    return theater_address((Collection<? extends String>) theater_address);
  }

  public TheaterBuilder theater_address(Collection<? extends String> theater_address) {
    if (theater_address == null) {
      throw new NullPointerException("theater_address");
    }
    for (String item : theater_address) {
      if (item == null) {
        throw new NullPointerException("theater_address: null item");
      }
    }
    this.theater_address = new ArrayList<String>(theater_address);
    return this;
  }

  public TheaterBuilder theater_address(Iterable<? extends String> theater_address) {
    if (theater_address == null) {
      throw new NullPointerException("theater_address");
    }
    if (theater_address instanceof Collection) {
      return theater_address((Collection<? extends String>) theater_address);
    }
    return theater_address(theater_address.iterator());
  }

  public TheaterBuilder theater_address(Iterator<? extends String> theater_address) {
    if (theater_address == null) {
      throw new NullPointerException("theater_address");
    }
    this.theater_address = new ArrayList<String>();
    while (theater_address.hasNext()) {
      String item = theater_address.next();
      if (item == null) {
        throw new NullPointerException("theater_address: null item");
      }
      this.theater_address.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final TheaterBuilder theater_address(String... theater_address) {
    if (theater_address == null) {
      throw new NullPointerException("theater_address");
    }
    return theater_address(Arrays.asList(theater_address));
  }

  public String theater_phone() {
    return theater_phone;
  }

  public TheaterBuilder theater_phone(String theater_phone) {
    if (theater_phone == null) {
      throw new NullPointerException("theater_phone");
    }
    this.theater_phone = theater_phone;
    return this;
  }

  public String admin_id() {
    return admin_id;
  }

  public TheaterBuilder admin_id(String admin_id) {
    if (admin_id == null) {
      throw new NullPointerException("admin_id");
    }
    this.admin_id = admin_id;
    return this;
  }

  public Theater build() {
    List<String> _theater_address = (theater_address != null) ? Collections.unmodifiableList(new ArrayList<String>(theater_address)) : Collections.<String>emptyList();
    return new Value(theater_id, _theater_address, theater_phone, admin_id);
  }

  public static TheaterBuilder from(Theater v) {
    return new TheaterBuilder(v);
  }

  public static TheaterBuilder from(TheaterBuilder v) {
    return new TheaterBuilder(v);
  }

  private static final class Value implements Theater {
    private final String theater_id;

    private final List<String> theater_address;

    private final String theater_phone;

    private final String admin_id;

    private Value(@AutoMatter.Field("theater_id") String theater_id,
        @AutoMatter.Field("theater_address") List<String> theater_address,
        @AutoMatter.Field("theater_phone") String theater_phone,
        @AutoMatter.Field("admin_id") String admin_id) {
      if (theater_id == null) {
        throw new NullPointerException("theater_id");
      }
      if (theater_phone == null) {
        throw new NullPointerException("theater_phone");
      }
      if (admin_id == null) {
        throw new NullPointerException("admin_id");
      }
      this.theater_id = theater_id;
      this.theater_address = (theater_address != null) ? theater_address : Collections.<String>emptyList();
      this.theater_phone = theater_phone;
      this.admin_id = admin_id;
    }

    @AutoMatter.Field
    @Override
    public String theater_id() {
      return theater_id;
    }

    @AutoMatter.Field
    @Override
    public List<String> theater_address() {
      return theater_address;
    }

    @AutoMatter.Field
    @Override
    public String theater_phone() {
      return theater_phone;
    }

    @AutoMatter.Field
    @Override
    public String admin_id() {
      return admin_id;
    }

    public TheaterBuilder builder() {
      return new TheaterBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Theater)) {
        return false;
      }
      final Theater that = (Theater) o;
      if (theater_id != null ? !theater_id.equals(that.theater_id()) : that.theater_id() != null) {
        return false;
      }
      if (theater_address != null ? !theater_address.equals(that.theater_address()) : that.theater_address() != null) {
        return false;
      }
      if (theater_phone != null ? !theater_phone.equals(that.theater_phone()) : that.theater_phone() != null) {
        return false;
      }
      if (admin_id != null ? !admin_id.equals(that.admin_id()) : that.admin_id() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.theater_id != null ? this.theater_id.hashCode() : 0);
      result = 31 * result + (this.theater_address != null ? this.theater_address.hashCode() : 0);
      result = 31 * result + (this.theater_phone != null ? this.theater_phone.hashCode() : 0);
      result = 31 * result + (this.admin_id != null ? this.admin_id.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "Theater{" +
      "theater_id=" + theater_id +
      ", theater_address=" + theater_address +
      ", theater_phone=" + theater_phone +
      ", admin_id=" + admin_id +
      '}';
    }
  }
}
