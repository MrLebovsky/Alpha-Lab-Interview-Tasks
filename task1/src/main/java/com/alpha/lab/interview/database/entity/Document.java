package com.alpha.lab.interview.database.entity;

import com.alpha.lab.interview.api.dictionary.DocumentType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "DOCUMENT", schema = "INTERVIEW")
public class Document extends AbstractEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCUMENT_GEN")
    @SequenceGenerator(name = "DOCUMENT_GEN", sequenceName = "DOCUMENT_ID_SEQ", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "DOCUMENT_TYPE")
    private DocumentType documentType;

    @Column(name = "DOCUMENT_SERIES")
    private String documentSeries;

    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;

    @Column(name = "ISSUE_DATE")
    private LocalDate issueDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID", nullable = false)
    private Person person;

}
