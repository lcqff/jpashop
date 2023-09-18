package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Item {
  @Id @GeneratedValue
  @Column(name = "ITEM_ID ")
  private Long id;
  @ManyToMany(mappedBy = "items")
  private List<Category> categories = new ArrayList<>();
  private String name;
  private int price;
  private int stockQuantity;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public int getStockQuantity() {
    return stockQuantity;
  }
}
