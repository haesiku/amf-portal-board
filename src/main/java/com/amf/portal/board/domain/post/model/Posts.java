package com.amf.portal.board.domain.post.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor      // 기본 생성자 자동 추가@Entity
@Entity
@Data
public class Posts extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "title")//, length = 500, nullable = false)
    private String title;

    @Column(name = "content")//, columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "author")
    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
