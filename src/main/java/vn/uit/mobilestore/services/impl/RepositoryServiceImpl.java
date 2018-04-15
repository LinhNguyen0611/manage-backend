package vn.uit.mobilestore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.AbstractEntity;
import vn.uit.mobilestore.services.RepositoryService;

import javax.annotation.PostConstruct;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    @Autowired
    private ApplicationContext applicationContext;

    private Repositories repositories;

    @PostConstruct
    protected void initialize() {
        repositories = new Repositories(applicationContext);
    }

    @Override
    public <Entity extends AbstractEntity, Repository extends JpaRepository<Entity, Long>> Repository getRepository(Class<Entity> entityClass) {
        Repository repository = (Repository) repositories.getRepositoryFor(entityClass);
        if (repository == null) {
            repository = (Repository) repositories.getRepositoryFor(entityClass.getSuperclass());
        }

        return repository;
    }
}
