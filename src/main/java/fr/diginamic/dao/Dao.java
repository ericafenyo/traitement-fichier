package fr.diginamic.dao;

/**
 * A generic interface defining basic operations for Data Access Objects (DAOs).
 *
 * @param <T> Type of the entity to be inserted.
 * @param <E> Type of the entity that will be returned after insertion.
 */
public interface Dao<T, E> {

    /**
     * Inserts a new entity of type T into the data store and returns the inserted entity.
     *
     * @param type The entity to be inserted.
     * @return The inserted entity of type E.
     */
    E insert(T type);
}
