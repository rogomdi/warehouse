package com.ikea.warehouse.domain.service;

import com.ikea.warehouse.domain.exception.NotFoundException;
import com.ikea.warehouse.domain.model.Article;

/**
 * Interface that defines the API for the service to manage the articles
 *
 * @author robertogomez
 */
public interface ArticleService {
    Article getById(long id) throws NotFoundException;
    Article save(Article article);
    Article reduceStock(Article article, int stockSold);

}
