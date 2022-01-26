package com.services.blogrestapi.service;

import com.services.blogrestapi.payload.PostDTO;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
}
