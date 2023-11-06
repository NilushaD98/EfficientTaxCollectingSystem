package taxproject.taxpayerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String District;
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
