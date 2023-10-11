package taxproject.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "auth_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long auth_detail_id;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_Id")
    private Employee emp_Id;
}
