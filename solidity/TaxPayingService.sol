// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract TaxPayingService{

    uint private personPaymentDetailsCount;
    uint private companyPaymentDetailsCount;
    mapping (uint => PersonPayment) public PersonPayments;
    mapping (uint => CompanyPayment) public CompanyPayments;

    struct PersonPayment{
        uint taxPayerRegistrationNumber;
        string invoiceNumber;
        string payeesNIC;
        string payeesName;
        string payersNIC;
        string payersName;
        string periodCode;
        uint installmentNumber;
        string paymentDescription;
        string paidAmount;
    }
    struct CompanyPayment{

        uint taxPayerRegistrationNumber;
        string invoiceNumber;
        string payeesNIC;
        string payeesName;
        string companyRegistrationNumber;
        string companyName;
        string periodCode;
        uint installmentNumber;
        string paymentDescription;
        string paidAmount;
    }

    event creditPersonPaymentAdded(uint ppid,uint taxPayerRegistrationNumber,string invoiceNumber,string payeesNIC,string payeesName,string payersNIC,string payersName,string periodCode,uint installmentNumber,string paymentDescription,string paidAmount);
    event creditCompanyPaymentAdded(uint ppid,uint taxPayerRegistrationNumber,string invoiceNumber,string payeesNIC,string payeesName,string companyRegistrationNumber,string companyName,string periodCode,uint installmentNumber,string paymentDescription,string paidAmount);

    function creditPersonPayment(
        uint taxPayerRegistrationNumber,
        string memory invoiceNumber,
        string memory payeesNIC,
        string memory payeesName,
        string memory payersNIC,
        string memory payersName,
        string memory periodCode,
        uint installmentNumber,
        string memory paymentDescription,
        string memory paidAmount) public returns (uint)
        {

        uint _personPaymentID = ++personPaymentDetailsCount;
        emit creditPersonPaymentAdded(_personPaymentID,taxPayerRegistrationNumber,invoiceNumber,payeesNIC, payeesName, payersNIC, payersName, periodCode, installmentNumber, paymentDescription, paidAmount);
        PersonPayments[_personPaymentID] = PersonPayment(taxPayerRegistrationNumber,invoiceNumber, payeesNIC, payeesName, payersNIC, payersName, periodCode, installmentNumber, paymentDescription, paidAmount);
        return _personPaymentID;
    }

    function getAllPaymentDetailsByNIC(string memory nic)public view returns (string[] memory,string[] memory,string[] memory,string[] memory,uint[] memory,string[] memory){
        uint totalPersonDetails = personPaymentDetailsCount +1;
        string memory NIC = nic;
        uint relevantDataCount;
        for(uint i=0 ;i< totalPersonDetails;i++){

            if(keccak256(abi.encodePacked(PersonPayments[i].payersNIC)) == keccak256(abi.encodePacked(NIC))){
                relevantDataCount  +=1;
            }
        }

        string[] memory invoiceNumbers = new string[](relevantDataCount);
        string[] memory payeesNICs = new string[](relevantDataCount);
        string[] memory payeesNames = new string[](relevantDataCount);
        string[] memory periodCodes = new string[](relevantDataCount);
        uint[] memory installmentNumbers = new uint[](relevantDataCount);
        string[] memory paidAmounts = new string[](relevantDataCount);
        uint count = 0;

        for(uint i=0 ;i< totalPersonDetails;i++){
            if(keccak256(abi.encodePacked(PersonPayments[i].payersNIC)) == keccak256(abi.encodePacked(NIC))){
                invoiceNumbers[count]=PersonPayments[i].invoiceNumber;
                payeesNICs[count] = PersonPayments[i].payeesNIC;
                payeesNames[count]=PersonPayments[i].payeesName;
                periodCodes[count]=PersonPayments[i].periodCode;
                installmentNumbers[count]=PersonPayments[i].installmentNumber;
                paidAmounts[count]=PersonPayments[i].paidAmount;
                count +=1;
            }

        }
        return(invoiceNumbers,payeesNICs,payeesNames,periodCodes, installmentNumbers,paidAmounts);
    }
    function getPersonPaymentDetailsByInvoiceNumber(string memory invoiceNumber) public view returns(uint ,string memory,string memory,string memory,uint ,string memory){
        uint totalPersonDetails = personPaymentDetailsCount +1;
        uint index;
        for(uint i=0 ;i< totalPersonDetails;i++){
            if(keccak256(abi.encodePacked(PersonPayments[i].invoiceNumber)) == keccak256(abi.encodePacked(invoiceNumber))){
               index = i;
            }
        }
        PersonPayment storage per = PersonPayments[index];
               return(
                per.taxPayerRegistrationNumber,
                per.payeesNIC,
                per.payeesName,
                per.periodCode,
                per.installmentNumber,
                per.paidAmount
               );
    }

     function creditCompanyPayment(
        uint taxPayerRegistrationNumber,
        string memory invoiceNumber,
        string memory payeesNIC,
        string memory payeesName,
        string memory companyRegistrationNumber,
        string memory companyName,
        string memory periodCode,
        uint installmentNumber,
        string memory paymentDescription,
        string memory paidAmount) public returns (uint)
        {

        uint _companyPaymentID = ++companyPaymentDetailsCount;
        emit creditCompanyPaymentAdded(_companyPaymentID,taxPayerRegistrationNumber,invoiceNumber, payeesNIC, payeesName, companyRegistrationNumber, companyName, periodCode, installmentNumber, paymentDescription, paidAmount);
        CompanyPayments[_companyPaymentID] = CompanyPayment(taxPayerRegistrationNumber,invoiceNumber, payeesNIC, payeesName, companyRegistrationNumber, companyName, periodCode, installmentNumber, paymentDescription, paidAmount);
        return _companyPaymentID;
    }

    function getAllPaymentDetailsByRegNumber(string memory regNumber)public view returns (string[] memory,string[] memory,string[] memory,string[] memory,uint[] memory,string[] memory){
        uint totalComapnyDetails = companyPaymentDetailsCount +1;
        string memory REGNUMBER = regNumber;
        uint relevantDataCount;
        for(uint i=0 ;i< totalComapnyDetails;i++){

            if(keccak256(abi.encodePacked(CompanyPayments[i].companyRegistrationNumber)) == keccak256(abi.encodePacked(REGNUMBER))){
                relevantDataCount  +=1;
            }
        }

        string[] memory invoiceNumbers = new string[](relevantDataCount);
        string[] memory payeesNICs = new string[](relevantDataCount);
        string[] memory payeesNames = new string[](relevantDataCount);
        string[] memory periodCodes = new string[](relevantDataCount);
        uint[] memory installmentNumbers = new uint[](relevantDataCount);
        string[] memory paidAmounts = new string[](relevantDataCount);
        uint count = 0;

        for(uint i=0 ;i< totalComapnyDetails;i++){
            if(keccak256(abi.encodePacked(CompanyPayments[i].companyRegistrationNumber)) == keccak256(abi.encodePacked(REGNUMBER))){
                invoiceNumbers[count]=CompanyPayments[i].invoiceNumber;
                payeesNICs[count] = CompanyPayments[i].payeesNIC;
                payeesNames[count]=CompanyPayments[i].payeesName;
                periodCodes[count]=CompanyPayments[i].periodCode;
                installmentNumbers[count]=CompanyPayments[i].installmentNumber;
                paidAmounts[count]=CompanyPayments[i].paidAmount;
                count +=1;
            }

        }
        return(invoiceNumbers,payeesNICs,payeesNames,periodCodes, installmentNumbers,paidAmounts);
    }
    function getCompanyPaymentDetailsByInvoiceNumber(string memory invoiceNumber) public view returns(uint ,string memory,string memory,string memory,uint ,string memory){
        uint totalComapnyDetails = companyPaymentDetailsCount +1;
        uint index;
        for(uint i=0 ;i< totalComapnyDetails;i++){
            if(keccak256(abi.encodePacked(CompanyPayments[i].invoiceNumber)) == keccak256(abi.encodePacked(invoiceNumber))){
               index = i;
            }
        }
        CompanyPayment storage per = CompanyPayments[index];
               return(
                per.taxPayerRegistrationNumber,
                per.payeesNIC,
                per.payeesName,
                per.periodCode,
                per.installmentNumber,
                per.paidAmount
               );
    }

}