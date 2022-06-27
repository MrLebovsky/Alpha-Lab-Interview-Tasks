package com.alpha.lab.interview.database.repository;

import com.alpha.lab.interview.database.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPersonByDocumentsDocumentNumberContainingAndDeletedFalse(String documentNumbersMask);
}
