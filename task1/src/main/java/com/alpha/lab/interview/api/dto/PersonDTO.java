package com.alpha.lab.interview.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonDTO {

    private String fullName;

    private List<DocumentDTO> documents;
}
