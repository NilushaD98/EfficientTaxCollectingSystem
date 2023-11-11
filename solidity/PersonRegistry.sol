// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract PersonRegistry {
    struct Person {
        string nic;
        string nameWithInitials;
    }

    struct Company {
        bytes32 registrationNumber;
        string companyName;
    }

    mapping(address => Person) public persons;
    address[] public personAddresses;

    mapping(address => Company) public companies;
    address[] public companyAddresses;

    event PersonAdded(address indexed account, string nic, string nameWithInitials);
    event CompanyAdded(address indexed account, bytes32 registrationNumber, string companyName);

    function addPerson(string memory _nic, string memory _nameWithInitials) public {
        require(bytes(persons[msg.sender].nic).length == 0, "Person with this NIC already exists");

        persons[msg.sender] = Person(_nic, _nameWithInitials);
        personAddresses.push(msg.sender);
        emit PersonAdded(msg.sender, _nic, _nameWithInitials);
    }

    function addCompany(bytes32 _registrationNumber, string memory _companyName) public {
        require(companies[msg.sender].registrationNumber == bytes32(0), "Company with this registration number already exists");

        companies[msg.sender] = Company(_registrationNumber, _companyName);
        companyAddresses.push(msg.sender);
        emit CompanyAdded(msg.sender, _registrationNumber, _companyName);
    }

    function getPersonByNIC(string memory _nic) public view returns (string memory, string memory) {
        for (uint256 i = 0; i < personAddresses.length; i++) {
            address personAddress = personAddresses[i];
            if (keccak256(abi.encodePacked(persons[personAddress].nic)) == keccak256(abi.encodePacked(_nic))) {
                return (persons[personAddress].nic, persons[personAddress].nameWithInitials);
            }
        }
        return ("", "");
    }

    function getCompanyByRegistrationNumber(bytes32 _registrationNumber) public view returns (bytes32, string memory) {
        for (uint256 i = 0; i < companyAddresses.length; i++) {
            address companyAddress = companyAddresses[i];
            if (companies[companyAddress].registrationNumber == _registrationNumber) {
                return (companies[companyAddress].registrationNumber, companies[companyAddress].companyName);
            }
        }
        return (bytes32(0), "");
    }

    function getAllPersons() public view returns (string[] memory, string[] memory) {
        uint256 totalPersons = personAddresses.length;

        string[] memory nics = new string[](totalPersons);
        string[] memory names = new string[](totalPersons);

        for (uint256 i = 0; i < totalPersons; i++) {
            address personAddress = personAddresses[i];
            nics[i] = persons[personAddress].nic;
            names[i] = persons[personAddress].nameWithInitials;
        }

        return (nics, names);
    }

    function getAllCompanies() public view returns (bytes32[] memory, string[] memory) {
        uint256 totalCompanies = companyAddresses.length;

        bytes32[] memory registrationNumbers = new bytes32[](totalCompanies);
        string[] memory companyNames = new string[](totalCompanies);

        for (uint256 i = 0; i < totalCompanies; i++) {
            address companyAddress = companyAddresses[i];
            registrationNumbers[i] = companies[companyAddress].registrationNumber;
            companyNames[i] = companies[companyAddress].companyName;
        }
        return (registrationNumbers, companyNames);
    }
}
