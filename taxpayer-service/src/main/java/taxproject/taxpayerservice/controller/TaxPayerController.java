package taxproject.taxpayerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import taxproject.taxpayerservice.config.PersonRegistry;
import taxproject.taxpayerservice.dto.request.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerCompanyDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerPersonDTO;
import taxproject.taxpayerservice.dto.request.Test;
import taxproject.taxpayerservice.dto.response.ResponseCompanyForTaxPayingDTO;
import taxproject.taxpayerservice.dto.response.ResponsePersonForTaxPayingDTO;
import taxproject.taxpayerservice.service.TaxpayerService;
import taxproject.taxpayerservice.util.StandardResponse;

@RestController
@RequestMapping(path = "/tax_payer/")
public class TaxPayerController {

    @Autowired
    private TaxpayerService taxpayerService;

    @PostMapping("add_new_company_type")
    public ResponseEntity<StandardResponse> addNewCompanyType(@RequestBody RequestAddCompanyTypeDTO requestAddCompanyTypeDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Company Type Saved Status : ",taxpayerService.addNewCompanyType(requestAddCompanyTypeDTO)),HttpStatus.ACCEPTED
        );
    }
    @PostMapping("register_new_company")
    public ResponseEntity<StandardResponse> registerNewCompany(@RequestBody RequestAddNewTaxpayerCompanyDTO requestAddNewTaxpayerCompanyDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Company Register Status : ",taxpayerService.registerNewCompany(requestAddNewTaxpayerCompanyDTO)), HttpStatus.ACCEPTED
        );
    }
    @PostMapping("register_new_person")
    public ResponseEntity<StandardResponse> registerNewPerson(@RequestBody RequestAddNewTaxpayerPersonDTO requestAddNewTaxpayerPersonDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Person Register Status",taxpayerService.registerNewPerson(requestAddNewTaxpayerPersonDTO)),HttpStatus.ACCEPTED
        );
    }
    @GetMapping("get_compnay_by_register_number_for_tax_paying/{registerNumber}")
    public ResponseCompanyForTaxPayingDTO getCompanyByRegisterNumber(
            @PathVariable (value = "registerNumber") String registerNumber
    ){
        return taxpayerService.getCompanyByRegNum(registerNumber);
    }

    @GetMapping("get_person_by_nicr_for_tax_paying/{nicNumber}")
    public ResponsePersonForTaxPayingDTO getPersonByNIC(
            @PathVariable(value = "nicNumber") String nic
    ){
        return taxpayerService.getPersonByNIC(nic);
    }
    @PostMapping("test")
    public void Test(@RequestBody Test test) throws Exception {
        taxpayerService.test(test);
    }
    @PostMapping("test2")
    public void test2() throws Exception {
        taxpayerService.test2();
    }

    @GetMapping("/deploy/contract")
    public void deployContract() throws Exception {
        String privateKey = "6e81f5778c39f67fa1a334bafd64b7d6ae8373543a03913a112df1ffb972c946";
        Credentials credentials = Credentials.create(privateKey);
        Web3j web3j = Web3j.build(new HttpService("https://sepolia.infura.io/v3/88c0c2df599940a4b3131bd5f5d6d965"));
        ContractGasProvider contractGasProvider = new DefaultGasProvider();

        PersonRegistry personRegistry = PersonRegistry.deploy(web3j,credentials,contractGasProvider).send();
        System.out.println(personRegistry.getContractAddress());

    }
}
