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
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bankDetailID;
    @Column(nullable = false)
    private String bankName;
    @Column(nullable = false,unique = true)
    private String accountNumber;
    @OneToOne(mappedBy = "companyBankDetailsID")
    private Company company;
    @OneToOne(mappedBy = "personBankDetailsID")
    private Person person;

    public BankDetails(String bankName, String accountNumber) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }
}
