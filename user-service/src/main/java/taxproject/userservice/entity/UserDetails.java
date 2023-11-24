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
    @Column(nullable = false)
    private String walletAddress;
    @Column(nullable = false)
    private String privateKey;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empId")
    private Employee empId;


    public UserDetails(String username, String password, String job_title, String walletAddress, String privateKey, Employee empId) {
        this.username = username;
        this.password = password;
        this.job_title = job_title;
        this.walletAddress = walletAddress;
        this.privateKey = privateKey;
        this.empId = empId;
    }
}
