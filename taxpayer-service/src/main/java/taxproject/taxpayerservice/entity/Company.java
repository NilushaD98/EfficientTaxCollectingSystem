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

    public Company(String registrationNumber, String companyName, CompanyTypes comTypeID, Date dateOfIncorporation, String countryOfIncorporation, String principalActivityOfBusiness, String contact, String email, String websiteURL, Boolean BOIRegisterStatus, Date BOIStartDate, Date BOIExpiryDate, String bankCode, String foreignCompanyDateOfIncorporation, String foreignCompanyCountryOfIncorporation, Date foreignCompanyDateOfCommencement, GroupCompany groupCompanyID, ContactDetails companyContactDetailsID, Director directorID, BankDetails companyBankDetailsID) {
        this.registrationNumber = registrationNumber;
        this.companyName = companyName;
        this.comTypeID = comTypeID;
        this.dateOfIncorporation = dateOfIncorporation;
        this.countryOfIncorporation = countryOfIncorporation;
        this.principalActivityOfBusiness = principalActivityOfBusiness;
        this.contact = contact;
        this.email = email;
        this.websiteURL = websiteURL;
        this.BOIRegisterStatus = BOIRegisterStatus;
        this.BOIStartDate = BOIStartDate;
        this.BOIExpiryDate = BOIExpiryDate;
        BankCode = bankCode;
        this.foreignCompanyDateOfIncorporation = foreignCompanyDateOfIncorporation;
        this.foreignCompanyCountryOfIncorporation = foreignCompanyCountryOfIncorporation;
        this.foreignCompanyDateOfCommencement = foreignCompanyDateOfCommencement;
        this.groupCompanyID = groupCompanyID;
        this.companyContactDetailsID = companyContactDetailsID;
        this.directorID = directorID;
        this.companyBankDetailsID = companyBankDetailsID;
    }
}
