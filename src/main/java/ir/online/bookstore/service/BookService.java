package ir.online.bookstore.service;

import ir.online.bookstore.dao.BookDAO;
import ir.online.bookstore.domain.Books;
import ir.online.bookstore.exception.AuthorNotFoundException;
import ir.online.bookstore.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookDAO bookDAO;

    @Autowired
    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    //CREATE-----------------------------------------------------------------------------------------------
    public Books addBook(Books book) {

        return bookDAO.save(book);
    }


   //READ-------------------------------------------------------------------------------------------------

    public List<Books> getBooksList() {
        return bookDAO.findAll();
    }

    public Optional<Books> getBookById(Long id) {
        return Optional.ofNullable(bookDAO.findById(id).orElseThrow(() ->
                new AuthorNotFoundException(String.valueOf(id))));
    }

    public Optional<Books> getBookByISBN(String isbn) {
        return Optional.ofNullable(bookDAO.findByISBN(isbn)).orElseThrow(() ->
                new BookNotFoundException(isbn));
    }

    public List<Books> getBookByName(String name) {
        return bookDAO.findByName(name);
    }

    public Books getBookByPublisher(String publisherName) {
        Optional<Books> byPublisherName = bookDAO.findByPublisherName(publisherName);
        return byPublisherName.orElseThrow(() ->
                new BookNotFoundException(publisherName));
    }

    public Books getBookByAuthors(String authorName) { // TODO -> toye dto bayad dorost beshe
        return new Books();
    }

    public Books getBookByCategory(String category) { // TODO -> toye dto bayad dorost beshe
        return new Books();
    }

    //DELETE-----------------------------------------------------------------------------------------------
    public Books deleteBookByISBN(String isbn) {
        Books bookByISBN = (Books) getBookByISBN(isbn).get();
        bookDAO.delete(bookByISBN);
        return bookByISBN;
    }


    public Books deleteBookByPublisher(String publisherName) {
        Books book = getBookByPublisher(publisherName);
        bookDAO.delete(book);
        return book;
    }

    public Books deleteBookByCategory(String category) {
        Books book = getBookByCategory(category);
        bookDAO.delete(book);
        return book;
    }

    public Books deleteBookByAuthor(String author) {
        Books book = getBookByAuthors(author);
        bookDAO.delete(book);
        return book;
    }
    //UPDATE-----------------------------------------------------------------------------------------------

    @Transactional
    public Optional<Books> updateBookById(Long id, Books book) {
        Optional<Books> bookByISBN = getBookById(id);
        Books bookToEdit = bookByISBN.get();

        if (book.getName() != null) {
            bookToEdit.setName(book.getName());
        }
        if (book.getPrice() != null && book.getPrice() > 1000) {
            bookToEdit.setPrice(book.getPrice());
        }
        if (book.getPublisherName() != null) {
            bookToEdit.setPublisherName(book.getPublisherName());
        }
        if (book.getYear() > 1300) {
            bookToEdit.setYear(book.getYear());
        }
        if (book.getPicture() != null) {
            bookToEdit.setPicture(book.getPicture());
        }
        if (book.getDescription() != null) {
            bookToEdit.setDescription(book.getDescription());
        }
        if (book.getQuantity() > 0) {
            bookToEdit.setQuantity(book.getQuantity());
        }

        return Optional.of(bookDAO.save(bookToEdit));
    }

    @Transactional
    public Optional<Books> updateBookByIsbn(String isbn, Books book) {
        Optional<Books> bookByISBN = getBookByISBN(isbn);
        Books bookToEdit = bookByISBN.get();

        if (book.getName() != null) {
            bookToEdit.setName(book.getName());
        }
        if (book.getPrice() != null && book.getPrice() > 1000) {
            bookToEdit.setPrice(book.getPrice());
        }
        if (book.getPublisherName() != null) {
            bookToEdit.setPublisherName(book.getPublisherName());
        }
        if (book.getYear() > 1300) {
            bookToEdit.setYear(book.getYear());
        }
        if (book.getPicture() != null) {
            bookToEdit.setPicture(book.getPicture());
        }
        if (book.getDescription() != null) {
            bookToEdit.setDescription(book.getDescription());
        }
        if (book.getQuantity() > 0) {
            bookToEdit.setQuantity(book.getQuantity());
        }

        return Optional.of(bookDAO.save(bookToEdit));
    }


}
