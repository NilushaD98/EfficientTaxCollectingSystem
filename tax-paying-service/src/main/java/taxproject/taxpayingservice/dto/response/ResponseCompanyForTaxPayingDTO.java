package taxproject.taxpayingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCompanyForTaxPayingDTO {

    private int companyID;
    private String registrationNumber;
    private String companyName;
    private int blockChainID;
}
