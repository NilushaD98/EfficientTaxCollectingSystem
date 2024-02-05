package taxproject.taxpayerservice.util.mappers;

import org.mapstruct.Mapper;
import taxproject.taxpayerservice.dto.request.RequestAddCompanyTypeDTO;
import taxproject.taxpayerservice.entity.CompanyTypes;

@Mapper(componentModel = "spring")
public interface CompanyTypeMapper {
    CompanyTypes DTOToEntity(RequestAddCompanyTypeDTO requestAddCompanyTypeDTO);
}
