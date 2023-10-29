package fr.diginamic.internal;

import fr.diginamic.dao.ProductRepository;
import fr.diginamic.entities.ProductEntity;
import fr.diginamic.models.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

class ProductRepositoryImpl implements ProductRepository {

    @Override
    public List<Product> getProductsByCategory(
            String category, String allergenToExclude, String ingredientToExclude, int limit
    ) {
        EntityManager manager = ServiceLoader.createEntityManager();
        Exclusion exclusion = getResult(allergenToExclude, ingredientToExclude);
        String statement = buildByCategoryStatement(exclusion);

        TypedQuery<ProductEntity> query = manager.createQuery(statement, ProductEntity.class);
        query.setParameter("category", category);
        query.setMaxResults(10);
        if (exclusion.isBoth) {
            query.setParameter("allergen", "%" + allergenToExclude + "%");
            query.setParameter("ingredient", "%" + ingredientToExclude + "%");
        } else if (exclusion.isIngredient) {
            query.setParameter("ingredient", "%" + ingredientToExclude + "%");
        } else if (exclusion.isAllergen) {
            query.setParameter("allergen", "%" + allergenToExclude + "%");
        }

        List<ProductEntity> entities = query.getResultList();
        return entities.stream()
                .map(ServiceLoader.provideProductMapper())
                .toList();
    }

    @Override
    public List<Product> getProductsByBrand(String brand, String allergenToExclude, String ingredientToExclude, int limit) {
        EntityManager manager = ServiceLoader.createEntityManager();
        Exclusion exclusion = getResult(allergenToExclude, ingredientToExclude);
        String statement = buildByBrandStatement(exclusion);

        TypedQuery<ProductEntity> query = manager.createQuery(statement, ProductEntity.class);
        query.setParameter("brand", brand);
        query.setMaxResults(10);

        if (exclusion.isBoth) {
            query.setParameter("allergen", "%" + allergenToExclude + "%");
            query.setParameter("ingredient", "%" + ingredientToExclude + "%");
        } else if (exclusion.isIngredient) {
            query.setParameter("ingredient", "%" + ingredientToExclude + "%");
        } else if (exclusion.isAllergen) {
            query.setParameter("allergen", "%" + allergenToExclude + "%");
        }

        List<ProductEntity> entities = query.getResultList();
        return entities.stream()
                .map(ServiceLoader.provideProductMapper())
                .toList();
    }

    private static String buildByBrandStatement(Exclusion exclusion) {
        StringBuilder builder = new StringBuilder("SELECT DISTINCT p FROM ProductEntity as p");
        if (exclusion.isBoth) {
            builder.append(" JOIN p.allergens as a JOIN p.ingredients as i");
        } else if (exclusion.isIngredient) {
            builder.append(" JOIN p.ingredients as i");
        } else if (exclusion.isAllergen) {
            builder.append(" JOIN p.allergens as a");
        }

        builder.append(" WHERE p.brand.name = :brand");

        if (exclusion.isIngredient) {
            builder.append(" AND i.name NOT LIKE :ingredient");
        }

        if (exclusion.isAllergen) {
            builder.append(" AND a.name NOT LIKE :allergen");
        }

        builder.append(" ORDER BY p.score");

        return builder.toString();
    }

    private static String buildByCategoryStatement(Exclusion exclusion) {
        StringBuilder builder = new StringBuilder("SELECT DISTINCT p FROM ProductEntity as p");

        if (exclusion.isBoth()) {
            builder.append(" JOIN p.allergens as a JOIN p.ingredients as i");
        } else if (exclusion.isIngredient()) {
            builder.append(" JOIN p.ingredients as i");
        } else if (exclusion.isAllergen()) {
            builder.append(" JOIN p.allergens as a");
        }

        builder.append(" WHERE p.category.name = :category");

        if (exclusion.isIngredient()) {
            builder.append(" AND i.name NOT LIKE :ingredient");
        }

        if (exclusion.isAllergen()) {
            builder.append(" AND a.name NOT LIKE :allergen");
        }

        builder.append(" ORDER BY p.score");

        return builder.toString();
    }

    private static Exclusion getResult(String allergenToExclude, String ingredientToExclude) {
        boolean isBoth = (ingredientToExclude != null && allergenToExclude != null);
        boolean isIngredient = (ingredientToExclude != null && allergenToExclude == null);
        boolean isAllergen = (ingredientToExclude == null && allergenToExclude != null);
        return new Exclusion(isBoth, isIngredient, isAllergen);
    }

    private record Exclusion(boolean isBoth, boolean isIngredient, boolean isAllergen) { }
}
