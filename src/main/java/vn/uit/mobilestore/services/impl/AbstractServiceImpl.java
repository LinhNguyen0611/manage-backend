package vn.uit.mobilestore.services.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.uit.mobilestore.entities.AbstractEntity;
import vn.uit.mobilestore.exception.business.ResourceNotFoundException;
import vn.uit.mobilestore.services.AbstractService;

public abstract class AbstractServiceImpl<Entity extends AbstractEntity> implements AbstractService<Entity> {

    protected JpaRepository<Entity, String> repository;

    protected JpaRepository<Entity, String> getRepository() {
        return repository;
    }

    protected void setRepository(JpaRepository<Entity, String> repository) {
        this.repository = repository;
    }

    @Override
    public Entity findResource(String id) {
        return repository.findOne(id);
    }

    @Override
    public Entity findResourceStrict(String id) {
        Entity entity = repository.findOne(id);
        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return entity;
    }

}
