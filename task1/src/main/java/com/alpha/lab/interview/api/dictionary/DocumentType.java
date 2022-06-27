package com.alpha.lab.interview.api.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DocumentType implements DictionaryInterface {

    PASSPORT("Паспорт гражданина РФ"),

    INSURANCE_CERTIFICATE("Страховое свидетельство (СНИЛС) "),

    INSURANCE_POLICY_OMS("Медицинский страховой полис (ОМС)"),

    INSURANCE_POLICY_DMS("Медицинский страховой полис (ДМС)");

    private final String description;
}
