package ir.online.bookstore.dao;


import ir.online.bookstore.domain.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorDAO extends JpaRepository<Authors, Long> {
    Optional<List<Authors>> findByName(String name);

    Optional<List<Authors>> findByLastName(String lastName);

    Optional<List<Authors>> findByNameContainingOrLastNameContaining(String name, String lastName);

//    Optional<Authors> findByAuthorBooks(AuthorBook authorBook);

}
