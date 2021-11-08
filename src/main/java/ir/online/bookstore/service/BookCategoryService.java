package ir.online.bookstore.service;


import ir.online.bookstore.dao.BookCategoryDAO;
import ir.online.bookstore.domain.BookCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryService {
    private final BookCategoryDAO bookCategoryDAO;

    @Autowired
    public BookCategoryService(BookCategoryDAO bookCategoryDAO) {
        this.bookCategoryDAO = bookCategoryDAO;
    }

    public BookCategory addBookCategory(BookCategory BookCategory) {
        return bookCategoryDAO.save(BookCategory);
    }

    public List<BookCategory> getBookCategoryByIsbn(String isbn) {
        return bookCategoryDAO.findByBooks_ISBN(isbn).get();
    }

    public List<BookCategory> getAllBookCategory(){
        return bookCategoryDAO.findAll();
    }

    public void deleteByISBN(String ISBN){
        List<BookCategory> bookCategoryByIsbn = getBookCategoryByIsbn(ISBN);
        for (BookCategory bookCategory :bookCategoryByIsbn){
            bookCategoryDAO.delete(bookCategory);
        }
    }
}
