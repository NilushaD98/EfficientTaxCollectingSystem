package taxproject.taxpayerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taxproject.taxpayerservice.dto.request.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerCompanyDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerPersonDTO;
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
}
