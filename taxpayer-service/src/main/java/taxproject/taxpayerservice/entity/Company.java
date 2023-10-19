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
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int companyID;
    @Column(nullable = false,unique = true)
    private String registrationNumber;
    @Column(nullable = false)
    private String companyName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comTypeID")
    private CompanyTypes comTypeID;
    @Column(nullable = false)
    private Date dateOfIncorporation;
    @Column(nullable = false)
    private String countryOfIncorporation;
    @Column(nullable = false)
    private String principalActivityOfBusiness;
    @Column(nullable = false)
    private String contact;
    @Column(nullable = false)
    private String email;
    private String websiteURL;
    @Column(nullable = false)
    private Boolean BOIRegisterStatus;
    private Date BOIStartDate;
    private Date BOIExpiryDate;
    private String BankCode;
    private String foreignCompanyDateOfIncorporation;
    private String foreignCompanyCountryOfIncorporation;
    private Date foreignCompanyDateOfCommencement;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private GroupCompany groupCompanyID;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ContactDetails companyContactDetailsID;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Director directorID;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private BankDetails companyBankDetailsID;


}
