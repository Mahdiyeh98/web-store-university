package ir.online.bookstore.dao;

import ir.online.bookstore.domain.AuthorBook;
import ir.online.bookstore.domain.Authors;
import ir.online.bookstore.domain.Books;
import ir.online.bookstore.exception.BookNotFoundException;
import ir.online.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorBookDAO extends JpaRepository<AuthorBook, Long> {
    @Autowired
    static final BookService bookService = null;

    Optional<AuthorBook> findByBooks(Books book);

    Optional<List<AuthorBook>> findByBooks_ISBN(String isbn);


    Optional<AuthorBook> findByAuthors(Authors authors);

    Optional<List<AuthorBook>> findByAuthors_Name(String name);

    Optional<List<AuthorBook>> findByAuthors_LastName(String lastName);

    //    Optional<List<AuthorBook>> findByBooks_ISBN(String isbn);
    Optional<List<AuthorBook>> findByBooks_Name(String name);

    Optional<List<AuthorBook>> findByBooks_PublisherName(String publisherName);

//    default Optional<List<Authors>> findByBooks_ISBN(String isbn) {
//        return null;
//    }

    /*default */Authors getAuthorsByBooks_ISBN(String isbn);/* throws NoSuchMethodException {
        Optional bookByISBN = bookService.getBookByISBN(isbn);
        Books book = (Books) bookByISBN.get();
        Optional<AuthorBook> authorBook = findByBooks(book);
        Authors author = authorBook.get().getAuthors();
        return author;
    }*/
}
