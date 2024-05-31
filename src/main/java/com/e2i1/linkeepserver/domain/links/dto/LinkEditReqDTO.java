package com.e2i1.linkeepserver.domain.links.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkEditReqDTO {

    private String title;

    private String description;

    @URL(message = "유효한 URL 형식이 아닙니다.")
    private String url;

}
