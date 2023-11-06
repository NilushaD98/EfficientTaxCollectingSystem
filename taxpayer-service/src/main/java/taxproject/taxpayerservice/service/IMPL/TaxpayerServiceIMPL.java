package taxproject.taxpayerservice.service.IMPL;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxproject.taxpayerservice.dto.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.dto.RequestAddNewTaxpayerPersonDTO;
import taxproject.taxpayerservice.entity.CompanyTypes;
import taxproject.taxpayerservice.entity.Director;
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
    public String registerNewCompany(RequestAddNewTaxpayerPersonDTO requestAddNewTaxpayerPersonDTO) {

        Director director = new Director(
                re
        );


        return null;
    }


}
