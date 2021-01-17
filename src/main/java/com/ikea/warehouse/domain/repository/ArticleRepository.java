package com.ikea.warehouse.domain.repository;

import com.ikea.warehouse.domain.model.Article;

import java.util.Optional;

public interface ArticleRepository {
    Optional<Article> findById(long id);
    Article save(Article article);
}
