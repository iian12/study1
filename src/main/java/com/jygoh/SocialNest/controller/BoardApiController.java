package com.jygoh.SocialNest.controller;

import com.jygoh.SocialNest.domain.Article;
import com.jygoh.SocialNest.dto.AddArticleRequest;
import com.jygoh.SocialNest.dto.ArticleResponse;
import com.jygoh.SocialNest.dto.UpdateArticleRequest;
import com.jygoh.SocialNest.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal) {
        String loggedInUsername = principal.getName();

        Article article = request.toEntity(loggedInUsername);

        Article savedArticle = boardService.save(article);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = boardService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = boardService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id, Principal principal) {
        String loggedInUsername = principal.getName();
        Article article = boardService.findById(id);

        if (article != null && article.getAuthor().equals(loggedInUsername)) {
            boardService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request, Principal principal) {
        String loggedInUsername = principal.getName();

        Article article = boardService.findById(id);
        String articleAuthor = article.getAuthor();

        if (articleAuthor.equals(loggedInUsername)) {
            Article updatedArticle = boardService.update(id, request);
            return ResponseEntity.ok().body(updatedArticle);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}

