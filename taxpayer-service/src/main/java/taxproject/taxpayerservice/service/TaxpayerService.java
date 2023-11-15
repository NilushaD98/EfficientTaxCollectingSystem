package taxproject.taxpayerservice.service;

import taxproject.taxpayerservice.dto.request.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerCompanyDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerPersonDTO;
import taxproject.taxpayerservice.dto.request.Test;
import taxproject.taxpayerservice.dto.response.ResponseCompanyByBlockchain;
import taxproject.taxpayerservice.dto.response.ResponsePersonByBlockchainDTO;
import taxproject.taxpayerservice.dto.response.ResponseCompanyForTaxPayingDTO;
import taxproject.taxpayerservice.dto.response.ResponsePersonForTaxPayingDTO;

import java.util.List;

public interface TaxpayerService {
    String registerNewCompany(RequestAddNewTaxpayerCompanyDTO requestAddNewTaxpayerCompanyDTO);

    String addNewCompanyType(RequestAddCompanyTypeDTO requestAddCompanyTypeDTO);

    String registerNewPerson(RequestAddNewTaxpayerPersonDTO requestAddNewTaxpayerPersonDTO) throws Exception;

    ResponseCompanyForTaxPayingDTO getCompanyByRegNum(String registerNumber) throws Exception;

    ResponsePersonForTaxPayingDTO getPersonByNIC(String nic);

    void test(Test test) throws Exception;

    void test2(String  nic) throws Exception;

    List<ResponsePersonByBlockchainDTO> getAllPersons();

    List<ResponseCompanyByBlockchain> getAllCompanies();

    void deployContract();
}
