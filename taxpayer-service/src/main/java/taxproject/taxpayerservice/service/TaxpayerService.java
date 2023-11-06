package taxproject.taxpayerservice.service;

import taxproject.taxpayerservice.dto.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.dto.RequestAddNewTaxpayerCompanyDTO;
import taxproject.taxpayerservice.dto.RequestAddNewTaxpayerPersonDTO;

public interface TaxpayerService {
    String registerNewCompany(RequestAddNewTaxpayerCompanyDTO requestAddNewTaxpayerCompanyDTO);

    String addNewCompanyType(RequestAddCompanyTypeDTO requestAddCompanyTypeDTO);
}
