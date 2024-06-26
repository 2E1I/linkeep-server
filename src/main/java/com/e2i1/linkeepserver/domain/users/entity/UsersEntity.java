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
    private List<FriendsEntity> followingList;  // 내가 팔로우한 유저 리스트

    @OneToMany(mappedBy = "followedUser", cascade = ALL)
    private List<FriendsEntity> followerList;   // 나를 팔로우한 유저 리스트

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<LinksEntity> linkList;

    private String nickname;
    private String description;
    private String email;
    private String imgUrl;
    private String thumbnailUrl;

    @Enumerated(EnumType.STRING)
    private UserStatus status;



    // 프로필 변경
    public void update(String nickname, String imgUrl, String description) {
        if (nickname != null) {
            this.nickname = nickname;
        }
        
        // 삭제하려는 경우에는 null, 이미지 수정하는 경우에는 새로운 imgUrl, 변경사항 없는 경우 기존 imgUrl이 들어온다
        this.imgUrl = imgUrl;

        if (description != null) {
            this.description = description;
        }
    }

    public void delete() {
        this.status = UserStatus.UNREGISTERED;
    }

    


}