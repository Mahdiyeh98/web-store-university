package ir.online.bookstore.service;

import ir.online.bookstore.dao.OrderBookDetailsDAO;
import ir.online.bookstore.domain.OrderBookDetails;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderBookDetailsService {
    private final OrderBookDetailsDAO orderBookDetailsDAO;

    @Autowired
    public OrderBookDetailsService(OrderBookDetailsDAO orderBookDetailsDAO) {
        this.orderBookDetailsDAO = orderBookDetailsDAO;
    }

    public OrderBookDetails save(OrderBookDetails orderBookDetails) {
        return orderBookDetailsDAO.save(orderBookDetails);
    }

    public List<OrderBookDetails> getOrderBookDetailsByIsbn(String isbn) {
        return orderBookDetailsDAO.findByBooks_ISBN(isbn);
    }

    public List<OrderBookDetails> getOrderBookDetailsByPhoneNumber(String phoneNumber) {
        return orderBookDetailsDAO.findByOrder_Person_PhoneNumber(phoneNumber);
    }

    public List<OrderBookDetails> getOrderBookDetailsByNationalCode(String nationalCode) {
        return orderBookDetailsDAO.findByOrder_Person_NationalCode(nationalCode);
    }

    public List<OrderBookDetails> getAllOrderBookDetails() {
        return orderBookDetailsDAO.findAll();
    }

    public void deleteByPhoneNumber(String phoneNumber) {
        List<OrderBookDetails> orderBookDetails = getOrderBookDetailsByPhoneNumber(phoneNumber);
        for (OrderBookDetails orderBookDetail : orderBookDetails) {
            orderBookDetailsDAO.delete(orderBookDetail);
        }
    }
}
