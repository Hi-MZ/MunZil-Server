package io.munzil.munzil.domain.feed.presentation;

import io.munzil.munzil.domain.feed.presentation.dto.request.CreateFeedRequest;
import io.munzil.munzil.domain.feed.presentation.dto.request.UpdateFeedRequest;
import io.munzil.munzil.domain.feed.presentation.dto.response.CreateFeedResponse;
import io.munzil.munzil.domain.feed.presentation.dto.response.QueryFeedDetailsResponse;
import io.munzil.munzil.domain.feed.presentation.dto.response.QueryFeedPagesResponse;
import io.munzil.munzil.domain.feed.service.*;
import io.munzil.munzil.global.enums.SortPageType;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/feeds")
@RestController
public class FeedController {

    private final CreateFeedService createFeedService;
    private final UpdateFeedService updateFeedService;
    private final DeleteFeedService deleteFeedService;
    private final AddLikeService addLikeService;
    private final SubLikeService subLikeService;
    private final QueryFeedDetailsService queryFeedDetailsService;
    private final QueryFeedPagesService queryFeedPagesService;

    @Operation(summary = "게시글 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateFeedResponse createFeed(@RequestPart(required = false) List<MultipartFile> images, @RequestPart @Valid CreateFeedRequest request) {
        return createFeedService.execute(images, request);
    }

    @Operation(summary = "게시글 수정")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{feed-id}")
    public void updateFeed(@RequestPart(required = false) List<MultipartFile> images, @RequestPart @Valid UpdateFeedRequest request, @PathVariable("feed-id") Long feedId) {
        updateFeedService.execute(images, request, feedId);
    }

    @Operation(summary = "게시글 삭제")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{feed-id}")
    public void deleteFeed(@PathVariable("feed-id") Long feedId) {
        deleteFeedService.execute(feedId);
    }

    @Operation(summary = "게시글 상세 조회")
    @GetMapping("/{feed-id}")
    public QueryFeedDetailsResponse getFeed(@PathVariable("feed-id") Long feedId) {
        return queryFeedDetailsService.execute(feedId);
    }

    @Operation(summary = "게시글 리스트 조회")
    @GetMapping
    public QueryFeedPagesResponse getFeeds(@RequestParam(required = false, defaultValue = "0") Long cursorId, @RequestParam(required = false, defaultValue = "0") Integer pageId, @RequestParam SortPageType sortType) {
        return queryFeedPagesService.execute(cursorId, pageId, sortType);
    }

    @Operation(summary = "게시글 좋아요")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{feed-id}/like")
    public void like(@PathVariable("feed-id") Long feedId) {
        addLikeService.execute(feedId);
    }

    @Operation(summary = "게시글 좋아요 취소")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{feed-id}/like")
    public void unlike(@PathVariable("feed-id") Long feedId) {
        subLikeService.execute(feedId);
    }

}