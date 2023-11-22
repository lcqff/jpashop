package jpabook.jpashop.domain.items;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jpabook.jpashop.domain.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("b")
@Getter @Setter
public class Book extends Item {
    private String author;
    private String isbn;
}

