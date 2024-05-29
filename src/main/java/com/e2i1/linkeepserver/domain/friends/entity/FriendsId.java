package com.e2i1.linkeepserver.domain.friends.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendsId implements Serializable {
  private Long followingUserId;
  private Long followedUserId;
}
