package taxproject.taxpayingservice.config;

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
import org.web3j.abi.datatypes.*;
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
import org.web3j.tuples.generated.Tuple6;
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
public class TaxPayingService extends Contract {
    private static final String BINARY = "608060405234801561000f575f80fd5b506121838061001d5f395ff3fe608060405234801561000f575f80fd5b5060043610610085575f3560e01c806377ae0f741161005857806377ae0f74146100fa578063b66e64a41461011f578063d168e7f514610148578063fecb745f1461015b575f80fd5b80630a9deb02146100895780631dc7bd2f146100af5780632ac26c19146100d45780636f1690b2146100e7575b5f80fd5b61009c610097366004611a59565b61016e565b6040519081526020015b60405180910390f35b6100c26100bd366004611b9f565b6102f6565b6040516100a696959493929190611c25565b61009c6100e2366004611a59565b6105f5565b6100c26100f5366004611b9f565b6106cc565b61010d610108366004611b9f565b610798565b6040516100a696959493929190611ce2565b61013261012d366004611d89565b610e6b565b6040516100a69a99989796959493929190611da0565b61010d610156366004611b9f565b6112e7565b610132610169366004611d89565b61199c565b5f805f80815461017d90611e72565b91905081905590507f0cf6eb12e42a153eb8b2a02329344bf31e0a4e157d0a57cd2ba91171913b3550818d8d8d8d8d8d8d8d8d8d6040516101c89b9a99989796959493929190611e8a565b60405180910390a16040518061014001604052808d81526020018c81526020018b81526020018a81526020018981526020018881526020018781526020018681526020018581526020018481525060025f8381526020019081526020015f205f820151815f015560208201518160010190816102449190611fd4565b50604082015160028201906102599082611fd4565b506060820151600382019061026e9082611fd4565b50608082015160048201906102839082611fd4565b5060a082015160058201906102989082611fd4565b5060c082015160068201906102ad9082611fd4565b5060e0820151600782015561010082015160088201906102cd9082611fd4565b5061012082015160098201906102e39082611fd4565b50919d9c50505050505050505050505050565b5f60608060605f60605f8054600161030e9190612093565b90505f805b82811015610387578960405160200161032c91906120ac565b60408051601f1981840301815282825280516020918201205f858152600283529290922091926103609260010191016120c7565b604051602081830303815290604052805190602001200361037f578091505b600101610313565b505f60025f8381526020019081526020015f209050805f01548160020182600301836006018460070154856009018480546103c190611f50565b80601f01602080910402602001604051908101604052809291908181526020018280546103ed90611f50565b80156104385780601f1061040f57610100808354040283529160200191610438565b820191905f5260205f20905b81548152906001019060200180831161041b57829003601f168201915b5050505050945083805461044b90611f50565b80601f016020809104026020016040519081016040528092919081815260200182805461047790611f50565b80156104c25780601f10610499576101008083540402835291602001916104c2565b820191905f5260205f20905b8154815290600101906020018083116104a557829003601f168201915b505050505093508280546104d590611f50565b80601f016020809104026020016040519081016040528092919081815260200182805461050190611f50565b801561054c5780601f106105235761010080835404028352916020019161054c565b820191905f5260205f20905b81548152906001019060200180831161052f57829003601f168201915b5050505050925080805461055f90611f50565b80601f016020809104026020016040519081016040528092919081815260200182805461058b90611f50565b80156105d65780601f106105ad576101008083540402835291602001916105d6565b820191905f5260205f20905b8154815290600101906020018083116105b957829003601f168201915b5050505050905098509850985098509850985050505091939550919395565b5f8060015f815461060590611e72565b91905081905590507f0bd173a2c945e4216c833edc7bc015b6151b938e9569cbf4f6c33d9aa841b397818d8d8d8d8d8d8d8d8d8d6040516106509b9a99989796959493929190611e8a565b60405180910390a16040518061014001604052808d81526020018c81526020018b81526020018a81526020018981526020018881526020018781526020018681526020018581526020018481525060035f8381526020019081526020015f205f820151815f015560208201518160010190816102449190611fd4565b5f60608060605f60605f60015460016106e59190612093565b90505f805b8281101561075e578960405160200161070391906120ac565b60408051601f1981840301815282825280516020918201205f858152600383529290922091926107379260010191016120c7565b6040516020818303038152906040528051906020012003610756578091505b6001016106ea565b505f60035f8381526020019081526020015f209050805f01548160020182600301836006018460070154856009018480546103c190611f50565b6060806060806060805f60015460016107b19190612093565b9050875f805b8381101561083557826040516020016107d091906120ac565b60408051601f1981840301815282825280516020918201205f858152600383529290922091926108049260040191016120c7565b604051602081830303815290604052805190602001200361082d5761082a600183612093565b91505b6001016107b7565b505f816001600160401b0381111561084f5761084f6119bd565b60405190808252806020026020018201604052801561088257816020015b606081526020019060019003908161086d5790505b5090505f826001600160401b0381111561089e5761089e6119bd565b6040519080825280602002602001820160405280156108d157816020015b60608152602001906001900390816108bc5790505b5090505f836001600160401b038111156108ed576108ed6119bd565b60405190808252806020026020018201604052801561092057816020015b606081526020019060019003908161090b5790505b5090505f846001600160401b0381111561093c5761093c6119bd565b60405190808252806020026020018201604052801561096f57816020015b606081526020019060019003908161095a5790505b5090505f856001600160401b0381111561098b5761098b6119bd565b6040519080825280602002602001820160405280156109b4578160200160208202803683370190505b5090505f866001600160401b038111156109d0576109d06119bd565b604051908082528060200260200182016040528015610a0357816020015b60608152602001906001900390816109ee5790505b5090505f805b8a811015610e4e5789604051602001610a2291906120ac565b60408051601f1981840301815282825280516020918201205f85815260038352929092209192610a569260040191016120c7565b6040516020818303038152906040528051906020012003610e46575f8181526003602052604090206001018054610a8c90611f50565b80601f0160208091040260200160405190810160405280929190818152602001828054610ab890611f50565b8015610b035780601f10610ada57610100808354040283529160200191610b03565b820191905f5260205f20905b815481529060010190602001808311610ae657829003601f168201915b5050505050888381518110610b1a57610b1a612139565b602002602001018190525060035f8281526020019081526020015f206002018054610b4490611f50565b80601f0160208091040260200160405190810160405280929190818152602001828054610b7090611f50565b8015610bbb5780601f10610b9257610100808354040283529160200191610bbb565b820191905f5260205f20905b815481529060010190602001808311610b9e57829003601f168201915b5050505050878381518110610bd257610bd2612139565b602002602001018190525060035f8281526020019081526020015f206003018054610bfc90611f50565b80601f0160208091040260200160405190810160405280929190818152602001828054610c2890611f50565b8015610c735780601f10610c4a57610100808354040283529160200191610c73565b820191905f5260205f20905b815481529060010190602001808311610c5657829003601f168201915b5050505050868381518110610c8a57610c8a612139565b602002602001018190525060035f8281526020019081526020015f206006018054610cb490611f50565b80601f0160208091040260200160405190810160405280929190818152602001828054610ce090611f50565b8015610d2b5780601f10610d0257610100808354040283529160200191610d2b565b820191905f5260205f20905b815481529060010190602001808311610d0e57829003601f168201915b5050505050858381518110610d4257610d42612139565b602002602001018190525060035f8281526020019081526020015f2060070154848381518110610d7457610d74612139565b60200260200101818152505060035f8281526020019081526020015f206009018054610d9f90611f50565b80601f0160208091040260200160405190810160405280929190818152602001828054610dcb90611f50565b8015610e165780601f10610ded57610100808354040283529160200191610e16565b820191905f5260205f20905b815481529060010190602001808311610df957829003601f168201915b5050505050838381518110610e2d57610e2d612139565b6020908102919091010152610e43600183612093565b91505b600101610a09565b50959e50939c50919a509850965094505050505091939550919395565b60026020525f908152604090208054600182018054919291610e8c90611f50565b80601f0160208091040260200160405190810160405280929190818152602001828054610eb890611f50565b8015610f035780601f10610eda57610100808354040283529160200191610f03565b820191905f5260205f20905b815481529060010190602001808311610ee657829003601f168201915b505050505090806002018054610f1890611f50565b80601f0160208091040260200160405190810160405280929190818152602001828054610f4490611f50565b8015610f8f5780601f10610f6657610100808354040283529160200191610f8f565b820191905f5260205f20905b815481529060010190602001808311610f7257829003601f168201915b505050505090806003018054610fa490611f50565b80601f0160208091040260200160405190810160405280929190818152602001828054610fd090611f50565b801561101b5780601f10610ff25761010080835404028352916020019161101b565b820191905f5260205f20905b815481529060010190602001808311610ffe57829003601f168201915b50505050509080600401805461103090611f50565b80601f016020809104026020016040519081016040528092919081815260200182805461105c90611f50565b80156110a75780601f1061107e576101008083540402835291602001916110a7565b820191905f5260205f20905b81548152906001019060200180831161108a57829003601f168201915b5050505050908060050180546110bc90611f50565b80601f01602080910402602001604051908101604052809291908181526020018280546110e890611f50565b80156111335780601f1061110a57610100808354040283529160200191611133565b820191905f5260205f20905b81548152906001019060200180831161111657829003601f168201915b50505050509080600601805461114890611f50565b80601f016020809104026020016040519081016040528092919081815260200182805461117490611f50565b80156111bf5780601f10611196576101008083540402835291602001916111bf565b820191905f5260205f20905b8154815290600101906020018083116111a257829003601f168201915b5050505050908060070154908060080180546111da90611f50565b80601f016020809104026020016040519081016040528092919081815260200182805461120690611f50565b80156112515780601f1061122857610100808354040283529160200191611251565b820191905f5260205f20905b81548152906001019060200180831161123457829003601f168201915b50505050509080600901805461126690611f50565b80601f016020809104026020016040519081016040528092919081815260200182805461129290611f50565b80156112dd5780601f106112b4576101008083540402835291602001916112dd565b820191905f5260205f20905b8154815290600101906020018083116112c057829003601f168201915b505050505090508a565b6060806060806060805f805460016112ff9190612093565b9050875f805b83811015611383578260405160200161131e91906120ac565b60408051601f1981840301815282825280516020918201205f858152600283529290922091926113529260040191016120c7565b604051602081830303815290604052805190602001200361137b57611378600183612093565b91505b600101611305565b505f816001600160401b0381111561139d5761139d6119bd565b6040519080825280602002602001820160405280156113d057816020015b60608152602001906001900390816113bb5790505b5090505f826001600160401b038111156113ec576113ec6119bd565b60405190808252806020026020018201604052801561141f57816020015b606081526020019060019003908161140a5790505b5090505f836001600160401b0381111561143b5761143b6119bd565b60405190808252806020026020018201604052801561146e57816020015b60608152602001906001900390816114595790505b5090505f846001600160401b0381111561148a5761148a6119bd565b6040519080825280602002602001820160405280156114bd57816020015b60608152602001906001900390816114a85790505b5090505f856001600160401b038111156114d9576114d96119bd565b604051908082528060200260200182016040528015611502578160200160208202803683370190505b5090505f866001600160401b0381111561151e5761151e6119bd565b60405190808252806020026020018201604052801561155157816020015b606081526020019060019003908161153c5790505b5090505f805b8a811015610e4e578960405160200161157091906120ac565b60408051601f1981840301815282825280516020918201205f858152600283529290922091926115a49260040191016120c7565b6040516020818303038152906040528051906020012003611994575f81815260026020526040902060010180546115da90611f50565b80601f016020809104026020016040519081016040528092919081815260200182805461160690611f50565b80156116515780601f1061162857610100808354040283529160200191611651565b820191905f5260205f20905b81548152906001019060200180831161163457829003601f168201915b505050505088838151811061166857611668612139565b602002602001018190525060025f8281526020019081526020015f20600201805461169290611f50565b80601f01602080910402602001604051908101604052809291908181526020018280546116be90611f50565b80156117095780601f106116e057610100808354040283529160200191611709565b820191905f5260205f20905b8154815290600101906020018083116116ec57829003601f168201915b505050505087838151811061172057611720612139565b602002602001018190525060025f8281526020019081526020015f20600301805461174a90611f50565b80601f016020809104026020016040519081016040528092919081815260200182805461177690611f50565b80156117c15780601f10611798576101008083540402835291602001916117c1565b820191905f5260205f20905b8154815290600101906020018083116117a457829003601f168201915b50505050508683815181106117d8576117d8612139565b602002602001018190525060025f8281526020019081526020015f20600601805461180290611f50565b80601f016020809104026020016040519081016040528092919081815260200182805461182e90611f50565b80156118795780601f1061185057610100808354040283529160200191611879565b820191905f5260205f20905b81548152906001019060200180831161185c57829003601f168201915b505050505085838151811061189057611890612139565b602002602001018190525060025f8281526020019081526020015f20600701548483815181106118c2576118c2612139565b60200260200101818152505060025f8281526020019081526020015f2060090180546118ed90611f50565b80601f016020809104026020016040519081016040528092919081815260200182805461191990611f50565b80156119645780601f1061193b57610100808354040283529160200191611964565b820191905f5260205f20905b81548152906001019060200180831161194757829003601f168201915b505050505083838151811061197b5761197b612139565b6020908102919091010152611991600183612093565b91505b600101611557565b60036020525f908152604090208054600182018054919291610e8c90611f50565b634e487b7160e01b5f52604160045260245ffd5b5f82601f8301126119e0575f80fd5b81356001600160401b03808211156119fa576119fa6119bd565b604051601f8301601f19908116603f01168101908282118183101715611a2257611a226119bd565b81604052838152866020858801011115611a3a575f80fd5b836020870160208301375f602085830101528094505050505092915050565b5f805f805f805f805f806101408b8d031215611a73575f80fd5b8a35995060208b01356001600160401b0380821115611a90575f80fd5b611a9c8e838f016119d1565b9a5060408d0135915080821115611ab1575f80fd5b611abd8e838f016119d1565b995060608d0135915080821115611ad2575f80fd5b611ade8e838f016119d1565b985060808d0135915080821115611af3575f80fd5b611aff8e838f016119d1565b975060a08d0135915080821115611b14575f80fd5b611b208e838f016119d1565b965060c08d0135915080821115611b35575f80fd5b611b418e838f016119d1565b955060e08d013594506101008d0135915080821115611b5e575f80fd5b611b6a8e838f016119d1565b93506101208d0135915080821115611b80575f80fd5b50611b8d8d828e016119d1565b9150509295989b9194979a5092959850565b5f60208284031215611baf575f80fd5b81356001600160401b03811115611bc4575f80fd5b611bd0848285016119d1565b949350505050565b5f5b83811015611bf2578181015183820152602001611bda565b50505f910152565b5f8151808452611c11816020860160208601611bd8565b601f01601f19169290920160200192915050565b86815260c060208201525f611c3d60c0830188611bfa565b8281036040840152611c4f8188611bfa565b90508281036060840152611c638187611bfa565b905084608084015282810360a0840152611c7d8185611bfa565b9998505050505050505050565b5f8282518085526020808601955060208260051b840101602086015f5b84811015611cd557601f19868403018952611cc3838351611bfa565b98840198925090830190600101611ca7565b5090979650505050505050565b60c081525f611cf460c0830189611c8a565b60208382036020850152611d08828a611c8a565b91508382036040850152611d1c8289611c8a565b91508382036060850152611d308288611c8a565b8481036080860152865180825260208089019450909101905f5b81811015611d6657845183529383019391830191600101611d4a565b505084810360a0860152611d7a8187611c8a565b9b9a5050505050505050505050565b5f60208284031215611d99575f80fd5b5035919050565b5f6101408c8352806020840152611db98184018d611bfa565b90508281036040840152611dcd818c611bfa565b90508281036060840152611de1818b611bfa565b90508281036080840152611df5818a611bfa565b905082810360a0840152611e098189611bfa565b905082810360c0840152611e1d8188611bfa565b90508560e0840152828103610100840152611e388186611bfa565b9050828103610120840152611e4d8185611bfa565b9d9c50505050505050505050505050565b634e487b7160e01b5f52601160045260245ffd5b5f60018201611e8357611e83611e5e565b5060010190565b5f6101608d83528c6020840152806040840152611ea98184018d611bfa565b90508281036060840152611ebd818c611bfa565b90508281036080840152611ed1818b611bfa565b905082810360a0840152611ee5818a611bfa565b905082810360c0840152611ef98189611bfa565b905082810360e0840152611f0d8188611bfa565b905085610100840152828103610120840152611f298186611bfa565b9050828103610140840152611f3e8185611bfa565b9e9d5050505050505050505050505050565b600181811c90821680611f6457607f821691505b602082108103611f8257634e487b7160e01b5f52602260045260245ffd5b50919050565b601f821115611fcf57805f5260205f20601f840160051c81016020851015611fad5750805b601f840160051c820191505b81811015611fcc575f8155600101611fb9565b50505b505050565b81516001600160401b03811115611fed57611fed6119bd565b61200181611ffb8454611f50565b84611f88565b602080601f831160018114612034575f841561201d5750858301515b5f19600386901b1c1916600185901b17855561208b565b5f85815260208120601f198616915b8281101561206257888601518255948401946001909101908401612043565b508582101561207f57878501515f19600388901b60f8161c191681555b505060018460011b0185555b505050505050565b808201808211156120a6576120a6611e5e565b92915050565b5f82516120bd818460208701611bd8565b9190910192915050565b5f8083546120d481611f50565b600182811680156120ec57600181146121015761212d565b60ff198416875282151583028701945061212d565b875f526020805f205f5b858110156121245781548a82015290840190820161210b565b50505082870194505b50929695505050505050565b634e487b7160e01b5f52603260045260245ffdfea264697066735822122030e0215d141ac7b856b9ab229441dbd7fc9313b27b78187abf0b0a87658b344f64736f6c63430008160033";

    public static final String FUNC_COMPANYPAYMENTS = "CompanyPayments";

    public static final String FUNC_PERSONPAYMENTS = "PersonPayments";

    public static final String FUNC_CREDITCOMPANYPAYMENT = "creditCompanyPayment";

    public static final String FUNC_CREDITPERSONPAYMENT = "creditPersonPayment";

    public static final String FUNC_GETALLPAYMENTDETAILSBYNIC = "getAllPaymentDetailsByNIC";

    public static final String FUNC_GETALLPAYMENTDETAILSBYREGNUMBER = "getAllPaymentDetailsByRegNumber";

    public static final String FUNC_GETCOMPANYPAYMENTDETAILSBYINVOICENUMBER = "getCompanyPaymentDetailsByInvoiceNumber";

    public static final String FUNC_GETPERSONPAYMENTDETAILSBYINVOICENUMBER = "getPersonPaymentDetailsByInvoiceNumber";

    public static final Event CREDITCOMPANYPAYMENTADDED_EVENT = new Event("creditCompanyPaymentAdded",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event CREDITPERSONPAYMENTADDED_EVENT = new Event("creditPersonPaymentAdded",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected TaxPayingService(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TaxPayingService(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TaxPayingService(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TaxPayingService(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<CreditCompanyPaymentAddedEventResponse> getCreditCompanyPaymentAddedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CREDITCOMPANYPAYMENTADDED_EVENT, transactionReceipt);
        ArrayList<CreditCompanyPaymentAddedEventResponse> responses = new ArrayList<CreditCompanyPaymentAddedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CreditCompanyPaymentAddedEventResponse typedResponse = new CreditCompanyPaymentAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ppid = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.taxPayerRegistrationNumber = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.invoiceNumber = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.payeesNIC = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.payeesName = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.companyRegistrationNumber = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.companyName = (String) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.periodCode = (String) eventValues.getNonIndexedValues().get(7).getValue();
            typedResponse.installmentNumber = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
            typedResponse.paymentDescription = (String) eventValues.getNonIndexedValues().get(9).getValue();
            typedResponse.paidAmount = (String) eventValues.getNonIndexedValues().get(10).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CreditCompanyPaymentAddedEventResponse> creditCompanyPaymentAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CreditCompanyPaymentAddedEventResponse>() {
            @Override
            public CreditCompanyPaymentAddedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(CREDITCOMPANYPAYMENTADDED_EVENT, log);
                CreditCompanyPaymentAddedEventResponse typedResponse = new CreditCompanyPaymentAddedEventResponse();
                typedResponse.log = log;
                typedResponse.ppid = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.taxPayerRegistrationNumber = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.invoiceNumber = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.payeesNIC = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.payeesName = (String) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.companyRegistrationNumber = (String) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.companyName = (String) eventValues.getNonIndexedValues().get(6).getValue();
                typedResponse.periodCode = (String) eventValues.getNonIndexedValues().get(7).getValue();
                typedResponse.installmentNumber = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
                typedResponse.paymentDescription = (String) eventValues.getNonIndexedValues().get(9).getValue();
                typedResponse.paidAmount = (String) eventValues.getNonIndexedValues().get(10).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CreditCompanyPaymentAddedEventResponse> creditCompanyPaymentAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREDITCOMPANYPAYMENTADDED_EVENT));
        return creditCompanyPaymentAddedEventFlowable(filter);
    }

    public List<CreditPersonPaymentAddedEventResponse> getCreditPersonPaymentAddedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CREDITPERSONPAYMENTADDED_EVENT, transactionReceipt);
        ArrayList<CreditPersonPaymentAddedEventResponse> responses = new ArrayList<CreditPersonPaymentAddedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CreditPersonPaymentAddedEventResponse typedResponse = new CreditPersonPaymentAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ppid = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.taxPayerRegistrationNumber = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.invoiceNumber = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.payeesNIC = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.payeesName = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.payersNIC = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.payersName = (String) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.periodCode = (String) eventValues.getNonIndexedValues().get(7).getValue();
            typedResponse.installmentNumber = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
            typedResponse.paymentDescription = (String) eventValues.getNonIndexedValues().get(9).getValue();
            typedResponse.paidAmount = (String) eventValues.getNonIndexedValues().get(10).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CreditPersonPaymentAddedEventResponse> creditPersonPaymentAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CreditPersonPaymentAddedEventResponse>() {
            @Override
            public CreditPersonPaymentAddedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(CREDITPERSONPAYMENTADDED_EVENT, log);
                CreditPersonPaymentAddedEventResponse typedResponse = new CreditPersonPaymentAddedEventResponse();
                typedResponse.log = log;
                typedResponse.ppid = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.taxPayerRegistrationNumber = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.invoiceNumber = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.payeesNIC = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.payeesName = (String) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.payersNIC = (String) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.payersName = (String) eventValues.getNonIndexedValues().get(6).getValue();
                typedResponse.periodCode = (String) eventValues.getNonIndexedValues().get(7).getValue();
                typedResponse.installmentNumber = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
                typedResponse.paymentDescription = (String) eventValues.getNonIndexedValues().get(9).getValue();
                typedResponse.paidAmount = (String) eventValues.getNonIndexedValues().get(10).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CreditPersonPaymentAddedEventResponse> creditPersonPaymentAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREDITPERSONPAYMENTADDED_EVENT));
        return creditPersonPaymentAddedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> CompanyPayments(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_COMPANYPAYMENTS,
                Arrays.<Type>asList(new Uint256(param0)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> PersonPayments(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PERSONPAYMENTS,
                Arrays.<Type>asList(new Uint256(param0)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> creditCompanyPayment(BigInteger taxPayerRegistrationNumber, String invoiceNumber, String payeesNIC, String payeesName, String companyRegistrationNumber, String companyName, String periodCode, BigInteger installmentNumber, String paymentDescription, String paidAmount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREDITCOMPANYPAYMENT,
                Arrays.<Type>asList(new Uint256(taxPayerRegistrationNumber),
                        new Utf8String(invoiceNumber),
                        new Utf8String(payeesNIC),
                        new Utf8String(payeesName),
                        new Utf8String(companyRegistrationNumber),
                        new Utf8String(companyName),
                        new Utf8String(periodCode),
                        new Uint256(installmentNumber),
                        new Utf8String(paymentDescription),
                        new Utf8String(paidAmount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> creditPersonPayment(BigInteger taxPayerRegistrationNumber, String invoiceNumber, String payeesNIC, String payeesName, String payersNIC, String payersName, String periodCode, BigInteger installmentNumber, String paymentDescription, String paidAmount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREDITPERSONPAYMENT,
                Arrays.<Type>asList(new Uint256(taxPayerRegistrationNumber),
                        new Utf8String(invoiceNumber),
                        new Utf8String(payeesNIC),
                        new Utf8String(payeesName),
                        new Utf8String(payersNIC),
                        new Utf8String(payersName),
                        new Utf8String(periodCode),
                        new Uint256(installmentNumber),
                        new Utf8String(paymentDescription),
                        new Utf8String(paidAmount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple6<List,List,List,List,List,List>> getAllPaymentDetailsByNIC(String nic) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETALLPAYMENTDETAILSBYNIC,
                Arrays.<Type>asList(new Utf8String(nic)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {},
                        new TypeReference<DynamicArray<Utf8String>>() {},
                        new TypeReference<DynamicArray<Utf8String>>() {},
                        new TypeReference<DynamicArray<Utf8String>>() {},
                        new TypeReference<DynamicArray<Uint>>() {},
                        new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<Tuple6<List,List,List,List,List,List>>(
                function, new Callable<Tuple6<List,List,List,List,List,List>>() {
            public Tuple6<List,List,List,List,List,List> call() throws Exception {
                List<Type> results = executeCallMultipleValueReturn(function);
                DynamicArray<Utf8String> array1 = (DynamicArray<Utf8String>) results.get(0);
                DynamicArray<Utf8String> array2 = (DynamicArray<Utf8String>) results.get(1);
                DynamicArray<Utf8String> array3 = (DynamicArray<Utf8String>) results.get(2);
                DynamicArray<Utf8String> array4 = (DynamicArray<Utf8String>) results.get(3);
                DynamicArray<Uint> array5 = (DynamicArray<Uint>) results.get(4);
                DynamicArray<Utf8String> array6 = (DynamicArray<Utf8String>) results.get(5);

                List<Utf8String> resultList1 = array1.getValue();
                List<Utf8String> resultList2 = array2.getValue();
                List<Utf8String> resultList3 = array3.getValue();
                List<Utf8String> resultList4 = array4.getValue();
                List<Uint> resultList5 = array5.getValue();
                List<Utf8String> resultList6 = array6.getValue();

                return new Tuple6<>(resultList1,resultList2,resultList3,resultList4,resultList5,resultList6);
            }
        }
        );
    }

    public RemoteFunctionCall<Tuple6<List,List,List,List,List,List>> getAllPaymentDetailsByRegNumber(String regNumber) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETALLPAYMENTDETAILSBYREGNUMBER,
                Arrays.<Type>asList(new Utf8String(regNumber)),
                Arrays.<TypeReference<?>>asList(
                        new TypeReference<DynamicArray<Utf8String>>() {},
                        new TypeReference<DynamicArray<Utf8String>>() {},
                        new TypeReference<DynamicArray<Utf8String>>() {},
                        new TypeReference<DynamicArray<Utf8String>>() {},
                        new TypeReference<DynamicArray<Uint>>() {},
                        new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<Tuple6<List,List,List,List,List,List>>(
                function, new Callable<Tuple6<List,List,List,List,List,List>>() {
            public Tuple6<List,List,List,List,List,List> call() throws Exception {
                List<Type> results = executeCallMultipleValueReturn(function);
                DynamicArray<Utf8String> array1 = (DynamicArray<Utf8String>) results.get(0);
                DynamicArray<Utf8String> array2 = (DynamicArray<Utf8String>) results.get(1);
                DynamicArray<Utf8String> array3 = (DynamicArray<Utf8String>) results.get(2);
                DynamicArray<Utf8String> array4 = (DynamicArray<Utf8String>) results.get(3);
                DynamicArray<Uint> array5 = (DynamicArray<Uint>) results.get(4);
                DynamicArray<Utf8String> array6 = (DynamicArray<Utf8String>) results.get(5);

                List<Utf8String> resultList1 = array1.getValue();
                List<Utf8String> resultList2 = array2.getValue();
                List<Utf8String> resultList3 = array3.getValue();
                List<Utf8String> resultList4 = array4.getValue();
                List<Uint> resultList5 = array5.getValue();
                List<Utf8String> resultList6 = array6.getValue();
                return new Tuple6<>(resultList1,resultList2,resultList3,resultList4,resultList5,resultList6);
            }
        }
        );
    }

    public RemoteFunctionCall<TransactionReceipt> getCompanyPaymentDetailsByInvoiceNumber(String invoiceNumber) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETCOMPANYPAYMENTDETAILSBYINVOICENUMBER,
                Arrays.<Type>asList(new Utf8String(invoiceNumber)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getPersonPaymentDetailsByInvoiceNumber(String invoiceNumber) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETPERSONPAYMENTDETAILSBYINVOICENUMBER,
                Arrays.<Type>asList(new Utf8String(invoiceNumber)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static TaxPayingService load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TaxPayingService(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TaxPayingService load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TaxPayingService(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TaxPayingService load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TaxPayingService(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TaxPayingService load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TaxPayingService(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TaxPayingService> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TaxPayingService.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TaxPayingService> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TaxPayingService.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<TaxPayingService> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TaxPayingService.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TaxPayingService> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TaxPayingService.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class CreditCompanyPaymentAddedEventResponse extends BaseEventResponse {
        public BigInteger ppid;

        public BigInteger taxPayerRegistrationNumber;

        public String invoiceNumber;

        public String payeesNIC;

        public String payeesName;

        public String companyRegistrationNumber;

        public String companyName;

        public String periodCode;

        public BigInteger installmentNumber;

        public String paymentDescription;

        public String paidAmount;
    }

    public static class CreditPersonPaymentAddedEventResponse extends BaseEventResponse {
        public BigInteger ppid;

        public BigInteger taxPayerRegistrationNumber;

        public String invoiceNumber;

        public String payeesNIC;

        public String payeesName;

        public String payersNIC;

        public String payersName;

        public String periodCode;

        public BigInteger installmentNumber;

        public String paymentDescription;

        public String paidAmount;
    }
}
