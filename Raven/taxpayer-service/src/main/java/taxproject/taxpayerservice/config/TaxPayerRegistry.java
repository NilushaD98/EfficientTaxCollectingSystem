package taxproject.taxpayerservice.config;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.5.
 */
@SuppressWarnings("rawtypes")
public class TaxPayerRegistry extends Contract {
    private static final String BINARY = "608060405234801561000f575f80fd5b50610e4f8061001d5f395ff3fe608060405234801561000f575f80fd5b5060043610610060575f3560e01c806322f5b58d14610064578063408bf4c31461008a5780636559c310146100a0578063739efb78146100b35780637fa4ba87146100d4578063a459cc27146100dc575b5f80fd5b610077610072366004610a31565b6100ef565b6040519081526020015b60405180910390f35b610092610141565b604051610081929190610b36565b6100776100ae366004610a31565b61037d565b6100c66100c1366004610b63565b6103be565b604051610081929190610b9d565b61009261059c565b6100c66100ea366004610b63565b6107cc565b5f806100fb848461086f565b90507f3d965559aa104be56fef3bda0cc9fad160cfb3e3d4976851e6585a45999b6e2981858560405161013093929190610bc1565b60405180910390a190505b92915050565b6060805f60015460016101549190610c09565b90505f8167ffffffffffffffff81111561017057610170610994565b6040519080825280602002602001820160405280156101a357816020015b606081526020019060019003908161018e5790505b5090505f8267ffffffffffffffff8111156101c0576101c0610994565b6040519080825280602002602001820160405280156101f357816020015b60608152602001906001900390816101de5790505b50905060015b83811015610372575f818152600560205260409020805461021990610c1c565b80601f016020809104026020016040519081016040528092919081815260200182805461024590610c1c565b80156102905780601f1061026757610100808354040283529160200191610290565b820191905f5260205f20905b81548152906001019060200180831161027357829003601f168201915b50505050508382815181106102a7576102a7610c54565b602002602001018190525060055f8281526020019081526020015f2060010180546102d190610c1c565b80601f01602080910402602001604051908101604052809291908181526020018280546102fd90610c1c565b80156103485780601f1061031f57610100808354040283529160200191610348565b820191905f5260205f20905b81548152906001019060200180831161032b57829003601f168201915b505050505082828151811061035f5761035f610c54565b60209081029190910101526001016101f9565b509094909350915050565b5f806103898484610911565b90507f0c3eda347ada2142a6e57b7c01d3bdc6270951033ebc69b30dbfcd40e293dca181858560405161013093929190610bc1565b6060805f805460016103d09190610c09565b90505f5b8181101561057357846040516020016103ed9190610c68565b60408051601f1981840301815282825280516020918201205f8581526004835292909220919261041e929101610c83565b604051602081830303815290604052805190602001200361056b575f81815260046020526040902080546001820190829061045890610c1c565b80601f016020809104026020016040519081016040528092919081815260200182805461048490610c1c565b80156104cf5780601f106104a6576101008083540402835291602001916104cf565b820191905f5260205f20905b8154815290600101906020018083116104b257829003601f168201915b505050505091508080546104e290610c1c565b80601f016020809104026020016040519081016040528092919081815260200182805461050e90610c1c565b80156105595780601f1061053057610100808354040283529160200191610559565b820191905f5260205f20905b81548152906001019060200180831161053c57829003601f168201915b50505050509050935093505050915091565b6001016103d4565b5060405180602001604052805f81525060405180602001604052805f8152509250925050915091565b6060805f805460016105ae9190610c09565b90505f8167ffffffffffffffff8111156105ca576105ca610994565b6040519080825280602002602001820160405280156105fd57816020015b60608152602001906001900390816105e85790505b5090505f8267ffffffffffffffff81111561061a5761061a610994565b60405190808252806020026020018201604052801561064d57816020015b60608152602001906001900390816106385790505b50905060015b83811015610372575f818152600460205260409020805461067390610c1c565b80601f016020809104026020016040519081016040528092919081815260200182805461069f90610c1c565b80156106ea5780601f106106c1576101008083540402835291602001916106ea565b820191905f5260205f20905b8154815290600101906020018083116106cd57829003601f168201915b505050505083828151811061070157610701610c54565b602002602001018190525060045f8281526020019081526020015f20600101805461072b90610c1c565b80601f016020809104026020016040519081016040528092919081815260200182805461075790610c1c565b80156107a25780601f10610779576101008083540402835291602001916107a2565b820191905f5260205f20905b81548152906001019060200180831161078557829003601f168201915b50505050508282815181106107b9576107b9610c54565b6020908102919091010152600101610653565b6060805f60015460016107df9190610c09565b90505f5b8181101561057357846040516020016107fc9190610c68565b60408051601f1981840301815282825280516020918201205f8581526005835292909220919261082d929101610c83565b6040516020818303038152906040528051906020012003610867575f81815260056020526040902080546001820190829061045890610c1c565b6001016107e3565b5f805f80815461087e90610cf5565b9182905550600280546001810182555f919091529091507f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ace016108c18582610d59565b5060408051808201825285815260208082018690525f848152600490915291909120815181906108f19082610d59565b50602082015160018201906109069082610d59565b509195945050505050565b5f8060015f815461092190610cf5565b9182905550600380546001810182555f919091529091507fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b016109648582610d59565b5060408051808201825285815260208082018690525f848152600590915291909120815181906108f19082610d59565b634e487b7160e01b5f52604160045260245ffd5b5f82601f8301126109b7575f80fd5b813567ffffffffffffffff808211156109d2576109d2610994565b604051601f8301601f19908116603f011681019082821181831017156109fa576109fa610994565b81604052838152866020858801011115610a12575f80fd5b836020870160208301375f602085830101528094505050505092915050565b5f8060408385031215610a42575f80fd5b823567ffffffffffffffff80821115610a59575f80fd5b610a65868387016109a8565b93506020850135915080821115610a7a575f80fd5b50610a87858286016109a8565b9150509250929050565b5f5b83811015610aab578181015183820152602001610a93565b50505f910152565b5f8151808452610aca816020860160208601610a91565b601f01601f19169290920160200192915050565b5f8282518085526020808601955060208260051b840101602086015f5b84811015610b2957601f19868403018952610b17838351610ab3565b98840198925090830190600101610afb565b5090979650505050505050565b604081525f610b486040830185610ade565b8281036020840152610b5a8185610ade565b95945050505050565b5f60208284031215610b73575f80fd5b813567ffffffffffffffff811115610b89575f80fd5b610b95848285016109a8565b949350505050565b604081525f610baf6040830185610ab3565b8281036020840152610b5a8185610ab3565b838152606060208201525f610bd96060830185610ab3565b8281036040840152610beb8185610ab3565b9695505050505050565b634e487b7160e01b5f52601160045260245ffd5b8082018082111561013b5761013b610bf5565b600181811c90821680610c3057607f821691505b602082108103610c4e57634e487b7160e01b5f52602260045260245ffd5b50919050565b634e487b7160e01b5f52603260045260245ffd5b5f8251610c79818460208701610a91565b9190910192915050565b5f808354610c9081610c1c565b60018281168015610ca85760018114610cbd57610ce9565b60ff1984168752821515830287019450610ce9565b875f526020805f205f5b85811015610ce05781548a820152908401908201610cc7565b50505082870194505b50929695505050505050565b5f60018201610d0657610d06610bf5565b5060010190565b601f821115610d5457805f5260205f20601f840160051c81016020851015610d325750805b601f840160051c820191505b81811015610d51575f8155600101610d3e565b50505b505050565b815167ffffffffffffffff811115610d7357610d73610994565b610d8781610d818454610c1c565b84610d0d565b602080601f831160018114610dba575f8415610da35750858301515b5f19600386901b1c1916600185901b178555610e11565b5f85815260208120601f198616915b82811015610de857888601518255948401946001909101908401610dc9565b5085821015610e0557878501515f19600388901b60f8161c191681555b505060018460011b0185555b50505050505056fea2646970667358221220a329176b5b485246c247b605f59b90a8e32f26a8256190041caeee1d98fc8b6464736f6c63430008160033";

    public static final String FUNC_ADDCOMPANY = "addCompany";

    public static final String FUNC_ADDPERSON = "addPerson";

    public static final String FUNC_GETALLCOMPANIES = "getAllCompanies";

    public static final String FUNC_GETALLPERSONS = "getAllPersons";

    public static final String FUNC_GETCOMPANYBYREGISTRATIONNUMBER = "getCompanyByRegistrationNumber";

    public static final String FUNC_GETPERSONBYNIC = "getPersonByNIC";

    public static final Event COMPANYADDED_EVENT = new Event("CompanyAdded",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event PERSONADDED_EVENT = new Event("PersonAdded",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected TaxPayerRegistry(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TaxPayerRegistry(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TaxPayerRegistry(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TaxPayerRegistry(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }
    public List<CompanyAddedEventResponse> getCompanyAddedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(COMPANYADDED_EVENT, transactionReceipt);
        ArrayList<CompanyAddedEventResponse> responses = new ArrayList<CompanyAddedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CompanyAddedEventResponse typedResponse = new CompanyAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.cID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.registrationNumber = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.companyName = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }
    public Flowable<CompanyAddedEventResponse> companyAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CompanyAddedEventResponse>() {
            @Override
            public CompanyAddedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(COMPANYADDED_EVENT, log);
                CompanyAddedEventResponse typedResponse = new CompanyAddedEventResponse();
                typedResponse.log = log;
                typedResponse.cID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.registrationNumber = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.companyName = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }
    public Flowable<CompanyAddedEventResponse> companyAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(COMPANYADDED_EVENT));
        return companyAddedEventFlowable(filter);
    }
    public List<PersonAddedEventResponse> getPersonAddedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PERSONADDED_EVENT, transactionReceipt);
        ArrayList<PersonAddedEventResponse> responses = new ArrayList<PersonAddedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PersonAddedEventResponse typedResponse = new PersonAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.personID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.nic = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.nameWithInitials = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }
    public Flowable<PersonAddedEventResponse> personAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PersonAddedEventResponse>() {
            @Override
            public PersonAddedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PERSONADDED_EVENT, log);
                PersonAddedEventResponse typedResponse = new PersonAddedEventResponse();
                typedResponse.log = log;
                typedResponse.personID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.nic = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.nameWithInitials = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }
    public Flowable<PersonAddedEventResponse> personAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PERSONADDED_EVENT));
        return personAddedEventFlowable(filter);
    }
    public RemoteFunctionCall<TransactionReceipt> addCompany(String _registrationNumber, String _companyName) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDCOMPANY,
                Arrays.<Type>asList(new Utf8String(_registrationNumber),
                        new Utf8String(_companyName)),
                Collections.<TypeReference<?>>emptyList());
        System.out.println("3");
        return executeRemoteCallTransaction(function);
    }
    public RemoteFunctionCall<TransactionReceipt> addPerson(String _nic, String _nameWithInitials) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDPERSON,
                Arrays.<Type>asList(new Utf8String(_nic),
                        new Utf8String(_nameWithInitials)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }
    public RemoteFunctionCall<Tuple2<List,List>> getAllCompanies() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETALLCOMPANIES,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<Tuple2<List,List>>(
                function, new Callable<Tuple2<List, List>>() {
            public Tuple2<List,List> call() throws Exception {
                List<Type> results = executeCallMultipleValueReturn(function);
                DynamicArray<Utf8String> array1 = (DynamicArray<Utf8String>) results.get(0);
                DynamicArray<Utf8String> array2 = (DynamicArray<Utf8String>) results.get(1);

                List<Utf8String> resultList1 = array1.getValue();
                List<Utf8String> resultList2 = array2.getValue();
                return new Tuple2<>(resultList1,resultList2);
            }
        }
        );
    }
    public RemoteFunctionCall<Tuple2<List,List>> getAllPersons() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETALLPERSONS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<Tuple2<List,List>>(
                function, new Callable<Tuple2<List, List>>() {
            public Tuple2<List,List> call() throws Exception {
                List<Type> results = executeCallMultipleValueReturn(function);
                DynamicArray<Utf8String> array1 = (DynamicArray<Utf8String>) results.get(0);
                DynamicArray<Utf8String> array2 = (DynamicArray<Utf8String>) results.get(1);

                List<Utf8String> resultList1 = array1.getValue();
                List<Utf8String> resultList2 = array2.getValue();
                return new Tuple2<>(resultList1,resultList2);
            }
        }
        );
    }
    public RemoteFunctionCall<Tuple2<String,String>> getCompanyByRegistrationNumber(String _registrationNumber) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETCOMPANYBYREGISTRATIONNUMBER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_registrationNumber)),
                Arrays.<TypeReference<?>>asList( new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));

        return new RemoteFunctionCall<Tuple2<String,String>>(function,
                new Callable<Tuple2<String,String>>(){
                    @Override
                    public Tuple2<String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, String>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue());
                    }
                });
    }
    public RemoteFunctionCall<Tuple2<String,String>> getPersonByNIC(String _nic) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETPERSONBYNIC,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_nic)),
                Arrays.<TypeReference<?>>asList( new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));


        return new RemoteFunctionCall<Tuple2<String,String>>(function,
                new Callable<Tuple2<String,String>>(){
                    @Override
                    public Tuple2<String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, String>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue());
                    }
                });
    }
    @Deprecated
    public static TaxPayerRegistry load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TaxPayerRegistry(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }
    @Deprecated
    public static TaxPayerRegistry load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TaxPayerRegistry(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
    public static TaxPayerRegistry load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TaxPayerRegistry(contractAddress, web3j, credentials, contractGasProvider);
    }
    public static TaxPayerRegistry load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TaxPayerRegistry(contractAddress, web3j, transactionManager, contractGasProvider);
    }
    public static RemoteCall<TaxPayerRegistry> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TaxPayerRegistry.class, web3j, credentials, contractGasProvider, BINARY, "");
    }
    @Deprecated
    public static RemoteCall<TaxPayerRegistry> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TaxPayerRegistry.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }
    public static RemoteCall<TaxPayerRegistry> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TaxPayerRegistry.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }
    @Deprecated
    public static RemoteCall<TaxPayerRegistry> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TaxPayerRegistry.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
    public static class CompanyAddedEventResponse extends BaseEventResponse {
        public BigInteger cID;

        public String registrationNumber;

        public String companyName;
    }
    public static class PersonAddedEventResponse extends BaseEventResponse {
        public BigInteger personID;

        public String nic;

        public String nameWithInitials;
    }
}