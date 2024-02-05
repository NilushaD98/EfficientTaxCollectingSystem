package taxproject.taxpayerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "company_types")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int companyTypeID;
    @Column(nullable = false)
    private String companyType;
    @Column(nullable = false)
    private String companyTypeDescription;
    @OneToOne(mappedBy = "comTypeID")
    private Company company;
}
