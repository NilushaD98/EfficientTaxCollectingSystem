package taxproject.userservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestEmployeeDeleteDTO {
    private Long employeeId;
    private String reason;
}
