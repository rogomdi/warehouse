package com.ikea.warehouse.domain.service;

import com.ikea.warehouse.domain.exception.NotFoundException;
import com.ikea.warehouse.domain.model.Article;

public interface ArticleService {
    Article getById(long id) throws NotFoundException;
    Article save(Article article);

}
