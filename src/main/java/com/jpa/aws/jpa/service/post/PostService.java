package com.jpa.aws.jpa.service.post;

import com.jpa.aws.jpa.domain.post.Post;
import com.jpa.aws.jpa.domain.post.PostRepository;
import com.jpa.aws.jpa.web.dto.PostListResponseDto;
import com.jpa.aws.jpa.web.dto.PostResponseDto;
import com.jpa.aws.jpa.web.dto.PostSaveRequestDto;
import com.jpa.aws.jpa.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );

        post.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostResponseDto findById(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );

        return new PostResponseDto(entity);
    }

    //readOnly = true > 조회 기능만 남겨두어 조회 속도 개선
    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostListResponseDto::new) // == map(post -> new PostListResponseDto(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        postRepository.delete(post);
    }
}
