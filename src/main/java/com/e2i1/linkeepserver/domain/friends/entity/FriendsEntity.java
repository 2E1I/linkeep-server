package com.e2i1.linkeepserver.domain.friends.entity;

import com.e2i1.linkeepserver.common.entity.DateEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
@Table(name = "friends")
public class FriendsEntity {
    @EmbeddedId
    private FriendsId id;

    @ManyToOne(fetch = LAZY)
    @MapsId("followingUserId")
    @JoinColumn(name = "following_user_id")
    private UsersEntity followingUser;

    @ManyToOne(fetch = LAZY)
    @MapsId("followedUserId")
    @JoinColumn(name = "followed_user_id")
    private UsersEntity followedUser;   // followinUser가 followedUser를 팔로우하는 상황

    private Boolean isFollowing;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    public void setFollowingUser(UsersEntity usersEntity) {
        followingUser = usersEntity;
    }

    public void setFollowedUser(UsersEntity usersEntity) {
        followedUser = usersEntity;
    }
    public void updateStatus(){
      this.isFollowing = !this.isFollowing;
    }
}
