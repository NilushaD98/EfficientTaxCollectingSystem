package taxproject.taxpayerservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCompanyByBlockchain {

    private String registrationNumber;
    private String companyName;
}
