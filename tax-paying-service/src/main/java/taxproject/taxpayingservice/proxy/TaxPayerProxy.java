package taxproject.taxpayingservice.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import taxproject.taxpayingservice.dto.response.ResponseCompanyForTaxPayingDTO;
import taxproject.taxpayingservice.dto.response.ResponsePersonForTaxPayingDTO;

@FeignClient(name = "taxpayer-service")
public interface TaxPayerProxy {

    @GetMapping("/tax_payer/get_person_by_nic_for_tax_paying")
    public ResponsePersonForTaxPayingDTO getPersonByNIC(
            @RequestParam(value = "nicNumber") String nic
    );

    @GetMapping("/tax_payer/get_company_by_register_number_for_tax_paying")
    public ResponseCompanyForTaxPayingDTO getCompanyByRegisterNumber(
            @RequestParam(value = "registerNumber") String registerNumber
    );
}
