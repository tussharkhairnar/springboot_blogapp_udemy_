package com.services.blogrestapi.service.impl;

import com.services.blogrestapi.entity.Post;
import com.services.blogrestapi.payload.PostDTO;
import com.services.blogrestapi.repository.PostRepository;
import com.services.blogrestapi.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  private PostRepository postRepository;

  public PostServiceImpl(PostRepository postRepository){
      this.postRepository = postRepository;
  }

    @Override
    public PostDTO createPost(PostDTO postDTO) {

      //convert DTO to entity
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setDescription(postDTO.getDescription());

        Post savedPost = postRepository.save(post);

        //Convert entity to dto;
        PostDTO postResponse = new PostDTO();
        postResponse.setId(savedPost.getId());
        postResponse.setTitle(savedPost.getTitle());
        postResponse.setContent(savedPost.getContent());
        postResponse.setDescription(savedPost.getDescription());
        return postResponse;
    }

}
