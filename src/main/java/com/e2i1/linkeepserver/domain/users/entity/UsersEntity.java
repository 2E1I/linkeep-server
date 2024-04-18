package com.e2i1.linkeepserver.domain.users.entity;

import com.e2i1.linkeepserver.common.entity.DateEntity;
import com.e2i1.linkeepserver.domain.collaborators.entity.CollaboratorsEntity;
import com.e2i1.linkeepserver.domain.friends.entity.FriendsEntity;
import com.e2i1.linkeepserver.domain.likeothers.entity.LikeOthersEntity;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "users")
public class UsersEntity extends DateEntity {

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<CollaboratorsEntity> collaboratorList;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<LikeOthersEntity> likeOthersList;

    @OneToMany(mappedBy = "followingUser", cascade = ALL)
    private Set<FriendsEntity> followingList;  // 내가 팔로우한 유저 리스트

    @OneToMany(mappedBy = "followedUser", cascade = ALL)
    private Set<FriendsEntity> followerList;   // 나를 팔로우한 유저 리스트

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<LinksEntity> linkList;

    private String nickname;
    private String description;
    private String email;
    private String imgUrl;
    private String thumbnailUrl;

    @Enumerated(EnumType.STRING)
    private UserStatus status;


    // 연관관계 편의 메서드

    public void addCollaborator(CollaboratorsEntity collaborator) {
        collaboratorList.add(collaborator);
        collaborator.setUsersEntity(this);
    }

    public void addLikeOthers(LikeOthersEntity likeOthers) {
        likeOthersList.add(likeOthers);
        likeOthers.setUsersEntity(this);
    }

    public void addFollowing(FriendsEntity following) {
        followingList.add(following);
        following.setFollowingUser(this);
    }

    public void addFollower(FriendsEntity follower) {
        followerList.add(follower);
        follower.setFollowedUser(this);
    }

    // 사용자 이름 변경
    public UsersEntity update(String nickname) {
        this.nickname = nickname;

        return this;
    }


}