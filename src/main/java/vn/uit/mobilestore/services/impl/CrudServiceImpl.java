package vn.uit.mobilestore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.uit.mobilestore.dtos.AbstractDto;
import vn.uit.mobilestore.entities.AbstractEntity;
import vn.uit.mobilestore.exception.business.ResourceNotFoundException;
import vn.uit.mobilestore.mappers.AbstractMapper;
import vn.uit.mobilestore.mappers.MapperFactory;
import vn.uit.mobilestore.requests.AbstractRequest;
import vn.uit.mobilestore.services.CrudService;
import vn.uit.mobilestore.services.MapperService;
import vn.uit.mobilestore.services.RepositoryService;
import vn.uit.mobilestore.utils.ReflectUtils;

import javax.annotation.PostConstruct;
import java.util.List;

public class CrudServiceImpl<Dto extends AbstractDto, Entity extends AbstractEntity, Request extends AbstractRequest>
        extends AbstractServiceImpl<Entity> implements CrudService<Dto, Entity, Request> {

    @Autowired
    private MapperService mapperService;

    @Autowired
    private RepositoryService repositoryService;

    protected AbstractMapper<Dto, Entity, Request> mapper;

    @PostConstruct
    protected void initialize() {
        Class<Dto> dtoClass = (Class<Dto>) ReflectUtils.getParameterType(this.getClass(), 0);
        Class<Entity> entityClass = (Class<Entity>) ReflectUtils.getParameterType(this.getClass(), 1);
        Class<Request> requestClass = (Class<Request>) ReflectUtils.getParameterType(this.getClass(), 2);

        this.mapper = mapperService.getMapper(dtoClass, entityClass, requestClass);
        this.repository = repositoryService.getRepository(entityClass);
    }

    //------------------------------------------Simple dto response-----------------------------------------------------
    @Override
    public List<Dto> getSimpleResources() {
        return mapper.toSimpleDtos(repository.findAll());
    }

    @Override
    public Page<Dto> getSimpleResourcePage(Pageable pageable) {
        return repository.findAll(pageable).map(MapperFactory.getSimpleConverter(mapper));
    }

    @Override
    public Dto createSimpleResource(Request request) {
        Entity entity = mapper.toEntity(request);
        entity = repository.save(entity);

        return mapper.toSimpleDto(entity);
    }

    @Override
    public Dto readSimpleResource(Long id) {
        Entity entity = repository.findOne(id);
        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return mapper.toSimpleDto(entity);
    }

    @Override
    public Dto updateSimpleResource(Long id, Request request) {
        Entity entity = mapper.updateEntity(id, request);
        entity = repository.save(entity);

        return mapper.toSimpleDto(entity);
    }

    @Override
    public Dto deleteSimpleResource(Long id) {
        Entity entity = repository.findOne(id);
        if (entity == null) {
            throw new ResourceNotFoundException();
        }
        Dto dtoResult =  mapper.toSimpleDto(entity);
        repository.delete(entity);

        return dtoResult;
    }

    //--------------------------------------------Full dto response-----------------------------------------------------

    @Override
    public List<Dto> getFullResources() {
        return mapper.toFullDtos(repository.findAll());
    }

    @Override
    public Page<Dto> getFullResourcePage(Pageable pageable) {
        return repository.findAll(pageable).map(MapperFactory.getFullConverter(mapper));
    }

    @Override
    public Dto createFullResource(Request request) {
        Entity entity = mapper.toEntity(request);
        entity = repository.save(entity);

        return mapper.toFullDto(entity);
    }

    @Override
    public Dto readFullResource(Long id) {
        Entity entity = repository.findOne(id);
        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return mapper.toFullDto(entity);
    }

    @Override
    public Dto updateFullResource(Long id, Request request) {
        Entity entity = mapper.updateEntity(id, request);
        entity = repository.save(entity);

        return mapper.toFullDto(entity);
    }

    @Override
    public Dto deleteFullResource(Long id) {
        Entity entity = repository.findOne(id);
        if (entity == null) {
            throw new ResourceNotFoundException();
        }
        Dto dtoResult =  mapper.toFullDto(entity);
        repository.delete(entity);

        return dtoResult;
    }
}
