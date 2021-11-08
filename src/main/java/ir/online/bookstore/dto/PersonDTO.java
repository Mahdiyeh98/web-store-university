package ir.online.bookstore.dto;

import ir.online.bookstore.domain.Orders;
import lombok.Data;

import java.util.List;

@Data
public class PersonDTO {
    private final String phoneNumber;
    private final String name;
    private final String lastName;
    private final String nationalCode;
    private final String password;
    private final String address;
    private final String postalCode;
    private final List<Orders> orders;

    public PersonDTO(String phoneNumber, String name, String lastName, String nationalCode, String password, String address, String postalCode, List<Orders> orders) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.password = password;
        this.address = address;
        this.postalCode = postalCode;
        this.orders = orders;
    }
}
