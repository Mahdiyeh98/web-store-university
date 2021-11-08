package ir.online.bookstore.dao;

import ir.online.bookstore.domain.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookDAO extends JpaRepository<Books, Long> {
    Optional<Books> findByISBN(String isbn);

    List<Books> findByName(String name);

    Optional<Books> findByPublisherName(String publisherName);

    /*Optional<Books> findByBookCategories(List<BookCategory> bookCategories);
    Optional<Books> findByAuthorBooks (List<AuthorBook> authorBooks);*/
//    Optional<Books> findByNameAndISBNAndPublisherName(String input);
//    Optional<Books> findByISBNContaining;
}

