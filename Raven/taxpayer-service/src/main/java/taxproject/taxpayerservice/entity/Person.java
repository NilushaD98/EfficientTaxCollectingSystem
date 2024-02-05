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
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int personID;
    @Column(nullable = false,unique = true)
    private String nic;
    @Column(nullable = false)
    private String nameWithInitials;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private Date birthDate;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String race;
    @Column(nullable = false)
    private String jobTitle;
    @Column(nullable = false)
    private String nationality;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ContactDetails personContactDetailsID;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private BankDetails personBankDetailsID;
    private int blockChainID;

    public Person(String nic, String nameWithInitials, String fullName, Date birthDate, String country, String gender, String race, String jobTitle, String nationality, ContactDetails personContactDetailsID, BankDetails personBankDetailsID,int blockChainID) {
        this.nic = nic;
        this.nameWithInitials = nameWithInitials;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.country = country;
        this.gender = gender;
        this.race = race;
        this.jobTitle = jobTitle;
        this.nationality = nationality;
        this.personContactDetailsID = personContactDetailsID;
        this.personBankDetailsID = personBankDetailsID;
        this.blockChainID = blockChainID;
    }
}
