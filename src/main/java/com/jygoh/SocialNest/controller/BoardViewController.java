package com.jygoh.SocialNest.controller;

import com.jygoh.SocialNest.domain.Article;
import com.jygoh.SocialNest.dto.ArticleViewResponse;
import com.jygoh.SocialNest.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardViewController {

    private final BoardService boardService;

    @GetMapping("/")
    public String showArticleList(Model model, Principal principal) {
        List<Article> articles = boardService.findAll();

        model.addAttribute("articles", articles);

        if (principal != null) {
            model.addAttribute("loggedInUsername", principal.getName());
        }
        return "articles";
    }

    @GetMapping("/new-article/{id}")
    public String editArticle(@PathVariable long id, Model model, Principal principal) {
        Article article = boardService.findById(id);

        String loggedInUsername = principal.getName();
        if (!article.getAuthor().equals(loggedInUsername)) {
            return "redirect:/error";
        }

        model.addAttribute("article", article);

        return "new-article";
    }

    @GetMapping("/article/{id}")
    public String showArticle(@PathVariable long id, Model model, Principal principal) {
        Article article = boardService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        model.addAttribute("loggedInUsername", principal != null ? principal.getName() : null);

        return "article";
    }

    @GetMapping("/add-article")
    public String showAddArticlePage() {
        return "add-article";
    }
}
