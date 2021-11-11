package com.newts.newtapp.application;

/**
 * A generic interface to be implemented by Data Access Objects. Defines the core functionality of every DAO.
 */
public interface DataAccessObject<T> {

    /**
     * Return the entity with the given id.
     * @param id            ID of entity.
     * @return              The entity associated with the given id.
     * @throws Exception    If no entity exists with given id.
     */
    T get(int id) throws Exception;

    /**
     * Update the corresponding entity in the attached data store to match the provided entity.
     * @param entity    Updated entity.
     * @return          True iff update was successful.
     */
    boolean update(T entity);

    /**
     * Insert the given entity to the attached data store.
     * @param entity    Entity to insert.
     * @return          True iff insertion was successful.
     */
    boolean insert(T entity);

    /**
     * Delete the entity with provided id from the attached data store. Use with caution.
     * @param id    ID of entity to delete.
     * @return      True iff deletion was successful.
     */
    boolean delete(int id);
}
