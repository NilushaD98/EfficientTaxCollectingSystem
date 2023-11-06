package taxproject.taxpayerservice.service;

import taxproject.taxpayerservice.dto.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.dto.RequestAddNewTaxpayerPersonDTO;

public interface TaxpayerService {
    String registerNewCompany(RequestAddNewTaxpayerPersonDTO requestAddNewTaxpayerPersonDTO);

    String addNewCompanyType(RequestAddCompanyTypeDTO requestAddCompanyTypeDTO);
}
