//package ir.online.bookstore.domain;

//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@Accessors(chain = true)
//@Table(name = "address")
//@AllArgsConstructor
//@NoArgsConstructor
//public class Address {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(nullable = false)
//    private String address;
//    @Column(nullable = false, name = "postal_code", length = 15) //postalCode => 10 digits
//    private String postalCode;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "person_id", nullable = false)
//    private Persons person;
//
//}
