package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dtos.PostDTO;
import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @GetMapping()
    public ResponseEntity<List<PostDTO>> getPosts(Principal principal) {
        return ResponseEntity.ok(
                postMapper.toDTO(
                        postService.getAll(principal.getName())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("id") int id) {
        return ResponseEntity.ok(
                postMapper.toDTO(
                        postService.getPost(id)));
    }

    @PostMapping()
    public ResponseEntity<PostDTO> savePost(Principal principal, @RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(
                postMapper.toDTO(
                        postService.save(principal.getName(), postDTO)));
    }
}