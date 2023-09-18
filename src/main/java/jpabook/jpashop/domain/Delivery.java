package jpabook.jpashop.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery {
  @Id @GeneratedValue
  @Column(name = "DELIVERY_ID")
  private Long id;
  @OneToOne(mappedBy = "delivery")
  private Order order;
  private String City;
  private String street;
  private String zipcode;
  @Enumerated(EnumType.STRING)
  private DeliveryStatus status;
}
