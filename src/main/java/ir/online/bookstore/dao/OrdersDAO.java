package ir.online.bookstore.dao;

import ir.online.bookstore.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDAO extends JpaRepository<Orders, Long> {
}
