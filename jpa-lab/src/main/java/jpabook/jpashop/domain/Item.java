package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public class Item extends BaseEntity{
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
