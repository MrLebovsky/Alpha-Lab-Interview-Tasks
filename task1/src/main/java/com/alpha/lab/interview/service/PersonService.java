package com.alpha.lab.interview.service;

import com.alpha.lab.interview.database.repository.PersonRepository;
import com.alpha.lab.interview.mapper.JacksonMapper;
import com.alpha.lab.interview.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {
    private static final String TASK1_SUBSTRING = "777";

    private final PersonRepository personRepository;

    private final JacksonMapper jacksonMapper;

    private final PersonMapper personMapper;

    public void printPersonsToConsole() {
        personRepository
                .findPersonByDocumentsDocumentNumberContainingAndDeletedFalse(TASK1_SUBSTRING)
                .stream()
                .map(personMapper::toDto)
                .map(jacksonMapper::writeValueAsString)
                .forEach(log::info);
    }
}
