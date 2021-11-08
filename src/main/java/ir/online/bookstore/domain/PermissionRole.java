package ir.online.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Entity
@Table(name = "permission_role")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissionRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "permission_id")
    private Permissions permission;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "role_id")
    private Roles role;
}
