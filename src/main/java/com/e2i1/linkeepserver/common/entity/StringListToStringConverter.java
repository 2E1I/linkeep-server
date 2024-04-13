package com.e2i1.linkeepserver.common.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class StringListToStringConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "{}"; // PostgreSQL 에서 빈 배열을 표현
        }

        // List를 PostgreSQL 배열 문자열로 변환
        return "{" + attribute.stream()
                .map(s -> "\"" + s.replace("\"", "\\\"") + "\"") // 쌍따옴표 이스케이프
                .collect(Collectors.joining(",")) + "}";
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.equals("{}")) {
            return null;
        }

        // PostgreSQL 배열 문자열을 List로 변환
        dbData = dbData.substring(1, dbData.length() - 1); // 양쪽 중괄호 제거
        return Arrays.asList(dbData.split(",")).stream()
                .map(s -> s.replace("\"", "")) // 쌍따옴표 제거
                .collect(Collectors.toList());
    }
}