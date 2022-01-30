package com.services.blogrestapi.controller;

import com.services.blogrestapi.payload.PostDTO;
import com.services.blogrestapi.payload.PostResponse;
import com.services.blogrestapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    ResponseEntity createPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping
    PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue="0", required = false) int pageNo,
                             @RequestParam(value = "pageSize", defaultValue="10", required = false) int pageSize,
                             @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
                             ){
        return postService.getAllPosts(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    ResponseEntity getPostById(@PathVariable Long id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity updatePostById(@PathVariable Long id, @RequestBody PostDTO postDTO){
        return new ResponseEntity<>(postService.updatePostById(id, postDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deletePostById(@PathVariable Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

}
