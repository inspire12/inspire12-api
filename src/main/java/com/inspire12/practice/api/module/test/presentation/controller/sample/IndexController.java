package com.inspire12.practice.api.module.test.presentation.controller.sample;

import com.inspire12.practice.api.config.security.aop.LoginUser;
import com.inspire12.practice.api.module.posts.infrastructure.apdater.PostsRepositoryAdapter;
import com.inspire12.practice.api.module.posts.presentation.response.PostResponse;
import com.inspire12.practice.api.module.user.domain.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsRepositoryAdapter postsService;

    // view resolver
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
//        model.addAttribute("posts",  postsService.findAllDesc(Pageable.unpaged(), user.));
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/chat")
    public String index2(Model model) {

        return "practice/chat/test";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "practice/post/posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
//        PostResponse dto = postsService.findById(id);
//        model.addAttribute("post", dto);

        return "practice/post/posts-update";
    }
}
