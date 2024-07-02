package com.e2i1.linkeepserver.domain.collaborators.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollaboratorEditReqDTO {
  Long collectionId;
  List<Long> deleteUsers;
  List<Long> insertUsers;
}
