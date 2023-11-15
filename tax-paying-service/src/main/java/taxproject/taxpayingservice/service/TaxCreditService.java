package taxproject.taxpayingservice.service;

import taxproject.taxpayingservice.dto.request.RequestCreditCompanyPaymentDTO;
import taxproject.taxpayingservice.dto.request.RequestCreditPersonPaymentDTO;
import taxproject.taxpayingservice.dto.response.ResponseCompanyPaymentDTO;
import taxproject.taxpayingservice.dto.response.ResponseCompanyPaymentDetailsDTO;
import taxproject.taxpayingservice.dto.response.ResponsePersonPaymentDTO;
import taxproject.taxpayingservice.dto.response.ResponsePersonPaymentDetailsDTO;

import java.util.List;

public interface TaxCreditService {
    ResponsePersonPaymentDTO creditPersonPayment(RequestCreditPersonPaymentDTO requestCreditPersonPaymentDTO);

    ResponseCompanyPaymentDTO creditCompanyPayment(RequestCreditCompanyPaymentDTO requestCreditCompanyPaymentDTO);

    List<ResponsePersonPaymentDetailsDTO> getAllPaymentDetailsByNIC(String nic);

    List<ResponseCompanyPaymentDetailsDTO> getAllPaymentDetailsByRegNumber(String regNumber);

    void deployContract();
}
