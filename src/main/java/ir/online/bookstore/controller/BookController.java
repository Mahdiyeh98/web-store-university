package ir.online.bookstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import ir.online.bookstore.domain.*;
import ir.online.bookstore.dto.BooksInputDTO;
import ir.online.bookstore.dto.BooksOutputDTO;
import ir.online.bookstore.exception.InputExistException;
import ir.online.bookstore.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    Gson gson;

    private final AuthorBookService authorBookService;
    private final BookService bookService;
    private final BookCategoryService bookCategoryService;
    private final CategoriesService categoriesService;
    private final AuthorService authorService;
    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public BookController(BookService bookService,
                          AuthorService authorService,
                          CategoriesService categoriesService,
                          AuthorBookService authorBookService,
                          BookCategoryService bookCategoryService
    ) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoriesService = categoriesService;
        this.authorBookService = authorBookService;
        this.bookCategoryService = bookCategoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> addBook(@RequestBody final BooksInputDTO booksInputDTO) {
        ResponseEntity<String> stringResponseEntity;
        if (bookService.getBookByISBN(booksInputDTO.getISBN()).isPresent()) {
            stringResponseEntity = ResponseEntity.ok("book with isbn : " + booksInputDTO.getISBN() + " is exit");
        } else {
            Books books = modelMapper.map(booksInputDTO, Books.class);
            books.setAuthors(null);
            books.setBookCategories(null);
            bookService.addBook(books);


            List<Long> authorName = booksInputDTO.getAuthorName();
            List<Long> categoryName = booksInputDTO.getCategoryName();

            for (Long authorNameId : authorName) {
                AuthorBook authorBook = new AuthorBook();
                authorBook.setBooks(books);
                Authors author = authorService.getAuthorById(authorNameId).get();
                author.setAuthorBooks(null);
                authorBook.setAuthors(author);
                authorBookService.addAuthorBook(authorBook);
            }

            for (Long categoryNameId : categoryName) {
                BookCategory bookCategory = new BookCategory();
                bookCategory.setBooks(books);
                Categories categories = categoriesService.getCategoriesById(categoryNameId).get();
                categories.setBookCategories(null);
                bookCategory.setCategories(categories);
                bookCategoryService.addBookCategory(bookCategory);
            }
            stringResponseEntity = ResponseEntity.ok("book with isbn : " + books.getISBN() + " is create");
        }

        return stringResponseEntity;
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllBooks() throws JsonProcessingException {
        List<BooksOutputDTO> allBooksDto = getAllBooksDto();
        ObjectMapper objectMapper=new ObjectMapper();
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return ResponseEntity.ok(objectMapper.writeValueAsString(allBooksDto));
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateBook(@RequestBody final BooksInputDTO booksInputDTO) {
        ResponseEntity<String> stringResponseEntity;
        if (!bookService.getBookByISBN(booksInputDTO.getISBN()).isPresent()) {
            stringResponseEntity = ResponseEntity.ok("book with isbn : " + booksInputDTO.getISBN() + " is not exit");
        } else {

            Books books = modelMapper.map(booksInputDTO, Books.class);
            Long idOfBook = bookService.getBookByISBN(books.getISBN()).get().getId();
            books.setId(idOfBook);
            bookService.addBook(books);

            authorBookService.deleteByISBN(books.getISBN());
            bookCategoryService.deleteByISBN(books.getISBN());


            List<Long> authorName = booksInputDTO.getAuthorName();
            List<Long> categoryName = booksInputDTO.getCategoryName();

            for (Long authorNameId : authorName) {
                AuthorBook authorBook = new AuthorBook();
                authorBook.setBooks(books);
                Authors author = authorService.getAuthorById(authorNameId).get();
                author.setAuthorBooks(null);
                authorBook.setAuthors(author);
                authorBookService.addAuthorBook(authorBook);
            }

            for (Long categoryNameId : categoryName) {
                BookCategory bookCategory = new BookCategory();
                bookCategory.setBooks(books);
                Categories categories = categoriesService.getCategoriesById(categoryNameId).get();
                categories.setBookCategories(null);
                bookCategory.setCategories(categories);
                bookCategoryService.addBookCategory(bookCategory);
            }
            stringResponseEntity = ResponseEntity.ok("book with isbn : " + books.getISBN() + " is updated");
        }

        return stringResponseEntity;
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchBookByISBN(@RequestParam String ISBN) throws JsonProcessingException {
        BooksOutputDTO booksOutputDTO= new BooksOutputDTO();

            List<BookCategory> bookCategory = bookCategoryService.getBookCategoryByIsbn(ISBN);
            List<AuthorBook> authorBook = authorBookService.getAuthorBookByIsbn(ISBN);

            booksOutputDTO.setBooks(bookCategory.get(0).getBooks());

            List<Authors> authorsList=new ArrayList<>();
            List<Categories> categoriesList=new ArrayList<>();

            for (BookCategory items : bookCategory){
                categoriesList.add(items.getCategories());
            }
            for (AuthorBook author:authorBook){
                authorsList.add(author.getAuthors());
            }
            booksOutputDTO.setAuthors(authorsList);
            booksOutputDTO.setCategories(categoriesList);

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        return ResponseEntity.ok(objectMapper.writeValueAsString(booksOutputDTO));
    }

    @PostMapping("/image")
    public String image(@RequestParam("imageFile") MultipartFile file,
                        @RequestParam("isbn") String isbn) throws IOException, InputExistException {

        if (!bookService.getBookByISBN(isbn).isPresent()) {
            throw new InputExistException("Id is not exist");
        }

        BufferedImage image = ImageIO.read(file.getInputStream());
        ImageIO.write(image, "jpg", new FileOutputStream("src/main/webapp/img/" + isbn + ".jpg"));
        Optional<Books> byISBN = bookService.getBookByISBN(isbn);
        Books book =byISBN.get();
        book.setPicture("http://85.185.158.184/img/" + isbn + ".jpg");
        bookService.addBook(book);
        return gson.toJson("save image http://85.185.158.184/img/" + isbn + ".jpg");
    }

    public List<BooksOutputDTO>  getAllBooksDto() throws JsonProcessingException {

        Set<String> stringSet=new HashSet<>();

        List<BooksOutputDTO> booksOutputDTOList=new ArrayList<>();
        List<Books> all = bookService.getBooksList();

        for (Books item :all){
            stringSet.add(item.getISBN());
        }

        for (String item:stringSet){

            BooksOutputDTO booksOutputDTO= new BooksOutputDTO();

            List<BookCategory> bookCategory = bookCategoryService.getBookCategoryByIsbn(item);
            List<AuthorBook> authorBook = authorBookService.getAuthorBookByIsbn(item);

            booksOutputDTO.setBooks(bookCategory.get(0).getBooks());

            List<Authors> authorsList=new ArrayList<>();
            List<Categories> categoriesList=new ArrayList<>();

            for (BookCategory items : bookCategory){
                categoriesList.add(items.getCategories());
            }
            for (AuthorBook author:authorBook){
                authorsList.add(author.getAuthors());
            }
            booksOutputDTO.setAuthors(authorsList);
            booksOutputDTO.setCategories(categoriesList);
            booksOutputDTOList.add(booksOutputDTO);
        }
        return booksOutputDTOList;
    }
}
