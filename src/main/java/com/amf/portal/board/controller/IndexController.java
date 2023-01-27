package com.amf.portal.board.controller;

import com.amf.portal.board.service.PostsService;
import com.amf.portal.board.service.dto.PostsResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

 //   @GetMapping("/")
 //   public String index() {
 //       return "index"; // index.mustache 호출
 //   }

     @GetMapping("/")
     public String index(Model model) {
         model.addAttribute("posts", postsService.findAllDesc());
         return "index";
     }

    @GetMapping("/posts/save")
    public String postSave() {
        return "posts-save"; // posts-save.mustache 호출
    }

    private final PostsService postsService;

    // 게시글 수정
    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = this.postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

}
