package org.example.spring_self_study.web;

import lombok.RequiredArgsConstructor;
import org.example.spring_self_study.service.posts.PostsService;
import org.example.spring_self_study.web.dto.PostsResponseDto;
import org.example.spring_self_study.web.dto.PostsSaveRequestDto;
import org.example.spring_self_study.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

//    @GetMapping("/posts/save")
//    public String postsSave(){
//        return "posts-save";
//    }
}
