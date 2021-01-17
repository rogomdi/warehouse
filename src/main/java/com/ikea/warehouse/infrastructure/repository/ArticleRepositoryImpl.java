package com.ikea.warehouse.infrastructure.repository;

import com.googlecode.jmapper.JMapper;
import com.ikea.warehouse.domain.model.Article;
import com.ikea.warehouse.domain.repository.ArticleRepository;
import com.ikea.warehouse.domain.repository.ProductRepository;
import com.ikea.warehouse.infrastructure.model.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interfaces that implements {@link ProductRepository} to store and get the products
 *
 * @author robertogomez
 */
@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {
    
    private final ArticleSpringJpaRepository articleSpringJpaRepository;
    private final JMapper<Article, ArticleEntity> articleMapper = new JMapper<>(Article.class, ArticleEntity.class);
    private final JMapper<ArticleEntity, Article> articleEntityMapper = new JMapper<>(ArticleEntity.class, Article.class);

    /**
     * Gets an article by its id
     *
     * @param id identifier of the article
     * @return an {@link Article}
     */
    @Override
    public Optional<Article> findById(long id) {
        return this.articleSpringJpaRepository.findById(id).map(articleMapper::getDestination);
    }

    /**
     * Stores a {@link Article}
     *
     * @param article {@link Article} to store
     */
    @Override
    public Article save(Article article) {
        return this.articleMapper.getDestination(this.articleSpringJpaRepository.save(this.articleEntityMapper.getDestination(article)));
    }

}
