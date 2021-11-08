package ir.online.bookstore.domain;

import ir.online.bookstore.dto.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "phone_number", nullable = false, unique = true, length = 13) // example 09121234567
    private String phoneNumber;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(name = "last_name", nullable = false, length = 60)
    private String lastName;
    @Column(name = "national_code",nullable = false, unique = true, length = 12) //example 0012345678
    private String nationalCode;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;
    @Column(nullable = false, length = 15) //postalCode => 10 digits
    private String postalCode;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "person", fetch = FetchType.LAZY)
    private List<Orders> orders;

    public PersonDTO toDto(){
        return new PersonDTO(
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
