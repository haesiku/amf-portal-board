package com.amf.portal.board.controller;

import com.amf.portal.board.domain.post.dto.PostsSaveRequestDto;
import com.amf.portal.board.domain.post.dto.PostsUpdateRequestDto;
import com.amf.portal.board.domain.post.model.Posts;
import com.amf.portal.board.domain.post.repository.PostsRepository;
import org.apache.catalina.filters.ExpiresFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsControllerTest {

    @LocalServerPort
    private int port;


    // TestRestTemplate is?
    // TestRestTemplate은 REST 방식으로 개발한 API의 Test를 최적화 하기 위해 만들어진 클래스
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() {
        this.postsRepository.deleteAll();
    }

    @Test
    public void save() {

        // given
        String title = "title";
        String content = "content";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when

        // ResponseEntity is?
        // HTTP 요청(Request) 또는 응답(Response)에 해당하는 HttpHeader와 HttpBody를 포함하는 클래스
        ResponseEntity<Long> responseEntity = this.restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void update(){

        // given
        Posts savedPosts = this.postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author").build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        // HttpEntity is?
        // HTTP 요청 또는 응답에 해당하는 HttpHeader와 HttpBody를 포함하는 클래스
        // HttpEntity --> RequestEntity, ResponseEntity 상속받아 구현한 클래스
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = this.postsRepository.findAll();

        System.out.println("Posts count : " + all.size());
        //assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        //assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

        Posts posts = all.get(0);
        System.out.println("posts[0] : " + posts.getTitle() + " / " + posts.getContent());
        assertThat(posts.getTitle()).isEqualTo(expectedTitle);
        assertThat(posts.getContent()).isEqualTo(expectedContent);
    }
}