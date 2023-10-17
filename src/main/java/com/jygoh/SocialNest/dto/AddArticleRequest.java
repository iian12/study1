package com.jygoh.SocialNest.dto;

import com.jygoh.SocialNest.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;
    private String author;

    public Article toEntity(String loggedInUsername) {
        return Article.builder()
                .title(title)
                .content(content)
                .author(loggedInUsername)
                .build();
    }
}
