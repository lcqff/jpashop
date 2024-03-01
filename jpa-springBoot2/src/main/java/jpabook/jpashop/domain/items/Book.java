package jpabook.jpashop.domain.items;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jpabook.jpashop.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("b")
@NoArgsConstructor
@Getter @Setter
public class Book extends Item {
    private String author;
    private String isbn;

    @Builder
    private Book(String name,int price, int stock, String author, String isbn) {
        super.setName(name);
        super.setPrice(price);
        super.setStockQuantity(stock);
        this.author = author;
        this.isbn = isbn;
    }
}

