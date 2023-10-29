package fr.diginamic.dao;

import fr.diginamic.entities.ProductEntity;
import fr.diginamic.models.Product;

import java.util.function.Function;

/**
 * Converts a product entity to a product domain model.
 */
public interface ProductMapper extends Function<ProductEntity, Product> { }
