package vn.uit.mobilestore.services;

import vn.uit.mobilestore.entities.AbstractEntity;

public interface AbstractService<Entity extends AbstractEntity> {

    Entity findResource(String id);

    Entity findResourceStrict(String id);

}
