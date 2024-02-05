package taxproject.taxpayingservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestCreditPersonPaymentDTO {

    private int taxPayerRegistrationNumber;
    private String payeesNIC;
    private String payeesName;
    private String payersNIC;
    private String payersName;
    private String periodCode;
    private int installmentNumber;
    private String paymentDescription;
    private String paidAmount;

}
