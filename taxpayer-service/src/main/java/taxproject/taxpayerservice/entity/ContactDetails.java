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
    @OneToOne(mappedBy = "contactDetailsID")
    private Company company;

}
