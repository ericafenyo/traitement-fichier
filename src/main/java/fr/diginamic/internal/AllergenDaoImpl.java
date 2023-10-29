package fr.diginamic.internal;


import fr.diginamic.dao.AllergenDao;
import fr.diginamic.entities.AllergenEntity;
import fr.diginamic.models.Allergen;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Set;
import java.util.stream.Collectors;

class AllergenDaoImpl implements AllergenDao {

    @Override
    public void bulkInsert(Iterable<Allergen> allergens) {
        insert(allergens, ServiceLoader.createEntityManager());
    }

    @Override
    public void bulkInsert(Iterable<Allergen> allergens, EntityManager manager) {
        insert(allergens, manager);
    }

    @Override
    public Set<AllergenEntity> findByNames(Iterable<String> names) {
        return find(names, ServiceLoader.createEntityManager());
    }

    @Override
    public Set<AllergenEntity> findByNames(Iterable<String> names, EntityManager manager) {
        return find(names, manager);
    }


    private void insert(Iterable<Allergen> allergens, EntityManager manager) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        for (Allergen allergen : allergens) {
            Query query = manager.createNativeQuery("INSERT IGNORE INTO allergens (name) VALUES (?)");
            query.setParameter(1, allergen.getName());
            query.executeUpdate();
        }
        transaction.commit();
    }

    private Set<AllergenEntity> find(Iterable<String> names, EntityManager manager) {
        return manager.createQuery("SELECT a FROM AllergenEntity as a WHERE a.name IN :names", AllergenEntity.class)
                .setParameter("names", names)
                .getResultStream()
                .collect(Collectors.toSet());
    }
}
