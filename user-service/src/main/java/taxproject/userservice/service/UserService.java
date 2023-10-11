package taxproject.userservice.service;

import taxproject.userservice.dto.request.RequestAddEmployee;
import taxproject.userservice.dto.response.ResponseUserAuthDetailsDTO;

public interface UserService {
    ResponseUserAuthDetailsDTO findUserByUserName(String username);

    String addEmployee(RequestAddEmployee requestAddEmployee);
}
