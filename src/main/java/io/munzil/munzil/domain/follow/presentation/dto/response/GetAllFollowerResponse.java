package io.munzil.munzil.domain.follow.presentation.dto.response;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllFollowerResponse {
    private final List<FollowInfo> followerUserList;
}