package com.ikea.warehouse.domain.repository;

import com.ikea.warehouse.domain.model.Article;

import java.util.Optional;

/**
 * Interface that defines the Repository to access the stored articles
 *
 * @author robertogomez
 */
public interface ArticleRepository {
    Optional<Article> findById(long id);
    Article save(Article article);
}
