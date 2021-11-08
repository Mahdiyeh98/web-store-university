package ir.online.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true, length = 20)
    private String title;

    @OneToMany(mappedBy = "role")
    private List<PermissionRole> permissionRoleList;

    @OneToMany(mappedBy = "role")
    private List<PersonRole> personRoleList;
}
