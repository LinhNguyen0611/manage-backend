package vn.uit.mobilestore.services.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.uit.mobilestore.entities.AbstractEntity;
import vn.uit.mobilestore.exception.business.ResourceNotFoundException;
import vn.uit.mobilestore.services.AbstractService;

public abstract class AbstractServiceImpl<Entity extends AbstractEntity> implements AbstractService<Entity> {

    protected JpaRepository<Entity, Long> repository;

    protected JpaRepository<Entity, Long> getRepository() {
        return repository;
    }

    protected void setRepository(JpaRepository<Entity, Long> repository) {
        this.repository = repository;
    }

    @Override
    public Entity findResource(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Entity findResourceStrict(Long id) {
        Entity entity = repository.findOne(id);
        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return entity;
    }

}
