package jpabook.jpashop.domain;

import static javax.persistence.FetchType.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import net.bytebuddy.dynamic.TypeResolutionStrategy.Lazy;

@Entity
@Table(name = "Orders")
public class Order{
  @Id @GeneratedValue
  private Long id;
  @ManyToOne(cascade = CascadeType.ALL, fetch = LAZY)
  @JoinColumn(name = "PRODUCT_ID")
  private Product product;
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "ORDER_ID")
  private Member member;
  private int orderAmount;
  @Embedded
  private Address address;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public int getOrderAmount() {
    return orderAmount;
  }

  public void setOrderAmount(int orderAmount) {
    this.orderAmount = orderAmount;
  }

  public void addOrder(Member member) {
    this.member = member;
    member.getOrders().add(this);
  }

}
