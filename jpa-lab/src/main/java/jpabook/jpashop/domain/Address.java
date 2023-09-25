package jpabook.jpashop.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
  @Column(length = 10)
  private String City;
  @Column(length = 10)
  private String street;
  @Column(length = 10)
  private String zipcode;

  public Address() {}

  public Address(String city, String street, String zipcode) {
    City = city;
    this.street = street;
    this.zipcode = zipcode;
  }

  public String fullAddress() {
    return getCity() + " " + getStreet() + " " + getZipcode();
  }

  public String getCity() {
    return City;
  }

  public String getStreet() {
    return street;
  }

  public String getZipcode() {
    return zipcode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(),
        address.getStreet()) && Objects.equals(getZipcode(), address.getZipcode());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCity(), getStreet(), getZipcode());
  }
}
