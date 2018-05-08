package vn.uit.mobilestore.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import vn.uit.mobilestore.constants.Const;
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
abstract class BaseService<R extends JpaRepository<E, ID>, E extends BaseEntity, ID extends Serializable> implements IService<R, E, ID> {
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
    @Override
    public E saveData(E entity) {
        LOG.info(Const.LOGGING_SERVICE_BEGIN + " saveData");
        entity = repository.saveAndFlush(entity);
        return entity;
    }

    /**
     * Update existing entity
     *
     * @param entity Entity to update
     * @return Stored entity
     */
    @Override
    public E updateData(E entity) {
        LOG.info(Const.LOGGING_SERVICE_BEGIN + " updateData");
        entity = repository.saveAndFlush(entity);
        return entity;
    }

    /**
     * Get entity by ID
     *
     * @param id ID of entity
     * @return Stored entity
     */
    @Override
    public E getById(ID id) {
        LOG.info(Const.LOGGING_SERVICE_BEGIN + " getById {}", id);
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
    @Override
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public E deleteById(ID id) {
        E entity = repository.findOne(id);
        if (entity == null) {
            throw new ApplicationException(MessageCode.ERROR_NOT_FOUND);
        }
        repository.delete(entity);
        return entity;
    }

    @Override
    public Page<E> listAll(Integer page, Integer size) {
        LOG.info(Const.LOGGING_SERVICE_BEGIN + " listAll, page={},size={}", page, size);
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        return findAll(pageRequest);
    }

    @Override
    public void deleteOne(ID id) {
        LOG.info(Const.LOGGING_SERVICE_BEGIN + " deleteOne {}", id);
        E entity = (E) this.getById(id);
        entity.setActive(false);
        this.saveData(entity);
    }
}
