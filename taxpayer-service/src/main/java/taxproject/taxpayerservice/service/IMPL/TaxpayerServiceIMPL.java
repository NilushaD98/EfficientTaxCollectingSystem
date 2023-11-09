package taxproject.taxpayerservice.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxproject.taxpayerservice.dto.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.dto.RequestAddNewTaxpayerCompanyDTO;
import taxproject.taxpayerservice.entity.*;
import taxproject.taxpayerservice.exception.DirectorIdAlreadyTakenException;
import taxproject.taxpayerservice.exception.GroupOfCompanyDetailsWrongException;
import taxproject.taxpayerservice.repo.*;
import taxproject.taxpayerservice.service.TaxpayerService;
import taxproject.taxpayerservice.util.mappers.CompanyTypeMapper;


@Service
public class TaxpayerServiceIMPL implements TaxpayerService {

    @Autowired
    private BankDetailsRepo bankDetailsRepo;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private CompanyTypeRepo companyTypeRepo;
    @Autowired
    private ContactDetailsRepo contactDetailsRepo;
    @Autowired
    private DirectorRepo directorRepo;
    @Autowired
    private GroupCompanyRepo groupCompanyRepo;
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private CompanyTypeMapper companyTypeMapper;

    @Override
    public String addNewCompanyType(RequestAddCompanyTypeDTO requestAddCompanyTypeDTO) {
        return companyTypeRepo.save(companyTypeMapper.DTOToEntity(requestAddCompanyTypeDTO)).getCompanyType() +" saved successfully.";
    }
    @Override
    public String registerNewCompany(RequestAddNewTaxpayerCompanyDTO requestAddNewTaxpayerCompanyDTO) {

        Director director2 = new Director();
        Director director = directorRepo.findDirectorByNICOrPassportNoEquals(requestAddNewTaxpayerCompanyDTO.getNICOrPassportNo());
        if(director == null){
            Director director1 = new Director(
                    requestAddNewTaxpayerCompanyDTO.getNICOrPassportNo(),
                    requestAddNewTaxpayerCompanyDTO.getIssuanceCountryOfPassport(),
                    requestAddNewTaxpayerCompanyDTO.getFullName(),
                    requestAddNewTaxpayerCompanyDTO.getNameWithInitials(),
                    requestAddNewTaxpayerCompanyDTO.getDateOfBirth(),
                    requestAddNewTaxpayerCompanyDTO.getDirectorAddress(),
                    requestAddNewTaxpayerCompanyDTO.getDirectorPostalCode(),
                    requestAddNewTaxpayerCompanyDTO.getContactMobile(),
                    requestAddNewTaxpayerCompanyDTO.getContactOffice(),
                    requestAddNewTaxpayerCompanyDTO.getContactHome(),
                    requestAddNewTaxpayerCompanyDTO.getDirectorEmail()
            );
            directorRepo.save(director1);
            director2 =director1;
        }else {
            throw new DirectorIdAlreadyTakenException();
        }
        GroupCompany groupCompany2 = new GroupCompany();
        GroupCompany groupCompany1 = groupCompanyRepo.findGroupCompanyByGroupCompanyRegistrationNoEquals(requestAddNewTaxpayerCompanyDTO.getGroupCompanyRegistrationNo());

        if(groupCompany1 == null){
            GroupCompany groupCompany = new GroupCompany(
                    requestAddNewTaxpayerCompanyDTO.getGroupCompanyRegistrationNo(),
                    requestAddNewTaxpayerCompanyDTO.getNameOfParentCountry(),
                    requestAddNewTaxpayerCompanyDTO.getAddressOfGroupCompany(),
                    requestAddNewTaxpayerCompanyDTO.getGroupCompanyCountryOfIncorporation(),
                    requestAddNewTaxpayerCompanyDTO.getGroupCompanyDateOfIncorporation()
            );
            groupCompanyRepo.save(groupCompany);
            groupCompany2 = groupCompany;
        }else if (
                groupCompany1.getGroupCompanyRegistrationNo().equals(requestAddNewTaxpayerCompanyDTO.getGroupCompanyRegistrationNo()) &&
                groupCompany1.getDateOfIncorporation().equals(requestAddNewTaxpayerCompanyDTO.getGroupCompanyDateOfIncorporation())
        ){
            groupCompany2 = groupCompany1;
        }else {
            throw new GroupOfCompanyDetailsWrongException();
        }
        ContactDetails contactDetails = new ContactDetails(
                requestAddNewTaxpayerCompanyDTO.getPremisesNo(),
                requestAddNewTaxpayerCompanyDTO.getUnitNo(),
                requestAddNewTaxpayerCompanyDTO.getAddress(),
                requestAddNewTaxpayerCompanyDTO.getPostalCode(),
                requestAddNewTaxpayerCompanyDTO.getProvince(),
                requestAddNewTaxpayerCompanyDTO.getDistrict(),
                requestAddNewTaxpayerCompanyDTO.getDivisionalSecretariat(),
                requestAddNewTaxpayerCompanyDTO.getGramaNiladhariDivision(),
                requestAddNewTaxpayerCompanyDTO.getMobileContact(),
                requestAddNewTaxpayerCompanyDTO.getOfficeContact(),
                requestAddNewTaxpayerCompanyDTO.getHomeContact(),
                requestAddNewTaxpayerCompanyDTO.getEmail(),
                requestAddNewTaxpayerCompanyDTO.getNameOfContactPerson()
        );
        contactDetailsRepo.save(contactDetails);

        BankDetails bankDetails = new BankDetails(
                requestAddNewTaxpayerCompanyDTO.getBankName(),
                requestAddNewTaxpayerCompanyDTO.getAccountNumber()
        );
        bankDetailsRepo.save(bankDetails);
        CompanyTypes companyTypes = companyTypeRepo.getById(requestAddNewTaxpayerCompanyDTO.getComTypeID());
        Company company = new Company(
                requestAddNewTaxpayerCompanyDTO.getRegistrationNumber(),
                requestAddNewTaxpayerCompanyDTO.getCompanyName(),
                companyTypes,
                requestAddNewTaxpayerCompanyDTO.getDateOfIncorporation(),
                requestAddNewTaxpayerCompanyDTO.getCountryOfIncorporation(),
                requestAddNewTaxpayerCompanyDTO.getPrincipalActivityOfBusiness(),
                requestAddNewTaxpayerCompanyDTO.getContact(),
                requestAddNewTaxpayerCompanyDTO.getCompanyEmail(),
                requestAddNewTaxpayerCompanyDTO.getWebsiteURL(),
                requestAddNewTaxpayerCompanyDTO.getBOIRegisterStatus(),
                requestAddNewTaxpayerCompanyDTO.getBOIStartDate(),
                requestAddNewTaxpayerCompanyDTO.getBOIExpiryDate(),
                requestAddNewTaxpayerCompanyDTO.getBankCode(),
                requestAddNewTaxpayerCompanyDTO.getForeignCompanyDateOfIncorporation(),
                requestAddNewTaxpayerCompanyDTO.getForeignCompanyCountryOfIncorporation(),
                requestAddNewTaxpayerCompanyDTO.getForeignCompanyDateOfCommencement(),
                groupCompany1,
                contactDetails,
                director2,
                bankDetails
        );
        return companyRepo.save(company).getCompanyName()+" saved.";
    }
}
