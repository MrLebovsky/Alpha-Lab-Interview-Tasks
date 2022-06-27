package com.alpha.lab.interview.mapper;

import com.alpha.lab.interview.api.dto.DocumentDTO;
import com.alpha.lab.interview.database.entity.Document;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface DocumentMapper  extends EntityMapper<Document, DocumentDTO> {
    @Override
    DocumentDTO toDto(Document document);

    List<DocumentDTO> toDtoList(Set<Document> documents);
}
