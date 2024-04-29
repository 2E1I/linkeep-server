package com.e2i1.linkeepserver.domain.users.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkHomeResDTO {

    private Long id;
    private String title;
    private String url;
    private String description;
    private LocalDateTime updatedAt;
}
