package vn.uit.mobilestore.services;

import vn.uit.mobilestore.constants.MessageCode;
import vn.uit.mobilestore.entities.BaseEntity;
import vn.uit.mobilestore.exceptions.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Base Service
 *
 * @param <R>  the type parameter
 * @param <E>  the type parameter
 * @param <ID> the type parameter
 */
abstract class BaseService<R extends JpaRepository<E, ID>, E extends BaseEntity, ID extends Serializable> {
    /**
     * The Repository.
     */
    protected final R repository;
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * Controller
     *
     * @param repository repository
     */
    BaseService(R repository) {
        this.repository = repository;
    }

    /**
     * Save new entity
     *
     * @param entity Entity to add
     * @return Stored entity
     */
    public E saveData(E entity) {
        entity = repository.saveAndFlush(entity);
        return entity;
    }

    /**
     * Update existing entity
     *
     * @param entity Entity to update
     * @return Stored entity
     */
    public E updateData(E entity) {
        entity = repository.saveAndFlush(entity);
        return entity;
    }

    /**
     * Get entity by ID
     *
     * @param id ID of entity
     * @return Stored entity
     */
    public E getById(ID id) {
        E entity = repository.findOne(id);
        if (entity == null) {
            throw new ApplicationException(MessageCode.ERROR_NOT_FOUND);
        }
        return entity;
    }

    /**
     * Find all entity by Pageable item
     *
     * @param pageable Pageable term
     * @return List all entity by sort term
     */
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public E deleteById(ID id) {
        E entity = repository.findOne(id);
        if (entity == null) {
            throw new ApplicationException(MessageCode.ERROR_NOT_FOUND);
        }
        repository.delete(entity);
        return entity;
    }
}
