package taxproject.taxpayingservice.service;

import taxproject.taxpayingservice.dto.request.RequestCreditCompanyPaymentDTO;
import taxproject.taxpayingservice.dto.request.RequestCreditPersonPaymentDTO;
import taxproject.taxpayingservice.dto.response.ResponseCompanyPaymentDTO;
import taxproject.taxpayingservice.dto.response.ResponseCompanyPaymentDetailsDTO;
import taxproject.taxpayingservice.dto.response.ResponsePersonPaymentDTO;
import taxproject.taxpayingservice.dto.response.ResponsePersonPaymentDetailsDTO;

import java.util.List;

public interface TaxCreditService {
    ResponsePersonPaymentDTO creditPersonPayment(RequestCreditPersonPaymentDTO requestCreditPersonPaymentDTO,String encryptedToken);

    ResponseCompanyPaymentDTO creditCompanyPayment(RequestCreditCompanyPaymentDTO requestCreditCompanyPaymentDTO,String encryptedToken);

    List<ResponsePersonPaymentDetailsDTO> getAllPaymentDetailsByNIC(String nic,String encryptedToken);

    List<ResponseCompanyPaymentDetailsDTO> getAllPaymentDetailsByRegNumber(String regNumber,String encryptedToken);

    void deployContract();
}
