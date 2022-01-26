package com.services.blogrestapi.controller;

import com.services.blogrestapi.payload.PostDTO;
import com.services.blogrestapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    ResponseEntity createPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }
}
