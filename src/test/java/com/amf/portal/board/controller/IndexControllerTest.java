package com.amf.portal.board.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void loadingMainPageTest() {

        // when
        String body = this.testRestTemplate.getForObject("/", String.class);

        // then
        assertThat(body).contains("스프링부트로 시작하는 웹 서비스");
    }
}