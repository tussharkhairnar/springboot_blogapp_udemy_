package com.services.blogrestapi.service.impl;

import com.services.blogrestapi.entity.Post;
import com.services.blogrestapi.exception.ResourceNotFound;
import com.services.blogrestapi.payload.PostDTO;
import com.services.blogrestapi.payload.PostResponse;
import com.services.blogrestapi.repository.PostRepository;
import com.services.blogrestapi.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

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
    public PostResponse getAllPosts(int pageNo, int pageSize){

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> posts =  postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();


        List<PostDTO> pagedPosts =  listOfPosts
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

        PostResponse postResponse = new PostResponse();
        postResponse.setPageNo(posts.getNumber() );
        postResponse.setContent(pagedPosts);
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
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

    @Override
    public PostDTO updatePostById(long id, PostDTO postDTO) {
        Post post =  postRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Post","id", id));
        post.setTitle(postDTO.getTitle() );
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        postRepository.save(post);

        PostDTO updatedDTO = new PostDTO();
        updatedDTO.setTitle(post.getTitle());
        updatedDTO.setDescription((post.getDescription()));
        updatedDTO.setContent(post.getContent());
        return updatedDTO;
    }

    @Override
    public void deletePostById(long id){
        Post post =  postRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Post","id", id));
      postRepository.delete(post);
    }

}
