package taxproject.taxpayerservice.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taxproject.taxpayerservice.dto.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.dto.RequestAddNewTaxpayerCompanyDTO;
import taxproject.taxpayerservice.dto.RequestAddNewTaxpayerPersonDTO;
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
}
