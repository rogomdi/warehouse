package com.ikea.warehouse.infrastructure.repository;

import com.googlecode.jmapper.JMapper;
import com.ikea.warehouse.domain.model.Article;
import com.ikea.warehouse.domain.repository.ArticleRepository;
import com.ikea.warehouse.infrastructure.model.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {
    
    private final ArticleSpringJpaRepository articleSpringJpaRepository;
    private final JMapper<Article, ArticleEntity> articleMapper = new JMapper<>(Article.class, ArticleEntity.class);
    private final JMapper<ArticleEntity, Article> articleEntityMapper = new JMapper<>(ArticleEntity.class, Article.class);
    
    @Override
    public Optional<Article> findById(long id) {
        return this.articleSpringJpaRepository.findById(id).map(articleMapper::getDestination);
    }

    @Override
    public Article save(Article article) {
        return this.articleMapper.getDestination(this.articleSpringJpaRepository.save(this.articleEntityMapper.getDestination(article)));
    }

}
