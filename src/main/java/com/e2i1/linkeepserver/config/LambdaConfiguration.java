package com.e2i1.linkeepserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class LambdaConfiguration {
    @Bean
    public Function<Long, CollectionResDTO> GetCollectionList(){
        return id ->{
            CollectionResDTO res = CollectionResDTO.builder().collectionId(2).description("굳").title("싸이코딩").build();
          return res;
        };
    }
    @Bean
    public Function<CollectionReqDTO,String>SaveCollection(){
        return input -> {
            String result = input.description + "인 설명의 "+input.title+"모음집";
            return result;
        };
    }
}
