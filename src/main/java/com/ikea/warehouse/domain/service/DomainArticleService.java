package com.ikea.warehouse.domain.service;

import com.ikea.warehouse.domain.exception.NotFoundException;
import com.ikea.warehouse.domain.model.Article;
import com.ikea.warehouse.domain.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Domain Service that implements {@link ArticleService} to store the manage the {@link Article}
 *
 * @author robertogomez
 */
@Service
@RequiredArgsConstructor
public class DomainArticleService implements ArticleService {

    private final ArticleRepository articleRepository;

    /**
     * Gets an article by its id
     *
     * @param id identifier of the article
     * @return an {@link Article}
     */
    @Transactional(readOnly = true)
    @Override
    public Article getById(long id) throws NotFoundException {
        return articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article not found"));
    }

    /**
     * Stores a {@link Article}
     *
     * @param article {@link Article} to store
     */
    @Transactional
    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    /**
     * Decreases the stock for an article
     *  @param article Identifier of the product to sell
     * @param stockSold Identifier of the product to sell
     * @return the new {@link Article} with the stock updated
     */
    public Article reduceStock(Article article, int stockSold) {
        article.setStock(article.getStock() - stockSold);
        return articleRepository.save(article);
    }
}
