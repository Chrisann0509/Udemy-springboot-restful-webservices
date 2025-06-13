package net.javaguides.springboot_restful_webservices.mapper;

import net.javaguides.springboot_restful_webservices.dto.UserDTO;
import net.javaguides.springboot_restful_webservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    // provide the implementation for this interface at compilation time
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    // If UserDTO have different field name (eg. emailAddress), we can use @Mapping
    //@Mapping(source ="email", target = "emailAddress")
    UserDTO mapTOUserDTO(User user);

    User mapToUser(UserDTO userDTO);

}
