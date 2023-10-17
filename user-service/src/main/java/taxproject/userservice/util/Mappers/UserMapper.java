package taxproject.userservice.util.Mappers;

import org.mapstruct.Mapper;
import taxproject.userservice.dto.EmployeeDTO;
import taxproject.userservice.dto.response.ResponseEmployeeDTO;
import taxproject.userservice.entity.Employee;


@Mapper(componentModel = "spring")
public interface UserMapper {
    Employee EmployeeDTOToEntity(EmployeeDTO employeeDTO);

}
