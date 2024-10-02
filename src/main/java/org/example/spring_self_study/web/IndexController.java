package org.example.spring_self_study.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.spring_self_study.config.auth.dto.SessionUser;
import org.example.spring_self_study.domain.posts.Posts;
import org.example.spring_self_study.service.posts.PostsService;
import org.example.spring_self_study.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor //final 필드와 @NonNull이 붙은 필드들을 대상으로 자동으로 생성자를 만들어주는 역할
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
