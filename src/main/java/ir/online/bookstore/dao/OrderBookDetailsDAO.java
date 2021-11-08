package ir.online.bookstore.dao;

import ir.online.bookstore.domain.OrderBookDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderBookDetailsDAO extends JpaRepository<OrderBookDetails, Long> {
    List<OrderBookDetails>findByBooks_ISBN(String isbn);
    List<OrderBookDetails>findByOrder_Person_NationalCode(String isbn);
    List<OrderBookDetails>findByOrder_Person_PhoneNumber(String isbn);
}
