package taxproject.taxpayerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taxproject.taxpayerservice.dto.request.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerCompanyDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerPersonDTO;
import taxproject.taxpayerservice.dto.request.Test;
import taxproject.taxpayerservice.dto.response.ResponseCompanyByBlockchain;
import taxproject.taxpayerservice.dto.response.ResponseCompanyForTaxPayingDTO;
import taxproject.taxpayerservice.dto.response.ResponsePersonForTaxPayingDTO;
import taxproject.taxpayerservice.service.TaxpayerService;
import taxproject.taxpayerservice.util.StandardResponse;

import java.util.List;

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
    public ResponseEntity<StandardResponse> registerNewPerson(@RequestBody RequestAddNewTaxpayerPersonDTO requestAddNewTaxpayerPersonDTO) throws Exception {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Person Register Status",taxpayerService.registerNewPerson(requestAddNewTaxpayerPersonDTO)),HttpStatus.ACCEPTED
        );
    }
    @GetMapping("get_company_by_register_number_for_tax_paying")
    public ResponseCompanyForTaxPayingDTO getCompanyByRegisterNumber(
            @RequestParam(value = "registerNumber") String registerNumber
    ) throws Exception {
        return taxpayerService.getCompanyByRegNum(registerNumber);
    }

    @GetMapping("get_person_by_nic_for_tax_paying")
    public ResponsePersonForTaxPayingDTO getPersonByNIC(
            @RequestParam(value = "nicNumber") String nic
    ){
        return taxpayerService.getPersonByNIC(nic);
    }
    @GetMapping("get_all_persons")
    public ResponseEntity<StandardResponse> getAllPersons(){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"All Persons from BlockChain : ",taxpayerService.getAllPersons()),HttpStatus.ACCEPTED
        );
    }
    @GetMapping("get_all_companies")
    public ResponseEntity<StandardResponse> getAllCompanies(){
        List<ResponseCompanyByBlockchain> companyByBlockchainList = taxpayerService.getAllCompanies();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"All Companies from Blockchain : ",companyByBlockchainList),HttpStatus.ACCEPTED
        );
    }
    @PostMapping("test")
    public void Test(@RequestBody Test test) throws Exception {
        taxpayerService.test(test);
    }
    @PostMapping("test2/{nic}")
    public void test2(@RequestBody Test test) throws Exception {
        taxpayerService.test2(test.getNic());
    }
    @GetMapping("deploy")
    public void deployContract(){
        taxpayerService.deployContract();
    }
}
