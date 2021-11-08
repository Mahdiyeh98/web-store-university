package ir.online.bookstore.controller;

import com.google.gson.Gson;
import ir.online.bookstore.dao.AuthorBookDAO;
import ir.online.bookstore.dao.BookDAO;
import ir.online.bookstore.domain.*;
import ir.online.bookstore.exception.ErrorForResponse;
import ir.online.bookstore.exception.InputExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/create")
public class CreateBook {
    @Autowired
    private Gson gson;
    @Autowired
    BookDAO bookDAO;
    @Autowired
    AuthorBookDAO authorBookDAO;
//    @Autowired
//    BookCategoryDAO bookCategoryDAO;

//    @PostMapping
//    public String creatBooks(@RequestBody ArrayList<BooksDTO> booksDTOS) throws IOException {
//        ArrayList<Books> booksArrayList = new ArrayList<>();
//        Books book1 = new Books();
//        book1.setISBN(booksDTOS.get(0).getISBN());
//        book1.setName(booksDTOS.get(0).getName());
//        book1.setPrice(booksDTOS.get(0).getPrice());
//        book1.setYear(booksDTOS.get(0).getYear());
//        book1.setPublisherName(booksDTOS.get(0).getPublisherName());
//        book1.setQuantity(booksDTOS.get(0).getQuantity());
//        book1.setDescription(booksDTOS.get(0).getDescription());
//
//        book1.setPicture(booksDTOS.get(0).getPicture());
//        booksArrayList.add(book1);
//
//        Categories categories1 = new Categories();
//        categories1.setName(booksDTOS.get(0).getCategoryName());
//
//        BookCategory bookCategory1 = new BookCategory();
//        bookCategory1.setCategory(categories1);
//        bookCategory1.setBooks(book1);
//
//        bookCategoryDAO.save(bookCategory1);
//
//        Authors authors1 = new Authors();
//        authors1.setName(booksDTOS.get(0).getAuthorName());
//        authors1.setLastName(booksDTOS.get(0).getAuthorLastName());
//
//        AuthorBook authorBook1 = new AuthorBook();
//        authorBook1.setAuthors(authors1);
//        authorBook1.setBooks(book1);
//
//        authorBookDAO.save(authorBook1);
//
//        bookDAO.saveAll(booksArrayList);
//        return "book added";
//    }

    //    curl -F "imageFile=@file1.jpg"   http://localhost:8080/users/image


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorForResponse exceptionHandler(NullPointerException e) {
        return new ErrorForResponse(400, e.getMessage() + " is empty");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorForResponse exceptionHandler(InputExistException e) {
        return new ErrorForResponse(400, e.getMessage());
    }


}
