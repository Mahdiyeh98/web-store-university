package ir.online.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Accessors(chain = true)
@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 2, nullable = false)
    private Long amount; // sum of order-details
    @Column(name = "create_time", nullable = false)
    private Date creatTime;
    @Column(name = "is_cancel", length = 1, nullable = false)
    private Boolean isCancel;
    @Column(length = 10, nullable = false)
    private String Status; // ex. Accepted //TODO Enum

    @OneToMany(mappedBy = "transaction")
    private List<Ipg> ipgList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;
}
