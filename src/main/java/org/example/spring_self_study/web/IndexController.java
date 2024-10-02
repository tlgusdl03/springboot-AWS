package org.example.spring_self_study.web;

import lombok.RequiredArgsConstructor;
import org.example.spring_self_study.domain.posts.Posts;
import org.example.spring_self_study.service.posts.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor //final 필드와 @NonNull이 붙은 필드들을 대상으로 자동으로 생성자를 만들어주는 역할
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
