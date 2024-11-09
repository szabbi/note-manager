package hu.unideb.inf.notemanager.mapper;

import hu.unideb.inf.notemanager.dto.NoteDto;
import hu.unideb.inf.notemanager.dto.UserDto;
import hu.unideb.inf.notemanager.entitiy.NoteEntity;
import hu.unideb.inf.notemanager.entitiy.UserEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoteEntityMapper {
    NoteEntity toEntity(NoteDto noteDto);
    NoteDto toDto(NoteEntity noteEntity);
    List<NoteDto> toDtoList(List<NoteEntity> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    NoteEntity partialUpdate(NoteDto noteDto, @MappingTarget NoteEntity noteEntity);

}