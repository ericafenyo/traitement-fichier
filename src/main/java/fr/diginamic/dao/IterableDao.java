package fr.diginamic.dao;


import javax.persistence.EntityManager;
import java.util.Set;

/**
 * A generic interface for performing operations on an iterable for a specific entity type.
 *
 * @param <T> The value or data transfer object containing data to be inserted.
 * @param <E> The entity to retrieve.
 */
public interface IterableDao<T, E> {

    /**
     * Inserts entities into the database.
     *
     * @param types the entities to be inserted.
     */
    void bulkInsert(Iterable<T> types);

    /**
     * Inserts entities into the database using the provided EntityManager.
     *
     * @param types   the entities to be inserted.
     * @param manager EntityManager to use for insertion.
     */
    void bulkInsert(Iterable<T> types, EntityManager manager);

    /**
     * Retrieves entities based on the given names.
     *
     * @param names the names to search for.
     * @return the entities that match the provided names.
     */
    Set<E> findByNames(Iterable<String> names);

    /**
     * Retrieves entities based on the given names using the provided EntityManager.
     *
     * @param names   the names to search for.
     * @param manager EntityManager to use for retrieval.
     * @return the entities that match the provided names.
     */
    Set<E> findByNames(Iterable<String> names, EntityManager manager);
}
