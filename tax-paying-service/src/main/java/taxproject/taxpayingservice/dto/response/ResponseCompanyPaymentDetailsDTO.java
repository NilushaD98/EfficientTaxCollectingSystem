package taxproject.taxpayingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCompanyPaymentDetailsDTO {
    private String invoiceNumber;
    private String payeesNIC;
    private String payeesName;
    private String periodCode;
    private int installmentNumber;
    private String paidAmount;
}
