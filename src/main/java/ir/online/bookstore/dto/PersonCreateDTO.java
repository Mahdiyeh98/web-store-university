package ir.online.bookstore.dto;

import ir.online.bookstore.domain.Orders;
import ir.online.bookstore.domain.Person;
import lombok.Data;

import java.util.List;

@Data
public class PersonCreateDTO {
    private final String phoneNumber;
    private final String name;
    private final String lastName;
    private final String nationalCode;
    private final String password;
    private final String address;
    private final String postalCode;

    public PersonCreateDTO(String phoneNumber, String name, String lastName, String nationalCode, String password, String address, String postalCode) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.password = password;
        this.address = address;
        this.postalCode = postalCode;
    }
    public Person toPerson(Long id,List<Orders> orders){
        return new Person(
                id,
                phoneNumber,
                name,
                lastName,
                nationalCode,
                password,
                address,
                postalCode,
                orders
        );
    }
}
