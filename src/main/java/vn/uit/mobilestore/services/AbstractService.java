package vn.uit.mobilestore.services;

import vn.uit.mobilestore.entities.AbstractEntity;

public interface AbstractService<Entity extends AbstractEntity> {

    Entity findResource(Long id);

    Entity findResourceStrict(Long id);

}
