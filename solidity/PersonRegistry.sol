// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract PersonRegistry {

    uint private numPerson;
    uint private numCompany;
    string[] personID;
    string[] companyRegNumber;
    mapping (uint => Person) private Persons;
    mapping (uint => Company) private Companies;

    struct Person {
        string nic;
        string nameWithInitials;
    }

    struct Company {
        string registrationNumber;
        string companyName;
    }


    event PersonAdded(uint personID, string nic, string nameWithInitials);
    event CompanyAdded(uint cID, string registrationNumber, string companyName);

    //for add person to blockchain
    function addPerson(string memory _nic, string memory _nameWithInitials) public returns (uint){

        uint _id = insertPerson(_nic,_nameWithInitials);
        emit PersonAdded(_id, _nic, _nameWithInitials);
        return _id;
    }
    //for add company to blockchain
    function addCompany(string memory _registrationNumber, string memory _companyName)public returns (uint){
        uint _cid = insertCompany(_registrationNumber,_companyName);

        emit CompanyAdded(_cid, _registrationNumber, _companyName);
        return _cid;
    }

    //for create id for person
    function insertPerson(string memory _nic, string memory _nameWithInitials) private returns (uint){
        uint id = ++numPerson;
        personID.push(_nic);
        Persons[id] = Person(_nic,_nameWithInitials);
        return id;
    }
    //for create id for company
    function insertCompany(string memory _registrationNumber, string memory _companyName) private  returns (uint){
        uint cid = ++numCompany;
        companyRegNumber.push(_registrationNumber);
        Companies[cid] = Company(_registrationNumber,_companyName);
        return cid;
    }

    //for get person details using person's NIC
    function getPersonByNIC(string memory _nic) public view returns (string memory,string memory){
        for (uint256 i = 0; i < personID.length; i++){
            if(keccak256(abi.encodePacked(Persons[i].nic)) == keccak256(abi.encodePacked(_nic))){
                return (Persons[i].nic,Persons[i].nameWithInitials);
            }
        }
        return("","");
    }
    //for get company details using company's RegNum
    function getCompanyByRegistrationNumber(string memory _registrationNumber) public view returns (string memory, string memory) {
        for (uint256 i = 0; i < companyRegNumber.length; i++) {
            if (keccak256(abi.encodePacked(Companies[i].registrationNumber)) == keccak256(abi.encodePacked(_registrationNumber))) {

                return (Companies[i].registrationNumber,Companies[i].companyName);
            }
        }
        require(true,"sdfsdfsdfsdggdhdfghgfh");
        return ("", "");
    }

    //for get All persons as two arrays
    function getAllPersons() public view returns (string[] memory, string[] memory) {
        uint256 totalPersons = numPerson + 1;

        string[] memory nics = new string[](totalPersons);
        string[] memory names = new string[](totalPersons);

        for (uint256 i = 1; i < totalPersons; i++) {
            nics[i] = Persons[i].nic;
            names[i] = Persons[i].nameWithInitials;
        }

        return (nics, names);
    }
    //for get All Companies as two arrays
    function getAllCompanies() public view returns (string[] memory, string[] memory) {
        uint256 totalCompanies = numCompany + 1;

        string[] memory registrationNumbers = new string[](totalCompanies);
        string[] memory companyNames = new string[](totalCompanies);

        for (uint256 i = 1; i < totalCompanies; i++) {
            registrationNumbers[i] = Companies[i].registrationNumber;
            companyNames[i] = Companies[i].companyName;
        }
        return (registrationNumbers, companyNames);
    }
}
