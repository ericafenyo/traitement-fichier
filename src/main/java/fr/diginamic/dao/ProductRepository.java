package fr.diginamic.dao;

import fr.diginamic.models.Product;

import java.util.List;


import java.util.List;

/**
 * Interface defining operations to retrieve products based on different criteria.
 */
public interface ProductRepository {

    /**
     * Retrieves a list of products based on the specified category, excluding specific allergens and ingredients.
     *
     * @param category            The category to filter products.
     * @param allergenToExclude   The allergen to exclude from the results.
     * @param ingredientToExclude The ingredient to exclude from the results.
     * @param limit               The maximum number of products to retrieve.
     * @return A list of products filtered by the specified criteria.
     */
    List<Product> getProductsByCategory(
            String category,
            String allergenToExclude,
            String ingredientToExclude,
            int limit
    );

    /**
     * Retrieves a list of products based on the specified brand, excluding specific allergens and ingredients.
     *
     * @param brand               The brand to filter products.
     * @param allergenToExclude   The allergen to exclude from the results.
     * @param ingredientToExclude The ingredient to exclude from the results.
     * @param limit               The maximum number of products to retrieve.
     * @return A list of products filtered by the specified criteria.
     */
    List<Product> getProductsByBrand(
            String brand,
            String allergenToExclude,
            String ingredientToExclude,
            int limit
    );
}
