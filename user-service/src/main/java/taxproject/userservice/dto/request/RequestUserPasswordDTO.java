package taxproject.userservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUserPasswordDTO {
    private Long userID;
    private String currentPassword;
    private String newPassword;
}
