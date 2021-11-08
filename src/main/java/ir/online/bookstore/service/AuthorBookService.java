
package ir.online.bookstore.service;

import ir.online.bookstore.dao.AuthorBookDAO;
import ir.online.bookstore.domain.AuthorBook;
import ir.online.bookstore.exception.AuthorBookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorBookService {
    private final AuthorBookDAO authorBookDAO;

    @Autowired
    public AuthorBookService(AuthorBookDAO authorBookDAO) {
        this.authorBookDAO = authorBookDAO;
    }

    public AuthorBook addAuthorBook(AuthorBook authorBook) {
        return authorBookDAO.save(authorBook);
    }

    public List<AuthorBook> getAuthorBookList() {
        return authorBookDAO.findAll();
    }

    public AuthorBook getAuthorBookById(Long id) {
        return authorBookDAO.findById(id).orElseThrow(() ->
                new AuthorBookNotFoundException(String.valueOf(id)));
    }

    public List<AuthorBook> getAuthorBookByIsbn(String isbn) {
        return authorBookDAO.findByBooks_ISBN(isbn).get();
    }
    public void deleteByISBN(String ISBN){
        List<AuthorBook> authorBookByIsbn = getAuthorBookByIsbn(ISBN);
        for (AuthorBook authorBook:authorBookByIsbn){
            authorBookDAO.delete(authorBook);

        }
    }
}

