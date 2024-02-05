package taxproject.taxpayerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addressID;
    @Column(nullable = false)
    private int premisesNo;
    @Column(nullable = false)
    private int unitNo;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int postalCode;
    @Column(nullable = false)
    private String province;
    @Column(nullable = false)
    private String District;
    @Column(nullable = false)
    private String divisionalSecretariat;
    @Column(nullable = false)
    private String gramaNiladhariDivision;
    @Column(nullable = false)
    private String mobileContact;
    @Column(nullable = false)
    private String officeContact;
    private String homeContact;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String nameOfContactPerson;
    @OneToOne(mappedBy = "companyContactDetailsID")
    private Company company;
    @OneToOne(mappedBy = "personContactDetailsID")
    private Person person;

    public ContactDetails(int premisesNo, int unitNo, String address, int postalCode, String province, String district, String divisionalSecretariat, String gramaNiladhariDivision, String mobileContact, String officeContact, String homeContact, String email, String nameOfContactPerson) {
        this.premisesNo = premisesNo;
        this.unitNo = unitNo;
        this.address = address;
        this.postalCode = postalCode;
        this.province = province;
        District = district;
        this.divisionalSecretariat = divisionalSecretariat;
        this.gramaNiladhariDivision = gramaNiladhariDivision;
        this.mobileContact = mobileContact;
        this.officeContact = officeContact;
        this.homeContact = homeContact;
        this.email = email;
        this.nameOfContactPerson = nameOfContactPerson;
    }
}
