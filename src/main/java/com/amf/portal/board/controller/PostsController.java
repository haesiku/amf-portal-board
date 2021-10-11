package com.amf.portal.board.controller;

import com.amf.portal.board.domain.post.dto.PostsResponseDto;
import com.amf.portal.board.domain.post.dto.PostsSaveRequestDto;
import com.amf.portal.board.domain.post.dto.PostsUpdateRequestDto;
import com.amf.portal.board.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return this.postsService.save(requestDto);
    }

    // 게시글 수정
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return this.postsService.update(id, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        this.postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return this.postsService.findById(id);
    }


    /*
    private PostsService postsService;

    @GetMapping(value = "post/add/{quantity}")
    public String addQuantity(@PathVariable int quantity) {
        return "member/read/";

    }

    @GetMapping(value = "post/modify/{quantity}")
    public String modifyQuantity(@PathVariable int quantity) {
        return "member/read/";

    }


 */
}
