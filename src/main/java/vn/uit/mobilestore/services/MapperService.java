package vn.uit.mobilestore.services;

import vn.uit.mobilestore.dtos.AbstractDto;
import vn.uit.mobilestore.entities.AbstractEntity;
import vn.uit.mobilestore.mappers.AbstractMapper;
import vn.uit.mobilestore.requests.AbstractRequest;

public interface MapperService {

    <Dto extends AbstractDto, Entity extends AbstractEntity, Request extends AbstractRequest, Mapper extends AbstractMapper<Dto, Entity, Request>>
        Mapper getMapper(Class<Dto> dtoClass, Class<Entity> entityClass, Class<Request> requestClass);
}
