package ir.online.bookstore.dao;

import ir.online.bookstore.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDAO extends JpaRepository<Categories,Long> {
    List<Categories> findByName(String name);
}
