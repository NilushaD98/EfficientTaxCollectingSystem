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
    String registerNewCompany(RequestAddNewTaxpayerCompanyDTO requestAddNewTaxpayerCompanyDTO ,String encryptedToken);

    String addNewCompanyType(RequestAddCompanyTypeDTO requestAddCompanyTypeDTO,String encryptedToken);

    String registerNewPerson(RequestAddNewTaxpayerPersonDTO requestAddNewTaxpayerPersonDTO,String encryptedToken) throws Exception;

    ResponseCompanyForTaxPayingDTO getCompanyByRegNum(String registerNumber,String encryptedToken) throws Exception;

    ResponsePersonForTaxPayingDTO getPersonByNIC(String nic,String encryptedToken);

    void test(Test test) throws Exception;

    void test2(String  nic) throws Exception;

    List<ResponsePersonByBlockchainDTO> getAllPersons(String encryptedToken);

    List<ResponseCompanyByBlockchain> getAllCompanies(String encryptedToken);

    void deployContract();
}
