package taxproject.taxpayerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupCompanyID;
    @Column(nullable = false)
    private String groupCompanyRegistrationNo;
    @Column(nullable = false)
    private String nameOfParentCountry;
    @Column(nullable = false)
    private String addressOfGroupCompany;
    @Column(nullable = false)
    private String countryOfIncorporation;
    @Column(nullable = false)
    private Date dateOfIncorporation;
}
