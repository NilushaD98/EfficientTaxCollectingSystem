package taxproject.taxpayingservice.service.IMPL;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import taxproject.taxpayingservice.config.TaxPayingService;
import taxproject.taxpayingservice.dto.request.RequestCreditCompanyPaymentDTO;
import taxproject.taxpayingservice.dto.request.RequestCreditPersonPaymentDTO;
import taxproject.taxpayingservice.dto.response.*;
import taxproject.taxpayingservice.entity.TaxCreditInvoiceCompany;
import taxproject.taxpayingservice.entity.TaxCreditInvoicePerson;
import taxproject.taxpayingservice.exception.BlockChainFetchException;
import taxproject.taxpayingservice.exception.DataInconsistentException;
import taxproject.taxpayingservice.exception.SavedErrorException;
import taxproject.taxpayingservice.proxy.TaxPayerProxy;
import taxproject.taxpayingservice.repo.TaxCreditInvoiceCompanyRepo;
import taxproject.taxpayingservice.repo.TaxCreditInvoicePersonRepo;
import taxproject.taxpayingservice.service.TaxCreditService;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TaxCreditServiceIMPL implements TaxCreditService {

    @Autowired
    private TaxCreditInvoiceCompanyRepo taxCreditInvoiceCompanyRepo;
    @Autowired
    private TaxCreditInvoicePersonRepo taxCreditInvoicePersonRepo;
    @Autowired
    private TaxPayerProxy taxPayerProxy;

    private String contractAddress = "0x60446acf5baeb2a151fd03e68a63568d7a6bfaab";
    @Value("${taxpayerregistry.contract.testneturl}")
    private String testNetURL;
    @Value("${taxpayerregistry.contract.privatekey}")
    private String privateKey;
    private TaxPayingService taxPayingService;
    @Override
    public ResponsePersonPaymentDTO creditPersonPayment(RequestCreditPersonPaymentDTO requestCreditPersonPaymentDTO) {
        //save data in blockchain
        ResponsePersonForTaxPayingDTO personByNIC = taxPayerProxy.getPersonByNIC(requestCreditPersonPaymentDTO.getPayersNIC());
        if(
                (personByNIC.getPersonID() == requestCreditPersonPaymentDTO.getTaxPayerRegistrationNumber())
                && (personByNIC.getNic().equals(requestCreditPersonPaymentDTO.getPayersNIC()))
        ){
            Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            TaxCreditInvoicePerson taxCreditInvoicePerson = new TaxCreditInvoicePerson(
                    ("TPAEXR"+requestCreditPersonPaymentDTO.getPayersNIC()+"PAY"+date),
                    requestCreditPersonPaymentDTO.getTaxPayerRegistrationNumber(),
                    requestCreditPersonPaymentDTO.getPayeesNIC(),
                    requestCreditPersonPaymentDTO.getPayeesName(),
                    requestCreditPersonPaymentDTO.getPayersNIC(),
                    requestCreditPersonPaymentDTO.getPayersName(),
                    requestCreditPersonPaymentDTO.getPeriodCode(),
                    requestCreditPersonPaymentDTO.getInstallmentNumber(),
                    requestCreditPersonPaymentDTO.getPaymentDescription(),
                    requestCreditPersonPaymentDTO.getPaidAmount(),
                    date
            );
            try{
                TransactionReceipt send = taxPayingService.creditPersonPayment(
                        BigInteger.valueOf(requestCreditPersonPaymentDTO.getTaxPayerRegistrationNumber()),
                        ("TPAEXR" + requestCreditPersonPaymentDTO.getPayersNIC() + "PAY" + date),
                        requestCreditPersonPaymentDTO.getPayeesNIC(),
                        requestCreditPersonPaymentDTO.getPayeesName(),
                        requestCreditPersonPaymentDTO.getPayersNIC(),
                        requestCreditPersonPaymentDTO.getPayersName(),
                        requestCreditPersonPaymentDTO.getPeriodCode(),
                        BigInteger.valueOf(requestCreditPersonPaymentDTO.getInstallmentNumber()),
                        requestCreditPersonPaymentDTO.getPaymentDescription(),
                        requestCreditPersonPaymentDTO.getPaidAmount()
                ).send();
                if(send.getStatus().equals("0x1")){
                    taxCreditInvoicePersonRepo.save(taxCreditInvoicePerson);
                    ResponsePersonPaymentDTO responsePersonPaymentDTO = new ResponsePersonPaymentDTO(
                            date,
                            send.getTransactionHash(),
                            ("TPAEXR" + requestCreditPersonPaymentDTO.getPayersNIC() + "PAY" + date),
                            requestCreditPersonPaymentDTO.getPayeesNIC(),
                            requestCreditPersonPaymentDTO.getPayeesName(),
                            requestCreditPersonPaymentDTO.getPayersNIC(),
                            requestCreditPersonPaymentDTO.getPayersName(),
                            requestCreditPersonPaymentDTO.getPeriodCode(),
                            requestCreditPersonPaymentDTO.getInstallmentNumber(),
                            requestCreditPersonPaymentDTO.getPaymentDescription(),
                            requestCreditPersonPaymentDTO.getPaidAmount()
                    );
                    return responsePersonPaymentDTO;
                }else {
                    throw new SavedErrorException();
                }
            }catch (Exception e){
                log.error(e.getMessage());
                throw new SavedErrorException();
            }
        }else{
            throw new DataInconsistentException();
        }
    }
    @Override
    public ResponseCompanyPaymentDTO creditCompanyPayment(RequestCreditCompanyPaymentDTO requestCreditCompanyPaymentDTO) {
        ResponseCompanyForTaxPayingDTO companyByRegisterNumber = taxPayerProxy.getCompanyByRegisterNumber(requestCreditCompanyPaymentDTO.getCompanyRegistrationNumber());
        if(
                (companyByRegisterNumber.getCompanyID() ==  requestCreditCompanyPaymentDTO.getTaxPayerRegistrationNumber())
                && (companyByRegisterNumber.getRegistrationNumber().equals(requestCreditCompanyPaymentDTO.getCompanyRegistrationNumber()))
        ){
            Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            TaxCreditInvoiceCompany taxCreditInvoiceCompany = new TaxCreditInvoiceCompany(
                    ("TCAOXM"+requestCreditCompanyPaymentDTO.getCompanyRegistrationNumber()+"PAY"+date),
                    requestCreditCompanyPaymentDTO.getTaxPayerRegistrationNumber(),
                    requestCreditCompanyPaymentDTO.getPayeesNIC(),
                    requestCreditCompanyPaymentDTO.getPayeesName(),
                    requestCreditCompanyPaymentDTO.getCompanyRegistrationNumber(),
                    requestCreditCompanyPaymentDTO.getCompanyName(),
                    requestCreditCompanyPaymentDTO.getPeriodCode(),
                    requestCreditCompanyPaymentDTO.getInstallmentNumber(),
                    requestCreditCompanyPaymentDTO.getPaymentDescription(),
                    requestCreditCompanyPaymentDTO.getPaidAmount(),
                    Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
            );
            taxCreditInvoiceCompanyRepo.save(taxCreditInvoiceCompany);
            try{
                TransactionReceipt send = taxPayingService.creditCompanyPayment(
                        BigInteger.valueOf(requestCreditCompanyPaymentDTO.getTaxPayerRegistrationNumber()),
                        ("TCAOXM" + requestCreditCompanyPaymentDTO.getCompanyRegistrationNumber() + "PAY" + date),
                        requestCreditCompanyPaymentDTO.getPayeesNIC(),
                        requestCreditCompanyPaymentDTO.getPayeesName(),
                        requestCreditCompanyPaymentDTO.getCompanyRegistrationNumber(),
                        requestCreditCompanyPaymentDTO.getCompanyName(),
                        requestCreditCompanyPaymentDTO.getPeriodCode(),
                        BigInteger.valueOf(requestCreditCompanyPaymentDTO.getInstallmentNumber()),
                        requestCreditCompanyPaymentDTO.getPaymentDescription(),
                        requestCreditCompanyPaymentDTO.getPaidAmount()
                ).send();
                if(send.getStatus().equals("0x1")){
                    taxCreditInvoiceCompanyRepo.save(taxCreditInvoiceCompany);
                    ResponseCompanyPaymentDTO responsePersonPaymentDTO = new ResponseCompanyPaymentDTO(
                            date,
                            send.getTransactionHash(),
                            ("TCAOXM" + requestCreditCompanyPaymentDTO.getCompanyRegistrationNumber() + "PAY" + date),
                            requestCreditCompanyPaymentDTO.getPayeesNIC(),
                            requestCreditCompanyPaymentDTO.getPayeesName(),
                            requestCreditCompanyPaymentDTO.getCompanyRegistrationNumber(),
                            requestCreditCompanyPaymentDTO.getCompanyName(),
                            requestCreditCompanyPaymentDTO.getPeriodCode(),
                            requestCreditCompanyPaymentDTO.getInstallmentNumber(),
                            requestCreditCompanyPaymentDTO.getPaymentDescription(),
                            requestCreditCompanyPaymentDTO.getPaidAmount()
                    );
                    return responsePersonPaymentDTO;
                }else {
                    throw new SavedErrorException();
                }
            }catch (Exception e){
                log.error(e.getMessage());
                throw new SavedErrorException();
            }
        }else{
            throw new DataInconsistentException();
        }
    }
    @Override
    public List<ResponsePersonPaymentDetailsDTO> getAllPaymentDetailsByNIC(String nic) {
        try {
            Tuple6<List, List, List, List, List, List> send = taxPayingService.getAllPaymentDetailsByNIC(nic).send();
            List<Utf8String> invoiceNumbers = send.getValue1();
            List<Utf8String> payeesNICs = send.getValue2();
            List<Utf8String> payeesNames = send.getValue1();
            List<Utf8String> periodCodes = send.getValue2();
            List<Uint> installmentNumbers = send.getValue1();
            List<Utf8String> paidAmounts = send.getValue2();
            List<ResponsePersonPaymentDetailsDTO> responsePersonPaymentDetailsDTOList = new ArrayList<>();
            for(int i=0;i< invoiceNumbers.size();i++){
                ResponsePersonPaymentDetailsDTO person = new ResponsePersonPaymentDetailsDTO();
                person.setInvoiceNumber(invoiceNumbers.get(i).getValue());
                person.setPayeesNIC(payeesNICs.get(i).getValue());
                person.setPayeesName(payeesNames.get(i).getValue());
                person.setPeriodCode(periodCodes.get(i).getValue());
                person.setInstallmentNumber(installmentNumbers.get(i).getValue().intValue());
                person.setPaidAmount(paidAmounts.get(i).getValue());
                responsePersonPaymentDetailsDTOList.add(person);
            }
            return responsePersonPaymentDetailsDTOList;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BlockChainFetchException();
        }
    }
    @Override
    public List<ResponseCompanyPaymentDetailsDTO> getAllPaymentDetailsByRegNumber(String regNumber) {
        try {
            System.out.println(3);
            Tuple6<List, List, List, List, List, List> send = taxPayingService.getAllPaymentDetailsByRegNumber(regNumber).send();
            System.out.println(send);
            List<Utf8String> invoiceNumbers = send.getValue1();
            System.out.println(send);
            List<Utf8String> payeesNICs = send.getValue2();
            System.out.println(send);
            List<Utf8String> payeesNames = send.getValue3();
            System.out.println(send);
            List<Utf8String> periodCodes = send.getValue4();
            System.out.println(send);
            List<Uint> installmentNumbers = send.getValue5();
            System.out.println(send);
            List<Utf8String> paidAmounts = send.getValue6();
            System.out.println(send);
            List<ResponseCompanyPaymentDetailsDTO> responseCompanyPaymentDetailsDTOList = new ArrayList<>();
            for(int i=0;i< invoiceNumbers.size();i++){
                ResponseCompanyPaymentDetailsDTO company = new ResponseCompanyPaymentDetailsDTO();
                company.setInvoiceNumber(invoiceNumbers.get(i).getValue());
                company.setPayeesNIC(payeesNICs.get(i).getValue());
                company.setPayeesName(payeesNames.get(i).getValue());
                company.setPeriodCode(periodCodes.get(i).getValue());
                company.setInstallmentNumber(installmentNumbers.get(i).getValue().intValue());
                company.setPaidAmount(paidAmounts.get(i).getValue());
                responseCompanyPaymentDetailsDTOList.add(company);
            }
            return responseCompanyPaymentDetailsDTOList;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BlockChainFetchException();
        }
    }
    @Override
    public void deployContract() {
        try {
            Credentials credentials = Credentials.create(privateKey);
            Web3j web3j = Web3j.build(new HttpService(testNetURL));
            ContractGasProvider contractGasProvider = new DefaultGasProvider();
            TaxPayingService taxPayingService = TaxPayingService.deploy(web3j,credentials,contractGasProvider).send();
            contractAddress = taxPayingService.getContractAddress();
            System.out.println(contractAddress);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
    @Bean
    private void loadContract(){
        try {
            Credentials credentials = Credentials.create(privateKey);
            Web3j web3j = Web3j.build(new HttpService(testNetURL));
            ContractGasProvider contractGasProvider = new DefaultGasProvider();
            taxPayingService = TaxPayingService.load(contractAddress, web3j, credentials, contractGasProvider);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}