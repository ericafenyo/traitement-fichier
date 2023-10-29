package fr.diginamic.internal;

import fr.diginamic.dao.AllergenDao;
import fr.diginamic.dao.BrandDao;
import fr.diginamic.dao.CategoryDao;
import fr.diginamic.dao.IngredientDao;
import fr.diginamic.dao.ProductDao;
import fr.diginamic.dao.ProductMapper;
import fr.diginamic.dao.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServiceLoader {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("database_config");
    private static final ProductMapper productMapper = new ProductMapperImpl();
    private static final BrandDao brandDao = new BrandDaoImpl();
    private static final ProductDaoImpl productDao = new ProductDaoImpl();
    private static final CategoryDaoImpl categoryDao = new CategoryDaoImpl();
    private static final IngredientDaoImpl ingredientDao = new IngredientDaoImpl();
    private static final AllergenDao allergenDao = new AllergenDaoImpl();
    private static final ProductRepository productRepository = new ProductRepositoryImpl();

    public static EntityManager createEntityManager() {
        // Construct an entity manager for handling database transactions
        return factory.createEntityManager();
    }

    public static BrandDao provideBrandDao() {
        return brandDao;
    }

    public static ProductDao provideProductDao() {
        return productDao;
    }

    public static CategoryDao provideCategoryDao() {
        return categoryDao;
    }

    public static IngredientDao provideIngredientDao() {
        return ingredientDao;
    }

    public static ProductMapper provideProductMapper() {
        return productMapper;
    }

    public static AllergenDao provideAllergenDao() {
        return allergenDao;
    }

    public static ProductRepository provideProductRepository() {
        return productRepository;
    }
}
