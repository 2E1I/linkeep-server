package com.e2i1.linkeepserver.domain.users.dto;

import com.e2i1.linkeepserver.domain.collections.dto.CollectionResDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHomeResDTO {
    private String nickname;
    private String imgUrl;
    private List<CollectionResDTO> collectionList;
}
