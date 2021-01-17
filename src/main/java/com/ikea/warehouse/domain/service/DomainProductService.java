package com.ikea.warehouse.domain.service;

import com.ikea.warehouse.application.exception.WarehouseException;
import com.ikea.warehouse.application.model.dto.ProductDto;
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


/**
 * Domain Service that implements {@link ProductService} to store the manage the {@link Product}
 *
 * @author robertogomez
 */
@Service
@RequiredArgsConstructor
public class DomainProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ArticleService articleService;

    /**
     * Gets a list of products
     *
     * @param page Page of products to retrieve
     * @param size Size of the page
     * @return {@link List} of {@link ProductDto}
     */
    @Transactional(readOnly = true)
    @Override
    public List<Product> getAll(int page, int size) {
        return productRepository.findAll(page, size);
    }


    /**
     * Sells a product
     *
     * @param uuid Identifier of the product to sell
     * @exception NoStockException if there is not enough stock
     * @exception NotFoundException if the product doesn't exist
     */
    @Transactional
    @Override
    public Product sell(UUID uuid) throws NoStockException, NotFoundException {
        Product product = productRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException("Product not found"));
        if (product.getComposition().stream().allMatch(composition -> checkStock(composition, composition.getQuantity()))) {
            product.getComposition()
                    .forEach(composition -> articleService.reduceStock(composition.getArticle(), composition.getQuantity()));
            return product;
        } else {
            throw new NoStockException("Not enough stock");
        }
    }

    /**
     * Checks if there is enough stock to sell the product
     *
     * @param composition Identifier of the product to sell
     * @param quantity Identifier of the product to sell
     * @return True if the product can be sold, false otherwise.
     */
    private boolean checkStock(Composition composition, long quantity) {
        return composition.getArticle().getStock() >= quantity;
    }

    /**
     * Stores a {@link Product}
     *
     * @param product {@link Product} to store
     */
    @Transactional
    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }
}
