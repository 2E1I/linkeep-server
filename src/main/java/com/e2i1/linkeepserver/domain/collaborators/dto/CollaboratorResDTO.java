package com.e2i1.linkeepserver.domain.collaborators.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollaboratorResDTO {
  private Long userId;
  private String name;
  private String role;

}
