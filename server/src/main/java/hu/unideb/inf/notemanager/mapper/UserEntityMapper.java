package hu.unideb.inf.notemanager.mapper;

import hu.unideb.inf.notemanager.dto.RegistrationDto;
import hu.unideb.inf.notemanager.dto.UserDto;
import hu.unideb.inf.notemanager.entitiy.UserEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntityMapper {
    UserEntity toEntity(UserDto userDto);
    UserDto toDto(UserEntity userEntity);
    List<UserDto> toDtoList(List<UserEntity> entities);

    UserEntity toEntityReg(RegistrationDto registrationDto);
    RegistrationDto toDtoReg(UserEntity userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(UserDto userDto, @MappingTarget UserEntity userEntity);

}
