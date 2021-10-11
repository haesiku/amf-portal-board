package com.amf.portal.board.domain.post.repository;

import com.amf.portal.board.domain.post.model.Posts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void loadPostsTest() {

        String title = "오늘 할일";
        String content = "일주일 동안 진행한 업무를 정리하세요";

        this.postsRepository.save(Posts.builder().
                title(title).
                content(content).
                author("haesiku@gmail.com")
                .build());

        List<Posts> postsList = this.postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void CreateModifyTimeSaveTest() {
        // given
        LocalDateTime now = LocalDateTime.now();
        this.postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = this.postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>> CreateDate=" + posts.getCreatedDate() + ", ModifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}