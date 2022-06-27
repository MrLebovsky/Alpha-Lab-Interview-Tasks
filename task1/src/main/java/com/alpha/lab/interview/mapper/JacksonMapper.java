package com.alpha.lab.interview.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JacksonMapper {
    private final ObjectMapper mapper;

    @SneakyThrows
    public String writeValueAsString(Object value) {
        return mapper.writeValueAsString(value);
    }
}
