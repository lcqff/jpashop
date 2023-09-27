package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
  @Id @GeneratedValue
  private Long id;
  @Column(length = 25)
  private String name;
  @OneToMany(mappedBy = "team",
      cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Member> members = new ArrayList<>();





















  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Member> getMember() {
    return members;
  }

  public void setMember(List<Member> member) {
    this.members = member;
  }
}
