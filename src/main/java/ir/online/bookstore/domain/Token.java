package ir.online.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

import java.sql.Timestamp;

@Accessors(chain = true)
@Entity
@Table(name = "token")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 5)
    @Transient
    private int token;
    @Column(nullable = false, name = "expired_time")
    @Transient
    private Timestamp expiredTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_id", nullable = false)
    private Person personal;
}
