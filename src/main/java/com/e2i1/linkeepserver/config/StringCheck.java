package com.e2i1.linkeepserver.config;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StringCheck implements Function<Long, CollectionResDTO> {

    @Override
    public CollectionResDTO apply(Long aLong) {
        return new CollectionResDTO(2,"good","wwwww");
    }
}
