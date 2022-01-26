package com.services.blogrestapi.service.impl;

import com.services.blogrestapi.entity.Post;
import com.services.blogrestapi.exception.ResourceNotFound;
import com.services.blogrestapi.payload.PostDTO;
import com.services.blogrestapi.repository.PostRepository;
import com.services.blogrestapi.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map( post-> {
                    PostDTO postResponse = new PostDTO();
                    postResponse.setId(post.getId());
                    postResponse.setTitle(post.getTitle());
                    postResponse.setContent(post.getContent());
                    postResponse.setDescription(post.getDescription());
                    return postResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(long id) {
        Post post =  postRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Post","id", id));
        PostDTO postResponse = new PostDTO();
        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setDescription(post.getDescription());
        return postResponse;
  }

}
