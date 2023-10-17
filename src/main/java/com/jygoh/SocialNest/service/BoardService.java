package com.jygoh.SocialNest.service;

import com.jygoh.SocialNest.domain.Article;
import com.jygoh.SocialNest.dto.AddArticleRequest;
import com.jygoh.SocialNest.dto.UpdateArticleRequest;
import com.jygoh.SocialNest.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Article save(Article request) {

        return boardRepository.save(request);
    }

    public List<Article> findAll() {
        return boardRepository.findAll();
    }

    public Article findById(long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
