package com.ikea.warehouse.domain.service;

import com.ikea.warehouse.domain.exception.NoStockException;
import com.ikea.warehouse.domain.exception.NotFoundException;
import com.ikea.warehouse.domain.model.Article;
import com.ikea.warehouse.domain.model.Composition;
import com.ikea.warehouse.domain.model.Product;
import com.ikea.warehouse.domain.repository.ArticleRepository;
import com.ikea.warehouse.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DomainProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAll(int page, int size) {
        return productRepository.findAll(page, size);
    }

    @Transactional
    @Override
    public Product sell(UUID uuid) throws NoStockException, NotFoundException {
        Product product = productRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException("Product not found"));
        if (product.getComposition().stream().allMatch(composition -> checkStock(composition, composition.getQuantity()))) {
            product.getComposition()
                    .forEach(composition -> reduceStock(composition.getArticle(), composition.getQuantity()));
            return product;
        } else {
            throw new NoStockException("Not enough stock");
        }
    }

    private boolean checkStock(Composition composition, long quantity) {
        return composition.getArticle().getStock() >= quantity;
    }

    private Article reduceStock(Article article, long stockSold) {
        article.setStock(article.getStock() - stockSold);
        return articleRepository.save(article);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }
}
