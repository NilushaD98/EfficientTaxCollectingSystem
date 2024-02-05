package taxproject.taxpayingservice.entity;

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
public class TaxCreditInvoiceCompany {
    @Id
    private String invoiceID;
    @Column(nullable = false)
    private int taxPayerRegistrationNumber;
    @Column(nullable = false)
    private String payeesNIC;
    @Column(nullable = false)
    private String payeesName;
    @Column(nullable = false)
    private String companyRegistrationNumber;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String periodCode;
    @Column(nullable = false)
    private int installmentNumber;
    @Column(nullable = false)
    private String paymentDescription;
    @Column(nullable = false)
    private String paidAmount;
    @Column(nullable = false)
    private Date paidDate;

    public TaxCreditInvoiceCompany(int taxPayerRegistrationNumber, String payeesNIC, String payeesName, String companyRegistrationNumber, String companyName, String periodCode, int installmentNumber, String paymentDescription, String paidAmount, Date paidDate) {
        this.taxPayerRegistrationNumber = taxPayerRegistrationNumber;
        this.payeesNIC = payeesNIC;
        this.payeesName = payeesName;
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.companyName = companyName;
        this.periodCode = periodCode;
        this.installmentNumber = installmentNumber;
        this.paymentDescription = paymentDescription;
        this.paidAmount = paidAmount;
        this.paidDate = paidDate;
    }
}
