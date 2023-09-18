package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Member extends BaseEntity{

  @Id @GeneratedValue
  @Column(name = "MEMBER_ID ")
  private Long id;
  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<Order>();
  @Column(length = 10)
  private String name;
  private String city;
  private String street;
  private String zipcode;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCity() {
    return city;
  }

  public String getStreet() {
    return street;
  }

  public String getZipcode() {
    return zipcode;
  }

  public static void main(String[] args) {
    System.out.println("Hello world!");
  }
}