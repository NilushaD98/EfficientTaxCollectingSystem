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
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int directorID;
    @Column(nullable = false,unique = true)
    private String NICOrPassportNo;
    private String IssuanceCountryOfPassport;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String nameWithInitials;
    @Column(nullable = false)
    private Date dateOfBirth;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int postalCode;
    @Column(nullable = false)
    private String contactMobile;
    @Column(nullable = false)
    private String contactOffice;
    @Column(nullable = false)
    private String contactHome;
    @Column(nullable = false)
    private String directorEmail;
    @OneToOne(mappedBy = "directorID")
    private Company company;

    public Director(String NICOrPassportNo, String issuanceCountryOfPassport, String fullName, String nameWithInitials, Date dateOfBirth, String address, int postalCode, String contactMobile, String contactOffice, String contactHome, String directorEmail) {
        this.NICOrPassportNo = NICOrPassportNo;
        IssuanceCountryOfPassport = issuanceCountryOfPassport;
        this.fullName = fullName;
        this.nameWithInitials = nameWithInitials;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.postalCode = postalCode;
        this.contactMobile = contactMobile;
        this.contactOffice = contactOffice;
        this.contactHome = contactHome;
        this.directorEmail = directorEmail;
    }
}
