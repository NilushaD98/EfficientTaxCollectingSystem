package taxproject.userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseUserAuthDetailsDTO {

    private String username;
    private String password;
    private String job_title;
    private String privateKey;
}
