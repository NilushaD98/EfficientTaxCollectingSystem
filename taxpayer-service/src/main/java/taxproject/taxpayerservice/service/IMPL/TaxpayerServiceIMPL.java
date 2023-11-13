package taxproject.taxpayerservice.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.Tuple;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import taxproject.taxpayerservice.config.PersonRegistry;
import taxproject.taxpayerservice.dto.request.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerCompanyDTO;
import taxproject.taxpayerservice.dto.request.RequestAddNewTaxpayerPersonDTO;
import taxproject.taxpayerservice.dto.request.Test;
import taxproject.taxpayerservice.dto.response.ResponseCompanyForTaxPayingDTO;
import taxproject.taxpayerservice.dto.response.ResponsePersonForTaxPayingDTO;
import taxproject.taxpayerservice.entity.*;
import taxproject.taxpayerservice.exception.CompanyRegistrationNumberWrongException;
import taxproject.taxpayerservice.exception.PersonNotInDatabaseException;
import taxproject.taxpayerservice.repo.*;
import taxproject.taxpayerservice.service.TaxpayerService;
import taxproject.taxpayerservice.util.mappers.CompanyTypeMapper;


@Service
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

    private int companyBlockChainID = 0;
    private int personBlockChainID = 0;
    @Override
    public String addNewCompanyType(RequestAddCompanyTypeDTO requestAddCompanyTypeDTO) {
        return companyTypeRepo.save(companyTypeMapper.DTOToEntity(requestAddCompanyTypeDTO)).getCompanyType() +" saved successfully.";
    }
    @Override
    public String registerNewPerson(RequestAddNewTaxpayerPersonDTO requestAddNewTaxpayerPersonDTO) {
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


        return personRepo.save(person).getNameWithInitials() +" saved.";
    }
    @Override
    public ResponseCompanyForTaxPayingDTO getCompanyByRegNum(String registerNumber) {
        Company company = companyRepo.getCompaniesByRegistrationNumberEquals(registerNumber);
        if(company != null){
            //need check is it available on blockchain

            return new ResponseCompanyForTaxPayingDTO(
                    company.getCompanyID(),
                    company.getRegistrationNumber(),
                    company.getCompanyName(),
                    company.getBlockChainID()
            );
        }else {
            throw new CompanyRegistrationNumberWrongException();
        }
    }
    @Override
    public ResponsePersonForTaxPayingDTO getPersonByNIC(String nic) {
        Person person = personRepo.getPersonByNicEquals(nic);
        if(person != null){
            //need check he is in available in blockchain
            return new ResponsePersonForTaxPayingDTO(
                    person.getPersonID(),
                    person.getNic(),
                    person.getNameWithInitials(),
                    person.getBlockChainID()
            );
        }else{
            throw new PersonNotInDatabaseException();
        }
    }

    @Override
    public void test(Test test) throws Exception {
        String privateKey = "6e81f5778c39f67fa1a334bafd64b7d6ae8373543a03913a112df1ffb972c946";
        Credentials credentials = Credentials.create(privateKey);
        Web3j web3j = Web3j.build(new HttpService("https://sepolia.infura.io/v3/88c0c2df599940a4b3131bd5f5d6d965"));
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        PersonRegistry personRegistry = PersonRegistry.load("0x4a3bc68fccb9a49f6423faf20a22800790d6a547",web3j,credentials,contractGasProvider);
        personRegistry.addPerson(test.getNic(),test.getName()).send();

    }

    @Override
    public void test2() throws Exception {
        String privateKey = "6e81f5778c39f67fa1a334bafd64b7d6ae8373543a03913a112df1ffb972c946";
        Credentials credentials = Credentials.create(privateKey);
        Web3j web3j = Web3j.build(new HttpService("https://sepolia.infura.io/v3/88c0c2df599940a4b3131bd5f5d6d965"));
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        PersonRegistry personRegistry = PersonRegistry.load("0x4a3bc68fccb9a49f6423faf20a22800790d6a547",web3j,credentials,contractGasProvider);
        Tuple2<String, String> personByNIC = personRegistry.getPersonByNIC("1234").send();
        System.out.println(personByNIC.getValue1());
    }
    private Test mappinf(Tuple2<String, String> tuple2){
        return new Test(tuple2.component1(), tuple2.component2());
    }

    @Override
    public String registerNewCompany(RequestAddNewTaxpayerCompanyDTO requestAddNewTaxpayerCompanyDTO) {

        Director director = new Director(
                requestAddNewTaxpayerCompanyDTO.getNICOrPassportNo(),
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
                requestAddNewTaxpayerCompanyDTO.getBOIRegisterStatus(),
                requestAddNewTaxpayerCompanyDTO.getBOIStartDate(),
                requestAddNewTaxpayerCompanyDTO.getBOIExpiryDate(),
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

        return companyRepo.save(company).getCompanyName()+" saved.";
    }

}
