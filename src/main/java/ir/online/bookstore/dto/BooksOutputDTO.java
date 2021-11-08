package ir.online.bookstore.dto;

import ir.online.bookstore.domain.Authors;
import ir.online.bookstore.domain.Books;
import ir.online.bookstore.domain.Categories;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BooksOutputDTO implements Serializable {
    private  Books books;
    private  List<Authors> authors;
    private  List<Categories> categories;
}
