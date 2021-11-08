package ir.online.bookstore.dao;

import ir.online.bookstore.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonDAO extends JpaRepository<Person, Long> {

    Optional<Person> findByNationalCode(String nationalCode);
}
