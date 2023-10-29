package fr.diginamic.internal;

import fr.diginamic.dao.AllergenDao;
import fr.diginamic.dao.BrandDao;
import fr.diginamic.dao.CategoryDao;
import fr.diginamic.dao.IngredientDao;
import fr.diginamic.dao.ProductDao;
import fr.diginamic.entities.BrandEntity;
import fr.diginamic.entities.CategoryEntity;
import fr.diginamic.entities.FailedProductEntity;
import fr.diginamic.entities.ProductEntity;
import fr.diginamic.models.Allergen;
import fr.diginamic.models.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

class ProductDaoImpl implements ProductDao {

    @Override
    public void bulkInsert(Iterable<Product> products) {
        EntityManager manager = ServiceLoader.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        BrandDao brandDao = ServiceLoader.provideBrandDao();
        CategoryDao categoryDao = ServiceLoader.provideCategoryDao();
        IngredientDao ingredientDao = ServiceLoader.provideIngredientDao();
        AllergenDao allergenDao = ServiceLoader.provideAllergenDao();

        for (Product product : products) {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(product.getName());
            productEntity.setScore(product.getScore());
            productEntity.setEnergy(product.getEnergy());
            productEntity.setFat(product.getFat());

            Set<String> names = product.getAllergens().stream().map(Allergen::getName).collect(Collectors.toSet());

            ExecutorService executor = Executors.newFixedThreadPool(20);
            List<Future<?>> futures = List.of(
                    executor.submit(() -> {
                        BrandEntity brandEntity = brandDao.insert(product.getBrand());
                        productEntity.setBrand(brandEntity);
                    }),

                    executor.submit(() -> {
                        CategoryEntity categoryEntity = categoryDao.insert(product.getCategory());
                        productEntity.setCategory(categoryEntity);
                    }),

                    executor.submit(() -> {
                        ingredientDao.bulkInsert(product.getIngredients());
                    }),
                    executor.submit(() -> {
                        allergenDao.bulkInsert(product.getAllergens());
                    })
            );

            futures.forEach(future -> {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException exception) {
//                    exception.printStackTrace();
                    throw new RuntimeException(exception);
                }
            });

            executor.shutdown();

            productEntity.setIngredients(ingredientDao.findByNames(names, manager));
            productEntity.setAllergens(allergenDao.findByNames(names, manager));
            manager.persist(productEntity);
        }
        transaction.commit();
    }

    @Override
    public void keepErrors(Iterable<String> errors) {
        EntityManager manager = ServiceLoader.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        for (String error : errors) {
            FailedProductEntity failedProduct = new FailedProductEntity();
            failedProduct.setEntry(error);
            manager.persist(failedProduct);
        }
        transaction.commit();
    }
}
