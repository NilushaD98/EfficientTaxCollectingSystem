package taxproject.taxpayerservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponsePersonByBlockchainDTO {

    private String nic;
    private String nameWithInitials;
}
