package com.amf.portal.board.service;

import com.amf.portal.board.domain.post.model.Posts;
import com.amf.portal.board.service.dto.PostsResponseDto;
import com.amf.portal.board.service.dto.PostsSaveRequestDto;
import com.amf.portal.board.service.dto.PostsUpdateRequestDto;
import com.amf.portal.board.web.dto.PostsListResponseDto;

import java.util.List;

public interface PostsService {

    Long save(PostsSaveRequestDto requestDto);
    Long update(Long id, PostsUpdateRequestDto requestDto);
    void delete(Long id);

    PostsResponseDto findById(Long id);

    List<PostsListResponseDto> findAllDesc();
}
