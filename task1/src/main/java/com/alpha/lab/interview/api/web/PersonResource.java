package com.alpha.lab.interview.api.web;

import com.alpha.lab.interview.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/person")
@RequiredArgsConstructor
public class PersonResource {

    private final PersonService personService;

    @GetMapping
    public void printPersonInfo() {
        personService.printPersonsToConsole();
    }

}
