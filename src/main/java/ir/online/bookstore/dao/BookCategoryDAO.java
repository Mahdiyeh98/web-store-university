package ir.online.bookstore.dao;

import ir.online.bookstore.domain.BookCategory;
import ir.online.bookstore.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookCategoryDAO extends JpaRepository<BookCategory, Long> {
    Optional<List<BookCategory>> findByBooks_ISBN(String isbn);
}
