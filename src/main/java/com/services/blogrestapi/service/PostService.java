package com.services.blogrestapi.service;

import com.services.blogrestapi.payload.PostDTO;
import com.services.blogrestapi.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
    PostResponse getAllPosts(int pageNo, int pageSize);
    PostDTO getPostById(long id);
    PostDTO updatePostById(long id,  PostDTO postDTO);
    void deletePostById(long id);
}
