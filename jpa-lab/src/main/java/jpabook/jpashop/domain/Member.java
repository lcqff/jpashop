package jpabook.jpashop.domain;

import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Member{
  @Id @GeneratedValue
  @Column(name = "MEMBER_ID ")
  private Long id;
  @Column(length = 10)
  private String username;
  private int age;
  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<Order>();
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "TEAM_ID")
  private Team team;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public void addTeam(Team team) {
    this.team = team;
    team.getMember().add(this);
  }
}