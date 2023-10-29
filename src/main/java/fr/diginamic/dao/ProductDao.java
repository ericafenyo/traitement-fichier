package fr.diginamic.dao;


import fr.diginamic.models.Product;

import java.util.Collection;

/**
 * A DAO (Data Access Object) interface defining operations for managing products.
 */
public interface ProductDao {

    /**
     * Bulk inserts a collection of products into the database.
     *
     * @param products A collection of products to be inserted.
     */
    void bulkInsert(Iterable<Product> products);

    /**
     * Keeps a record of errors related to products.
     *
     * @param errors A collection of failed entries.
     */
    void keepErrors(Iterable<String> errors);
}
