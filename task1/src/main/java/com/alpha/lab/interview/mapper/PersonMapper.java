package com.alpha.lab.interview.mapper;

import com.alpha.lab.interview.api.dto.PersonDTO;
import com.alpha.lab.interview.database.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring", uses = {DocumentMapper.class})
public interface PersonMapper extends EntityMapper<Person, PersonDTO> {

    @Override
    @Mapping(target = "fullName", source = ".", qualifiedByName = "toFullName")
    PersonDTO toDto(Person person);

    @Named("toFullName")
    default String translateToFullName(Person person) {
        return Stream.of(person.getFirstName(), person.getLastName(), person.getMiddleName())
                .filter(srt -> srt != null && !srt.isEmpty())
                .collect(Collectors.joining(" "));
    }
}
