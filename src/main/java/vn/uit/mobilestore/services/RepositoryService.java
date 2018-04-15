package vn.uit.mobilestore.services;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.uit.mobilestore.entities.AbstractEntity;

public interface RepositoryService {

    <Entity extends AbstractEntity, Repository extends JpaRepository<Entity, String>> Repository getRepository(Class<Entity> entityClass);

}
