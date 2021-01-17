package com.ikea.warehouse.infrastructure.repository;

import com.ikea.warehouse.infrastructure.model.ArticleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleSpringJpaRepository extends MongoRepository<ArticleEntity, Long> {
}
