package taxproject.taxpayerservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestAddNewTaxpayerCompanyDTO {
    //company details
    private String registrationNumber;
    private String companyName;
    private int comTypeID;
    private Date dateOfIncorporation;
    private String countryOfIncorporation;
    private String principalActivityOfBusiness;
    private String contact;
    private String companyEmail;
    private String websiteURL;
    private Boolean boiRegisterStatus;
    private Date boiStartDate;
    private Date boiExpiryDate;
    private String bankCode;
    private String foreignCompanyDateOfIncorporation;
    private String foreignCompanyCountryOfIncorporation;
    private Date foreignCompanyDateOfCommencement;
    //group company details
    private String groupCompanyRegistrationNo;
    private String nameOfParentCountry;
    private String addressOfGroupCompany;
    private String groupCompanyCountryOfIncorporation;
    private Date groupCompanyDateOfIncorporation;
    //contact details
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
    //director details
    private String nicOrPassportNo;
    private String issuanceCountryOfPassport;
    private String fullName;
    private String nameWithInitials;
    private Date dateOfBirth;
    private String directorAddress;
    private int directorPostalCode;
    private String contactMobile;
    private String contactOffice;
    private String contactHome;
    private String directorEmail;
    //bank details
    private String bankName;
    private String accountNumber;

}
