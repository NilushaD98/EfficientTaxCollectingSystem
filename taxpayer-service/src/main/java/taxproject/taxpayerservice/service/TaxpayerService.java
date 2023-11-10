package taxproject.taxpayerservice.service;

import taxproject.taxpayerservice.dto.request.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerCompanyDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerPersonDTO;
import taxproject.taxpayerservice.dto.response.ResponseCompanyForTaxPayingDTO;
import taxproject.taxpayerservice.dto.response.ResponsePersonForTaxPayingDTO;

public interface TaxpayerService {
    String registerNewCompany(RequestAddNewTaxpayerCompanyDTO requestAddNewTaxpayerCompanyDTO);

    String addNewCompanyType(RequestAddCompanyTypeDTO requestAddCompanyTypeDTO);

    String registerNewPerson(RequestAddNewTaxpayerPersonDTO requestAddNewTaxpayerPersonDTO);

    ResponseCompanyForTaxPayingDTO getCompanyByRegNum(String registerNumber);

    ResponsePersonForTaxPayingDTO getPersonByNIC(String nic);
}
