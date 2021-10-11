package com.amf.portal.board.service.impl;

import com.amf.portal.board.domain.post.dto.PostsResponseDto;
import com.amf.portal.board.domain.post.dto.PostsSaveRequestDto;
import com.amf.portal.board.domain.post.dto.PostsUpdateRequestDto;
import com.amf.portal.board.domain.post.model.Posts;
import com.amf.portal.board.domain.post.repository.PostsRepository;
import com.amf.portal.board.service.PostsService;
import com.amf.portal.board.web.dto.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return this.postsRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    @Transactional // 이 부분 안쓰면 테스트 코드에서 업데이트 동작 안함
    public Long update(Long id, PostsUpdateRequestDto requestDto){

        Posts posts = this.postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Posts posts = this.postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        this.postsRepository.delete(posts);
    }

    @Override
    public PostsResponseDto findById(Long id) {
        Posts posts = this.postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        return new PostsResponseDto(posts);
    }

    @Override
    @Transactional(readOnly = true) // transaction의 범위는 유지하되 조회기능만 남겨서 조회속도 개선
    public List<PostsListResponseDto> findAllDesc() {
        return this.postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // is equal to .map(posts -> new PostsListResponseDto(posts))
                .collect(Collectors.toList());
    }


}
