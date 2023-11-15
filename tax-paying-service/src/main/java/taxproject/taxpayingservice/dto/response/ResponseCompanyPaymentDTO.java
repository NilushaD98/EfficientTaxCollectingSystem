package taxproject.taxpayingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCompanyPaymentDTO {
    private Date date;
    private String transactionID;
    private String invoiceID;
    private String payeeNIC;
    private String payeeName;
    private String companyRegistrationNumber;
    private String companyName;
    private String periodCode;
    private int installmentNumber;
    private String paymentDescription;
    private String paidAmount;
}
