package taxproject.taxpayingservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestCreditCompanyPaymentDTO {

    private int taxPayerRegistrationNumber;
    private String payeesNIC;
    private String payeesName;
    private String companyRegistrationNumber;
    private String companyName;
    private String periodCode;
    private int installmentNumber;
    private String paymentDescription;
    private String paidAmount;

}
