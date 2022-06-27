package com.alpha.lab.interview.api.dto;

import com.alpha.lab.interview.api.dictionary.DocumentType;
import lombok.Data;

@Data
public class DocumentDTO {

    private DocumentType documentType;

    private String documentSeries;

    private String documentNumber;
}
