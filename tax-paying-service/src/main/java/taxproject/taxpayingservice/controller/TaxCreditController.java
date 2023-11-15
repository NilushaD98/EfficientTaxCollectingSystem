package taxproject.taxpayingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taxproject.taxpayingservice.dto.request.RequestCreditCompanyPaymentDTO;
import taxproject.taxpayingservice.dto.request.RequestCreditPersonPaymentDTO;
import taxproject.taxpayingservice.dto.response.*;
import taxproject.taxpayingservice.proxy.TaxPayerProxy;
import taxproject.taxpayingservice.service.TaxCreditService;
import taxproject.taxpayingservice.util.StandardResponse;


@RestController
@RequestMapping("tax_credit/")
public class TaxCreditController {

    @Autowired
    private TaxCreditService taxCreditService;

    @PostMapping("person_payment")
    public ResponseEntity<StandardResponse> addPersonPayment(@RequestBody RequestCreditPersonPaymentDTO requestCreditPersonPaymentDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Person Payment Receipt :",taxCreditService.creditPersonPayment(requestCreditPersonPaymentDTO)), HttpStatus.ACCEPTED
        );
    }

    @PostMapping("company_payment")
    public ResponseEntity<StandardResponse> addCompanyPayment(@RequestBody RequestCreditCompanyPaymentDTO requestCreditCompanyPaymentDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Company Payment Recipt: ",taxCreditService.creditCompanyPayment(requestCreditCompanyPaymentDTO)),HttpStatus.ACCEPTED
        );
    }

    @GetMapping("get_payment_details_by_nic")
    public ResponseEntity<StandardResponse> getPaymentDetailsByNIC(@RequestParam(value = "NIC") String nic){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Person's Payment Details By Nic: ",taxCreditService.getAllPaymentDetailsByNIC(nic)),HttpStatus.OK
        );
    }

    @GetMapping("get_payment_details_by_company_registration_number")
    public ResponseEntity<StandardResponse> getPaymentDetailsByComRegNumber(@RequestParam(value = "regNumber") String regNumber){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Company's Payment Details By ",taxCreditService.getAllPaymentDetailsByRegNumber(regNumber)),HttpStatus.OK
        );
    }

//    @GetMapping("get_person_payment_details_by_payment_id")
//
//    @GetMapping("get_company_payment_details_by_payment_id")


    @Autowired
    private TaxPayerProxy taxPayerProxy;
    @GetMapping("test")
    public void dfsfsdF(){
        ResponseCompanyForTaxPayingDTO companyByRegisterNumber = taxPayerProxy.getCompanyByRegisterNumber("142g34");
        System.out.println(companyByRegisterNumber);
    }

    @GetMapping("deploy")
    public void deplpoyContract(){
        taxCreditService.deployContract();
    }
}
