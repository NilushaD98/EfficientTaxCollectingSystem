package taxproject.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import taxproject.userservice.dto.enu.Roles;

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
    @Column(nullable = false)
    private String job_title;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_Id")
    private Employee emp_Id;

    public UserDetails(String username, String password, String job_title, Employee emp_Id) {
        this.username = username;
        this.password = password;
        this.job_title = job_title;
        this.emp_Id = emp_Id;
    }
}
