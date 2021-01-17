package com.ikea.warehouse.infrastructure.repository;

import com.ikea.warehouse.infrastructure.model.ArticleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring data repository to access to the Article collection on MongoDB
 *
 * @author robertogomez
 */
@Repository
public interface ArticleSpringJpaRepository extends MongoRepository<ArticleEntity, Long> {
}
