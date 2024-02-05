package taxproject.taxpayerservice.service.IMPL;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import taxproject.taxpayerservice.config.TaxPayerRegistry;
import taxproject.taxpayerservice.dto.request.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerCompanyDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerPersonDTO;
import taxproject.taxpayerservice.dto.request.Test;
import taxproject.taxpayerservice.dto.response.ResponseCompanyByBlockchain;
import taxproject.taxpayerservice.dto.response.ResponseCompanyForTaxPayingDTO;
import taxproject.taxpayerservice.dto.response.ResponsePersonByBlockchainDTO;
import taxproject.taxpayerservice.dto.response.ResponsePersonForTaxPayingDTO;
import taxproject.taxpayerservice.entity.*;
import taxproject.taxpayerservice.exception.BlockChainFetchException;
import taxproject.taxpayerservice.exception.CompanyRegistrationNumberWrongException;
import taxproject.taxpayerservice.exception.PersonNotInDatabaseException;
import taxproject.taxpayerservice.exception.SavedErrorException;
import taxproject.taxpayerservice.repo.*;
import taxproject.taxpayerservice.service.TaxpayerService;
import taxproject.taxpayerservice.util.mappers.CompanyTypeMapper;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Service
@Slf4j
public class TaxpayerServiceIMPL implements TaxpayerService {

    @Autowired
    private BankDetailsRepo bankDetailsRepo;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private CompanyTypeRepo companyTypeRepo;
    @Autowired
    private ContactDetailsRepo contactDetailsRepo;
    @Autowired
    private DirectorRepo directorRepo;
    @Autowired
    private GroupCompanyRepo groupCompanyRepo;
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private CompanyTypeMapper companyTypeMapper;
    @Autowired
    private  Web3j web3j;
    @Autowired
    private JwtUtils jwtUtils;

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "YourSecretKey123";
    private String contractAddress = "0x4f10e41941dd394c826a38e177f2dbcdab4f7336";
    @Value("${taxpayerregistry.contract.testneturl}")
    private String testNetURL;

    private String privateKey;
    private int companyBlockChainID = 0;
    private int personBlockChainID = 0;
    @Override
    public String addNewCompanyType(RequestAddCompanyTypeDTO requestAddCompanyTypeDTO,String encryptedToken) {

        return companyTypeRepo.save(companyTypeMapper.DTOToEntity(requestAddCompanyTypeDTO)).getCompanyType() +" saved successfully.";
    }
    @Override
    public String registerNewPerson(RequestAddNewTaxpayerPersonDTO requestAddNewTaxpayerPersonDTO, String encryptedToken) throws Exception {
        BankDetails bankDetails = new BankDetails(
                requestAddNewTaxpayerPersonDTO.getBankName(),
                requestAddNewTaxpayerPersonDTO.getAccountNumber()
        );
        bankDetailsRepo.save(bankDetails);

        ContactDetails contactDetails = new ContactDetails(
                requestAddNewTaxpayerPersonDTO.getPremisesNo(),
                requestAddNewTaxpayerPersonDTO.getUnitNo(),
                requestAddNewTaxpayerPersonDTO.getAddress(),
                requestAddNewTaxpayerPersonDTO.getPostalCode(),
                requestAddNewTaxpayerPersonDTO.getProvince(),
                requestAddNewTaxpayerPersonDTO.getDistrict(),
                requestAddNewTaxpayerPersonDTO.getDivisionalSecretariat(),
                requestAddNewTaxpayerPersonDTO.getGramaNiladhariDivision(),
                requestAddNewTaxpayerPersonDTO.getMobileContact(),
                requestAddNewTaxpayerPersonDTO.getOfficeContact(),
                requestAddNewTaxpayerPersonDTO.getHomeContact(),
                requestAddNewTaxpayerPersonDTO.getEmail(),
                requestAddNewTaxpayerPersonDTO.getNameOfContactPerson()
        );
        contactDetailsRepo.save(contactDetails);
        Person person = new Person(
                requestAddNewTaxpayerPersonDTO.getNic(),
                requestAddNewTaxpayerPersonDTO.getNameWithInitials(),
                requestAddNewTaxpayerPersonDTO.getFullName(),
                requestAddNewTaxpayerPersonDTO.getBirthDate(),
                requestAddNewTaxpayerPersonDTO.getCountry(),
                requestAddNewTaxpayerPersonDTO.getGender(),
                requestAddNewTaxpayerPersonDTO.getRace(),
                requestAddNewTaxpayerPersonDTO.getJobTitle(),
                requestAddNewTaxpayerPersonDTO.getNationality(),
                contactDetails,
                bankDetails,
                ++personBlockChainID
        );
        //register the person in blockchain
        try {
            personRepo.save(person);
            Credentials credentials = Credentials.create( jwtUtils.getClaims(decrypt(encryptedToken)).get("pkey", String.class));
            Web3j web3j = Web3j.build(new HttpService(testNetURL));
            ContractGasProvider contractGasProvider = new DefaultGasProvider();
            TaxPayerRegistry taxPayerRegistry = TaxPayerRegistry.load(contractAddress,web3j,credentials,contractGasProvider);
            TransactionReceipt send = taxPayerRegistry.addPerson(requestAddNewTaxpayerPersonDTO.getNic(), requestAddNewTaxpayerPersonDTO.getNameWithInitials()).send();
            if(send.getStatus().equals("0x1")){
                return person.getNameWithInitials() +" saved."+ " Tax Payer Id: "+ person.getPersonID();
            }else {
                personRepo.deleteById(person.getPersonID());
                throw new SavedErrorException();
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @Override
    public String registerNewCompany(RequestAddNewTaxpayerCompanyDTO requestAddNewTaxpayerCompanyDTO,String encryptedToken) {

        Director director = new Director(
                requestAddNewTaxpayerCompanyDTO.getNicOrPassportNo(),
                requestAddNewTaxpayerCompanyDTO.getIssuanceCountryOfPassport(),
                requestAddNewTaxpayerCompanyDTO.getFullName(),
                requestAddNewTaxpayerCompanyDTO.getNameWithInitials(),
                requestAddNewTaxpayerCompanyDTO.getDateOfBirth(),
                requestAddNewTaxpayerCompanyDTO.getDirectorAddress(),
                requestAddNewTaxpayerCompanyDTO.getDirectorPostalCode(),
                requestAddNewTaxpayerCompanyDTO.getContactMobile(),
                requestAddNewTaxpayerCompanyDTO.getContactOffice(),
                requestAddNewTaxpayerCompanyDTO.getContactHome(),
                requestAddNewTaxpayerCompanyDTO.getDirectorEmail()
        );
        directorRepo.save(director);
        GroupCompany groupCompany = new GroupCompany(
                requestAddNewTaxpayerCompanyDTO.getGroupCompanyRegistrationNo(),
                requestAddNewTaxpayerCompanyDTO.getNameOfParentCountry(),
                requestAddNewTaxpayerCompanyDTO.getAddressOfGroupCompany(),
                requestAddNewTaxpayerCompanyDTO.getGroupCompanyCountryOfIncorporation(),
                requestAddNewTaxpayerCompanyDTO.getGroupCompanyDateOfIncorporation()
        );
        groupCompanyRepo.save(groupCompany);

        ContactDetails contactDetails = new ContactDetails(
                requestAddNewTaxpayerCompanyDTO.getPremisesNo(),
                requestAddNewTaxpayerCompanyDTO.getUnitNo(),
                requestAddNewTaxpayerCompanyDTO.getAddress(),
                requestAddNewTaxpayerCompanyDTO.getPostalCode(),
                requestAddNewTaxpayerCompanyDTO.getProvince(),
                requestAddNewTaxpayerCompanyDTO.getDistrict(),
                requestAddNewTaxpayerCompanyDTO.getDivisionalSecretariat(),
                requestAddNewTaxpayerCompanyDTO.getGramaNiladhariDivision(),
                requestAddNewTaxpayerCompanyDTO.getMobileContact(),
                requestAddNewTaxpayerCompanyDTO.getOfficeContact(),
                requestAddNewTaxpayerCompanyDTO.getHomeContact(),
                requestAddNewTaxpayerCompanyDTO.getEmail(),
                requestAddNewTaxpayerCompanyDTO.getNameOfContactPerson()
        );
        contactDetailsRepo.save(contactDetails);

        BankDetails bankDetails = new BankDetails(
                requestAddNewTaxpayerCompanyDTO.getBankName(),
                requestAddNewTaxpayerCompanyDTO.getAccountNumber()
        );
        bankDetailsRepo.save(bankDetails);
        CompanyTypes companyTypes = companyTypeRepo.getById(requestAddNewTaxpayerCompanyDTO.getComTypeID());
        Company company = new Company(
                requestAddNewTaxpayerCompanyDTO.getRegistrationNumber(),
                requestAddNewTaxpayerCompanyDTO.getCompanyName(),
                companyTypes,
                requestAddNewTaxpayerCompanyDTO.getDateOfIncorporation(),
                requestAddNewTaxpayerCompanyDTO.getCountryOfIncorporation(),
                requestAddNewTaxpayerCompanyDTO.getPrincipalActivityOfBusiness(),
                requestAddNewTaxpayerCompanyDTO.getContact(),
                requestAddNewTaxpayerCompanyDTO.getCompanyEmail(),
                requestAddNewTaxpayerCompanyDTO.getWebsiteURL(),
                requestAddNewTaxpayerCompanyDTO.getBoiRegisterStatus(),
                requestAddNewTaxpayerCompanyDTO.getBoiStartDate(),
                requestAddNewTaxpayerCompanyDTO.getBoiExpiryDate(),
                requestAddNewTaxpayerCompanyDTO.getBankCode(),
                requestAddNewTaxpayerCompanyDTO.getForeignCompanyDateOfIncorporation(),
                requestAddNewTaxpayerCompanyDTO.getForeignCompanyCountryOfIncorporation(),
                requestAddNewTaxpayerCompanyDTO.getForeignCompanyDateOfCommencement(),
                groupCompany,
                contactDetails,
                director,
                bankDetails,
                ++companyBlockChainID
        );
        // register the company in blockchain
        try {
            companyRepo.save(company);
            Credentials credentials = Credentials.create( jwtUtils.getClaims(decrypt(encryptedToken)).get("pkey", String.class));
            Web3j web3j = Web3j.build(new HttpService(testNetURL));
            ContractGasProvider contractGasProvider = new DefaultGasProvider();
            TaxPayerRegistry taxPayerRegistry = TaxPayerRegistry.load(contractAddress,web3j,credentials,contractGasProvider);
            TransactionReceipt send = taxPayerRegistry.addCompany(requestAddNewTaxpayerCompanyDTO.getRegistrationNumber(), requestAddNewTaxpayerCompanyDTO.getCompanyName()).send();
            System.out.println(send);
            if(send.getStatus().equals("0x1")){
                return company.getCompanyName() +" saved. Tax Payer Company Id: "+ company.getCompanyID();
            }else {
                personRepo.deleteById(company.getCompanyID());
                throw new SavedErrorException();
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Override
    public ResponseCompanyForTaxPayingDTO getCompanyByRegNum(String registerNumber,String encryptedToken) throws Exception {
        try {
            Credentials credentials = Credentials.create( jwtUtils.getClaims(decrypt(encryptedToken)).get("pkey", String.class));
            Web3j web3j = Web3j.build(new HttpService(testNetURL));
            ContractGasProvider contractGasProvider = new DefaultGasProvider();
            TaxPayerRegistry taxPayerRegistry = TaxPayerRegistry.load(contractAddress,web3j,credentials,contractGasProvider);
            Tuple2<String, String> send = taxPayerRegistry.getCompanyByRegistrationNumber(registerNumber).send();
            if(send.getValue1() != null){
                Company company = companyRepo.getCompaniesByRegistrationNumberEquals(registerNumber);
                if(company.getCompanyName().equals(send.getValue2())){
                    return new ResponseCompanyForTaxPayingDTO(
                            company.getCompanyID(),
                            company.getRegistrationNumber(),
                            company.getCompanyName(),
                            company.getBlockChainID()
                    );
                }else {
                    throw new CompanyRegistrationNumberWrongException();
                }
            }else {
                throw new CompanyRegistrationNumberWrongException();
            }
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BlockChainFetchException();
        }
    }
    @Override
    public ResponsePersonForTaxPayingDTO getPersonByNIC(String nic,String encryptedToken) {
        try {
            Credentials credentials = Credentials.create( jwtUtils.getClaims(decrypt(encryptedToken)).get("pkey", String.class));
            Web3j web3j = Web3j.build(new HttpService(testNetURL));
            ContractGasProvider contractGasProvider = new DefaultGasProvider();
            TaxPayerRegistry taxPayerRegistry = TaxPayerRegistry.load(contractAddress,web3j,credentials,contractGasProvider);
            Tuple2<String, String> send = taxPayerRegistry.getPersonByNIC(nic).send();
            if(send.getValue1() != null){
                Person person = personRepo.getPersonByNicEquals(nic);
                if(person.getNameWithInitials().equals(send.getValue2())){
                    return new ResponsePersonForTaxPayingDTO(
                            person.getPersonID(),
                            person.getNic(),
                            person.getNameWithInitials(),
                            person.getBlockChainID()
                    );
                }else {
                    throw new PersonNotInDatabaseException();
                }
            }else {
                throw new PersonNotInDatabaseException();
            }
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BlockChainFetchException();
        }
    }
    @Override
    public void test(Test test) throws Exception {
//        TaxPayerRegistry taxPayerRegistry = loadContract("bER9lo6u9RM690rKS58MHY8HPdBnoNNfYWq9X80eGalRnOypPKjp4FCFrfT8F8Mr8plPAOAF/xh/psypUg8yyAc8DzHz0mDQ6nN7qu3uzFNhue+9ntCNqOrZYf0DYxBBbLsEhJvT+2/kD5M6VgQ7bAVBR0I7RpSvHQSAB0q3A/ydG1OMJtfkvA8wgEGnD8o+mgyO+VPQzWAMzjjkGpc+UeAQd1XhR4w+tmTagvxXTMLUXdhYW4Gg3Sv83LPiDQ5GH0Jh2/jny0VsDLcMIARhxYX4SUnMQT2dru4VVeyrcvaT2rUlwhbyFdDXAN6rCn/nNNpqjG6+Zx0FdPxXNa+AcxrDRTbZR/ipE4iAIL0hMefXeVacdKBCerMs0do7wjH0I8FQxVyrwm4C4Vvm2Ju6lvvHDUTE8U0oDNCXv9hfpcPBJzWrrYmttFEvvhCIU/Xv");
//        taxPayerRegistry.addPerson(test.getNic(),test.getName()).send();
//        taxPayerRegistry.addCompany(test.getNic(),test.getName()).send();
    }
    @Override
    public void test2(String nic) throws Exception {
//        Tuple2<String, String> personByNIC = taxPayerRegistry.getPersonByNIC(nic).send();
//        System.out.println("1."+personByNIC.getValue1()+" "+personByNIC.getValue2());
//        Tuple2<String, String> send1 = taxPayerRegistry.getCompanyByRegistrationNumber(nic).send();
//        System.out.println("2."+send1.getValue1()+" "+send1.getValue2());
//        Tuple2<List, List> send = taxPayerRegistry.getAllPersons().send();
//        System.out.println("1."+send);
//        Tuple2<List, List> send2 = taxPayerRegistry.getAllCompanies().send();
//        System.out.println("2."+send2);

    }
    @Override
    public List<ResponsePersonByBlockchainDTO> getAllPersons(String encryptedToken) {
        try {
            Credentials credentials = Credentials.create( jwtUtils.getClaims(decrypt(encryptedToken)).get("pkey", String.class));
            Web3j web3j = Web3j.build(new HttpService(testNetURL));
            ContractGasProvider contractGasProvider = new DefaultGasProvider();
            TaxPayerRegistry taxPayerRegistry = TaxPayerRegistry.load(contractAddress,web3j,credentials,contractGasProvider);
            Tuple2<List, List> send = taxPayerRegistry.getAllPersons().send();
            List<Utf8String> nicList = send.component1();
            List<Utf8String> nameWithInitialsList = send.component2();
            List<ResponsePersonByBlockchainDTO> responsePersonByBlockchainDTOList = new ArrayList<>();
            for (int i = 0; i < nicList.size(); i++) {
                ResponsePersonByBlockchainDTO person = new ResponsePersonByBlockchainDTO();
                person.setNic(nicList.get(i).getValue());
                person.setNameWithInitials(nameWithInitialsList.get(i).getValue());
                responsePersonByBlockchainDTOList.add(person);
            }
            return responsePersonByBlockchainDTOList;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BlockChainFetchException();
        }
    }
    @Override
    public List<ResponseCompanyByBlockchain> getAllCompanies(String encryptedToken) {
        try {
            Credentials credentials = Credentials.create( jwtUtils.getClaims(decrypt(encryptedToken)).get("pkey", String.class));
            Web3j web3j = Web3j.build(new HttpService(testNetURL));
            ContractGasProvider contractGasProvider = new DefaultGasProvider();
            TaxPayerRegistry taxPayerRegistry = TaxPayerRegistry.load(contractAddress,web3j,credentials,contractGasProvider);
            Tuple2<List, List> send = taxPayerRegistry.getAllCompanies().send();
            List<Utf8String> regNumList = send.getValue1();
            List<Utf8String> companyNameList = send.getValue2();
            List<ResponseCompanyByBlockchain> responseCompanyByBlockchainList = new ArrayList<>();
            for (int i=0;i< regNumList.size();i++){
                ResponseCompanyByBlockchain company = new ResponseCompanyByBlockchain();
                company.setRegistrationNumber(regNumList.get(i).getValue());
                company.setCompanyName(companyNameList.get(i).getValue());
                responseCompanyByBlockchainList.add(company);
            }
            return responseCompanyByBlockchainList;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BlockChainFetchException();
        }
    }

//    private TaxPayerRegistry loadContract(String encryptedToken){
//        try {
//            Credentials credentials = Credentials.create("3ffab34a425107f0cd77f179ae370d71bda548c04954e419bd04bb412d1fd5fb");
//            Web3j web3j = Web3j.build(new HttpService(testNetURL));
//            ContractGasProvider contractGasProvider = new DefaultGasProvider();
//            TaxPayerRegistry taxPayerRegistry = TaxPayerRegistry.load(contractAddress,web3j,credentials,contractGasProvider);
//            return taxPayerRegistry;
//        }catch (Exception e){
//            log.error(e.getMessage());
//            throw new BlockChainFetchException();
//        }
//    }
    @Override
    public void deployContract() {
//        try {
//            Credentials credentials = Credentials.create("3ffab34a425107f0cd77f179ae370d71bda548c04954e419bd04bb412d1fd5fb");
//            Web3j web3j = Web3j.build(new HttpService(testNetURL));
//            ContractGasProvider contractGasProvider = new DefaultGasProvider();
//            TaxPayerRegistry taxPayerRegistry = TaxPayerRegistry.deploy(web3j,credentials,contractGasProvider).send();
//            contractAddress = taxPayerRegistry.getContractAddress();
//            System.out.println(contractAddress);
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }
    }

    private static String decrypt(String ciphertext) {

        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);

            // Decode Base64
            byte[] combined = Base64.getDecoder().decode(ciphertext);

            // Extract IV and encrypted data
            byte[] iv = new byte[16]; // Assuming 16 bytes IV for AES
            System.arraycopy(combined, 0, iv, 0, iv.length);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            byte[] encryptedBytes = new byte[combined.length - iv.length];
            System.arraycopy(combined, iv.length, encryptedBytes, 0, encryptedBytes.length);

            // Decrypt the ciphertext
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
