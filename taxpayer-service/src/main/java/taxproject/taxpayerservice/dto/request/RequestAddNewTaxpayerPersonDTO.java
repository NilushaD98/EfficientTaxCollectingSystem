package taxproject.taxpayerservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestAddNewTaxpayerPersonDTO {
    //Person Details
    private String nic;
    private String nameWithInitials;
    private String fullName;
    private Date birthDate;
    private String country;
    private String gender;
    private String race;
    private String jobTitle;
    private String nationality;
    //contact Details
    private int premisesNo;
    private int unitNo;
    private String address;
    private int postalCode;
    private String province;
    private String district;
    private String divisionalSecretariat;
    private String gramaNiladhariDivision;
    private String mobileContact;
    private String officeContact;
    private String homeContact;
    private String email;
    private String nameOfContactPerson;
    //bank details
    private String bankName;
    private String accountNumber;
}
