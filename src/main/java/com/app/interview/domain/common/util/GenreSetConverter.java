package com.app.interview.domain.common.util;

import com.app.interview.domain.title.entity.enums.Genre;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class GenreSetConverter implements AttributeConverter<Set<Genre>, String> {

    @Override
    public String convertToDatabaseColumn(Set<Genre> genres) {
        if (genres == null || genres.isEmpty()) {
            return null;
        }
        return genres.stream()
                .map(Genre::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<Genre> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.equals("\\N")) {
            return new HashSet<>();
        }
        return Arrays.stream(dbData.split(","))
                .map(Genre::valueOf)
                .collect(Collectors.toSet());
    }
}