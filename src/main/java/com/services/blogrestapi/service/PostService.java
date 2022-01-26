package com.services.blogrestapi.service;

import com.services.blogrestapi.payload.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
    List<PostDTO> getAllPosts();
}
