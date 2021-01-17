package com.ikea.warehouse.domain.service;

import com.ikea.warehouse.domain.exception.NotFoundException;
import com.ikea.warehouse.domain.model.Article;
import com.ikea.warehouse.domain.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DomainArticleService implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Article getById(long id) throws NotFoundException {
        return articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article not found"));
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }
}
