package hu.unideb.inf.notemanager.mapper;

import hu.unideb.inf.notemanager.dto.NoteDto;
import hu.unideb.inf.notemanager.entitiy.NoteEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoteEntityMapper {
    NoteEntity toEntity(NoteDto noteDto);
    NoteDto toDto(NoteEntity noteEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    NoteEntity partialUpdate(NoteDto noteDto, @MappingTarget NoteEntity noteEntity);

}