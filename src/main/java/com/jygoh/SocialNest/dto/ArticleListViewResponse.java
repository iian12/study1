package com.jygoh.SocialNest.dto;

import com.jygoh.SocialNest.domain.Article;
import lombok.Getter;

@Getter
public class ArticleListViewResponse {

    private final Long id;
    private final String title;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
    }
}
