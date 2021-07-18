package com.inspire12.practice.api.web;

import com.inspire12.practice.api.domain.posts.Posts;
import com.inspire12.practice.api.service.posts.PostsService;
import com.inspire12.practice.api.web.dto.PostsListResponseDto;
import com.inspire12.practice.api.web.dto.PostsResponseDto;
import com.inspire12.practice.api.web.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;
    // view resolver
    @GetMapping
    public String index(Model model) {
        List<PostsListResponseDto> post = postsService.findAllDesc();
        model.addAttribute("posts", post);
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
    @GetMapping("/test")
    public String index() {
        return "test";
    }
}
