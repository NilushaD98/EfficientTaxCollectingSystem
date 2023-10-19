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
}
