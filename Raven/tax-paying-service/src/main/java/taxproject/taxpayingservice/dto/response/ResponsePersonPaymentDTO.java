package taxproject.taxpayingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponsePersonPaymentDTO {
    private Date date;
    private String transactionID;
    private String invoiceID;
    private String payeeNIC;
    private String payeeName;
    private String payerNIC;
    private String payerName;
    private String periodCode;
    private int installmentNumber;
    private String paymentDescription;
    private String paidAmount;
}
