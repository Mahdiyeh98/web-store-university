package ir.online.bookstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.online.bookstore.domain.*;
import ir.online.bookstore.dto.BooksOutputDTO;
import ir.online.bookstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/search")
public class Search {
    @Autowired
    BookCategoryService bookCategoryService;
    @Autowired
    AuthorBookService authorBookService;
    @Autowired
    CategoriesService categoriesService;
    @Autowired
    AuthorService authorService;
    @Autowired
    BookService bookService;

    @GetMapping("/byCategoryName")
    public ResponseEntity<String> searchByCategory(@RequestParam String categoryName) throws JsonProcessingException {
        if (categoriesService.getCategoriesList(categoryName).isEmpty()) {
            return ResponseEntity.ok("not found category");

        }else {
        List<Categories> categoriesList = categoriesService.getCategoriesList(categoryName);
        Set<String> IsbnSet = new HashSet<>();
        for (int i = 0; i < categoriesList.size(); i++) {
            List<BookCategory> bookCategories = categoriesList.get(i).getBookCategories();
            for (int j = 0; j < bookCategories.size(); j++) {
                IsbnSet.add(bookCategories.get(j).getBooks().getISBN());
            }
        }
        if (IsbnSet.size() == 0) {
            return ResponseEntity.ok("not found anythings");
        } else {
            List<BooksOutputDTO> booksOutputDTOList = printBooksByIsbn(IsbnSet);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return ResponseEntity.ok(objectMapper.writeValueAsString(booksOutputDTOList));
        }
    }}

    @GetMapping("/byCategoryId")
    public ResponseEntity<String> searchById(@RequestParam Long id) throws JsonProcessingException {
        if (!categoriesService.getCategoriesById(id).isPresent()) {
            return ResponseEntity.ok("not found category");

        }else {
        Categories categories = categoriesService.getCategoriesById(id).get();
        Set<String> IsbnSet = new HashSet<>();
        List<BookCategory> bookCategories = categories.getBookCategories();
        for (int i = 0; i < bookCategories.size(); i++) {
            IsbnSet.add(bookCategories.get(i).getBooks().getISBN());
        }
        if (IsbnSet.size() == 0) {
            return ResponseEntity.ok("not found anythings");
        } else {
            List<BooksOutputDTO> booksOutputDTOList = printBooksByIsbn(IsbnSet);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return ResponseEntity.ok(objectMapper.writeValueAsString(booksOutputDTOList));
        }
    }}

    @GetMapping("/byBookNameAndAuthorName")
    public ResponseEntity<String> searchByBookNameAndAuthorName(@RequestParam String name) throws JsonProcessingException {

        Set<String> IsbnSet = new HashSet<>();
        List<Books> books;
        List<Authors> authorsByName;

        if (!bookService.getBookByName(name).isEmpty()) {
            books = bookService.getBookByName(name);
            for (Books booksLoop : books) {
                IsbnSet.add(booksLoop.getISBN());
            }
        }

        if (authorService.getAuthorsByName(name).isPresent()) {
            authorsByName = authorService.getAuthorsByName(name).get();
            for (int i = 0; i < authorsByName.size(); i++) {
                IsbnSet.add(authorsByName.get(i).getAuthorBooks().get(i).getBooks().getISBN());
            }
        }
        if (IsbnSet.size() == 0) {
            return ResponseEntity.ok("not found anythings");
        } else {
            List<BooksOutputDTO> booksOutputDTOList = printBooksByIsbn(IsbnSet);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return ResponseEntity.ok(objectMapper.writeValueAsString(booksOutputDTOList));
        }
    }


    public List<BooksOutputDTO> printBooksByIsbn(Set<String> IsbnSet) {

        List<BooksOutputDTO> booksOutputDTOList = new ArrayList<>();

        for (String item : IsbnSet) {

            BooksOutputDTO booksOutputDTO = new BooksOutputDTO();

            List<BookCategory> bookCategory = bookCategoryService.getBookCategoryByIsbn(item);
            List<AuthorBook> authorBook = authorBookService.getAuthorBookByIsbn(item);

            booksOutputDTO.setBooks(bookCategory.get(0).getBooks());

            List<Authors> authorsList = new ArrayList<>();
            List<Categories> categoriesList = new ArrayList<>();

            for (BookCategory items : bookCategory) {
                categoriesList.add(items.getCategories());
            }
            for (AuthorBook author : authorBook) {
                authorsList.add(author.getAuthors());
            }
            booksOutputDTO.setAuthors(authorsList);
            booksOutputDTO.setCategories(categoriesList);
            booksOutputDTOList.add(booksOutputDTO);
        }
        return booksOutputDTOList;
    }
}
