package hu.unideb.inf.notemanager.mapper;

import hu.unideb.inf.notemanager.dto.NoteDto;
import hu.unideb.inf.notemanager.entitiy.NoteEntity;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoteEntityMapper {
    NoteEntity toEntity(NoteDto noteDto);
    @Mapping(target = "createdBy", ignore = true)
    NoteDto toDto(NoteEntity noteEntity);
    List<NoteDto> toDtoList(List<NoteEntity> entities);

    @Named("toDtoWithUsername")
    default NoteDto toDtoWithUsername(NoteEntity noteEntity) {
        NoteDto noteDto = toDto(noteEntity);
        noteDto.setCreatedBy(noteEntity.getFelhasznalo().getName());

        return noteDto;
    }

    @Named("toDtoListWithUsername")
    default List<NoteDto> toDtoListWithUsername(List<NoteEntity> entities) {
        return entities.stream()
                .map(this::toDtoWithUsername)
                .collect(Collectors.toList());
    }

}