package taxproject.taxpayerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestAddCompanyTypeDTO {

    private String companyType;
    private String companyTypeDescription;
}
