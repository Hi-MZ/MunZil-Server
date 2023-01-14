package io.munzil.munzil.domain.user.domain;

import io.munzil.munzil.domain.feed.domain.Feed;
import io.munzil.munzil.domain.like.domain.FeedLike;
import io.munzil.munzil.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import io.munzil.munzil.global.entity.BaseTimeEntity;
import io.munzil.munzil.global.enums.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 45)
    @Column(unique = true)
    private String accountId;

    @NotNull
    @Size(max = 60)
    private String password;

    @NotNull
    @Size(max = 30)
    private String nickname;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private UserRole role;

    @Column(nullable = false)
    private String profileImageUrl;

    @NotNull
    @ColumnDefault("0")
    private Long feedCount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Feed> feeds;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<FeedLike> feedLikes;

    @Builder
    public User(String accountId, String password, String nickname, UserRole role, String profileImageUrl) {
        this.accountId = accountId;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.profileImageUrl = profileImageUrl;
    }

    @PrePersist
    public void prePersist() {
        this.feedCount = this.feedCount == null ? 0 : this.feedCount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void updateUser(UpdateUserInfoRequest request) {
        this.nickname = request.getNickname();
    }

    public void updateProfileImageUrl(String profileImageUrl){
        this.profileImageUrl = profileImageUrl;
    }

    public void addFeed() {
        this.feedCount++;
    }

    public void subFeed() {
        this.feedCount--;
    }

}
