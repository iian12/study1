package com.jygoh.SocialNest.repository;

import com.jygoh.SocialNest.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Article, Long> {
}
