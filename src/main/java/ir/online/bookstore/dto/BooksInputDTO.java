package ir.online.bookstore.dto;


import ir.online.bookstore.domain.Books;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BooksInputDTO implements Serializable {
    private String ISBN;
    private String name;
    private Long price;
    private int year;
    private String publisherName;
    private String description;
    private int quantity;
    private String picture;
    private List<Long> authorName;
    private List<Long> categoryName;

    public static BooksInputDTO from(Books book){
        BooksInputDTO bookDTO = new BooksInputDTO();
        bookDTO.setName(book.getName());
        bookDTO.setISBN(book.getISBN());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setYear(book.getYear());
        bookDTO.setPublisherName(book.getPublisherName());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setQuantity(book.getQuantity());
        return bookDTO;
    }
}
