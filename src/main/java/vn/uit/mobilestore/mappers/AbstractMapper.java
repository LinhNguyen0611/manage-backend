package vn.uit.mobilestore.mappers;

import org.mapstruct.IterableMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.uit.mobilestore.dtos.AbstractDto;
import vn.uit.mobilestore.entities.AbstractEntity;
import vn.uit.mobilestore.exception.business.ResourceNotFoundException;
import vn.uit.mobilestore.requests.AbstractRequest;
import vn.uit.mobilestore.services.RepositoryService;
import vn.uit.mobilestore.utils.ReflectUtils;

import javax.annotation.PostConstruct;
import java.lang.annotation.*;
import java.util.List;

public abstract class AbstractMapper<Dto extends AbstractDto, Entity extends AbstractEntity, Request extends AbstractRequest> {

    @Autowired
    protected RepositoryService repositoryService;

    protected JpaRepository<Entity, Long> repository;

    @PostConstruct
    public void initialize() {
        Class<Entity> entityClass = (Class<Entity>) ReflectUtils.getParameterType(this.getClass(), 1);
        repository = repositoryService.getRepository(entityClass);
    }

    // Convert from dto to entity when create entity
    @ToEntity
    protected abstract void mapEntity(Request request, @MappingTarget Entity entity);

    // Convert from dto to entity when update entity
    // The default is converted by mapEntity
    // Override this method if create and update is different
    @UpdateEntity
    protected void updateEntity(Request request, @MappingTarget Entity entity) {
        mapEntity(request, entity);
    }

    public Entity toEntity(Request request) {
        Entity entity = ReflectUtils.newInstanceFromParameter(this.getClass(), 1);
        if (entity != null) {
            mapEntity(request, entity);

            return entity;
        }

        return null;
    }

    public Entity updateEntity(Long id, Request request) {
        Entity entity = repository.findOne(id);
        if (entity != null) {
            updateEntity(request, entity);

            return entity;
        }
        else {
            String entityName = ((Class) ReflectUtils.getParameterType(this.getClass(), 1)).getSimpleName();
            throw new ResourceNotFoundException(entityName + " not found");
        }
    }

    @ToEntities
    @IterableMapping(qualifiedBy = ToEntity.class)
    public abstract List<Entity> toEntities(List<Dto> dtos);

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface UpdateEntity {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface ToEntity {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface ToEntities {
    }

    //------------------------------------------------Map Simple Dto----------------------------------------------------
    @SimpleDto
    public abstract Dto toSimpleDto(Entity entity);

    @SimpleDtos
    @IterableMapping(qualifiedBy = SimpleDto.class)
    public abstract List<Dto> toSimpleDtos(List<Entity> entities);

    @Qualifier
    @Inherited
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface SimpleDto {
    }

    @Qualifier
    @Inherited
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface SimpleDtos {
    }

    //-------------------------------------------------Map Full Dto-----------------------------------------------------
    @FullDto
    public abstract Dto toFullDto(Entity entity);

    @FullDtos
    @IterableMapping(qualifiedBy = FullDto.class)
    public abstract List<Dto> toFullDtos(List<Entity> entities);

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface FullDto {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface FullDtos {
    }
}
