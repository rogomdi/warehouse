package com.ikea.warehouse.application.service;

import com.googlecode.jmapper.JMapper;
import com.ikea.warehouse.application.exception.ApplicationError;
import com.ikea.warehouse.application.exception.WarehouseException;
import com.ikea.warehouse.application.model.dto.ArticleDto;
import com.ikea.warehouse.domain.exception.NotFoundException;
import com.ikea.warehouse.domain.model.Article;
import com.ikea.warehouse.domain.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Application service to interact with articles in the domain layer
 *
 * @author robertogomez
 */
@Service
@RequiredArgsConstructor
public class ApplicationArticleService {

    private final ArticleService articleService;
    private final JMapper<ArticleDto, Article> articleDtoMapper = new JMapper<>(ArticleDto.class, Article.class);
    private final JMapper<Article, ArticleDto> articleMapper = new JMapper<>(Article.class, ArticleDto.class);

    /**
     * Get an article by its id
     *
     * @param id identifier of the article
     * @return {@link ArticleDto}
     */
    public ArticleDto getArticle(long id) {
        try {
            return this.articleDtoMapper.getDestination(articleService.getById(id));
        } catch (NotFoundException e) {
            throw new WarehouseException(ApplicationError.INVALID_OPERATION_NOT_FOUND, e);
        }
    }

    /**
     * Stores a list of articles
     *
     * @param articleDtos list of articles to store
     */
    public void store(List<ArticleDto> articleDtos) {
        articleDtos.forEach(articleDto -> articleService.save(articleMapper.getDestination(articleDto)));
    }

}
