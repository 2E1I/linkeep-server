package com.e2i1.linkeepserver.domain.friends.entity;

import com.e2i1.linkeepserver.common.entity.DateEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
@Table(name = "friends")
public class FriendsEntity extends DateEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "following_user_id")
    private UsersEntity followingUser;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "followed_user_id")
    private UsersEntity followedUser;   // followinUser가 followedUser를 팔로우하는 상황

    private Boolean isFollowing;

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
