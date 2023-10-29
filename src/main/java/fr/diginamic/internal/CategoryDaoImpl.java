package fr.diginamic.internal;

import fr.diginamic.dao.CategoryDao;
import fr.diginamic.entities.CategoryEntity;
import fr.diginamic.models.Category;

import javax.persistence.EntityManager;

class CategoryDaoImpl implements CategoryDao {

    @Override
    public CategoryEntity insert(Category category) {
        EntityManager manager = ServiceLoader.createEntityManager();
        manager.getTransaction().begin();

        CategoryEntity entity = findByName(category.getName(), manager);
        if (entity == null) {
            try {
                entity = new CategoryEntity();
                entity.setName(category.getName());
                manager.persist(entity);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }

        manager.getTransaction().commit();
        return entity;
    }

    private CategoryEntity findByName(String name, EntityManager manager) {
        return manager.createQuery("SELECT c FROM CategoryEntity c WHERE c.name=:name", CategoryEntity.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }
}
