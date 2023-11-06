package taxproject.taxpayerservice.service.IMPL;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxproject.taxpayerservice.dto.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.dto.RequestAddNewTaxpayerCompanyDTO;
import taxproject.taxpayerservice.dto.RequestAddNewTaxpayerPersonDTO;
import taxproject.taxpayerservice.entity.CompanyTypes;
import taxproject.taxpayerservice.entity.Director;
import taxproject.taxpayerservice.repo.*;
import taxproject.taxpayerservice.service.TaxpayerService;
import taxproject.taxpayerservice.util.mappers.CompanyTypeMapper;

import java.util.Optional;

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

        Director director = directorRepo.findDirectorByNICOrPassportNoEquals(requestAddNewTaxpayerCompanyDTO.getNICOrPassportNo());
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

        return null;
    }


}
