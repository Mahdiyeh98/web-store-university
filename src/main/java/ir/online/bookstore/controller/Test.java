package ir.online.bookstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.online.bookstore.dao.*;
import ir.online.bookstore.domain.*;
import ir.online.bookstore.dto.BooksOutputDTO;
import ir.online.bookstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/test")
public class Test {
    @Autowired
    BookDAO bookDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    AuthorDAO authorDAO;

    @Autowired
    AuthorBookDAO authorBookDAO;

    @Autowired
    BookCategoryDAO bookCategoryDAO;

    @Autowired
    AuthorBookService authorBookService;
    @Autowired
    BookCategoryService bookCategoryService;

    @GetMapping
    void contextLoads() throws JsonProcessingException {
        //------------ book ------------
        Books books = new Books();
        books.setName("test");
        books.setDescription("test asdg usay gduy asgdiuasgd y");
        books.setPrice((long) 24352);
        books.setISBN("123456789");
        books.setYear(1378);
        books.setQuantity(111);

        bookDAO.save(books);

        //------------ categories ------------
        Categories computer = new Categories();
        computer.setName("computer");

        Categories math = new Categories();
        math.setName("math");

        Categories religious = new Categories();
        religious.setName("religious");

        categoryDAO.saveAll(Arrays.asList(computer,
                math, religious));

        //------------ authors ------------
        Authors mehdiYasinzadeh = new Authors();
        mehdiYasinzadeh.setName("مهدی");
        mehdiYasinzadeh.setLastName("یاسین زاده");

        Authors aliZare = new Authors();
        aliZare.setName("علی");
        aliZare.setLastName("زارع");

        Authors mahdiyeRezaee = new Authors();
        mahdiyeRezaee.setName("مهدیه");
        mahdiyeRezaee.setLastName("رضایی");

        authorDAO.saveAll(Arrays.asList(mehdiYasinzadeh,aliZare,mahdiyeRezaee));

        //------------ save ------------
        BookCategory bookCategory1 = new BookCategory();
        bookCategory1.setBooks(books);
        bookCategory1.setCategories(computer);
        bookCategoryDAO.save(bookCategory1);

        BookCategory bookCategory2 = new BookCategory();
        bookCategory2.setBooks(books);
        bookCategory2.setCategories(math);
        bookCategoryDAO.save(bookCategory2);

        BookCategory bookCategory3 = new BookCategory();
        bookCategory3.setBooks(books);
        bookCategory3.setCategories(religious);
        bookCategoryDAO.save(bookCategory3);

        AuthorBook authorBook1=new AuthorBook();
        authorBook1.setBooks(books);
        authorBook1.setAuthors(mahdiyeRezaee);
        authorBookDAO.save(authorBook1);

        AuthorBook authorBook2=new AuthorBook();
        authorBook2.setBooks(books);
        authorBook2.setAuthors(mehdiYasinzadeh);
        authorBookDAO.save(authorBook2);

        AuthorBook authorBook3=new AuthorBook();
        authorBook3.setBooks(books);
        authorBook3.setAuthors(aliZare);
        authorBookDAO.save(authorBook3);

    }
}