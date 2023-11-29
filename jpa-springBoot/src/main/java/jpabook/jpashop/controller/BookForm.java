package jpabook.jpashop.controller;

import jpabook.jpashop.domain.items.Book;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BookForm {
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;


    public BookForm(Long id, String name, int price, int stockQuantity, String author, String isbn) {
    }

    public static BookForm from(Book book) {
        return new BookForm(
                book.getId(),
                book.getName(),
                book.getPrice(),
                book.getStockQuantity(),
                book.getAuthor(),
                book.getIsbn()
        );
    }

}
