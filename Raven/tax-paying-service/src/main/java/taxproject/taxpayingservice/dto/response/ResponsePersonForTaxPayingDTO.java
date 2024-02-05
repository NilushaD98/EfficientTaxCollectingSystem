package taxproject.taxpayingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponsePersonForTaxPayingDTO {

    private int personID;
    private String nic;
    private String nameWithInitials;
    private int blockChainID;
}
