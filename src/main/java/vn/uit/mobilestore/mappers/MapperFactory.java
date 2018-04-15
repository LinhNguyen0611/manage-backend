package vn.uit.mobilestore.mappers;

import org.springframework.core.convert.converter.Converter;
import vn.uit.mobilestore.dtos.AbstractDto;
import vn.uit.mobilestore.entities.AbstractEntity;
import vn.uit.mobilestore.requests.AbstractRequest;

public class MapperFactory {

    public static <Entity extends AbstractEntity, Dto extends AbstractDto, Request extends AbstractRequest>
    Converter<Entity, Dto> getSimpleConverter(AbstractMapper<Dto, Entity, Request> mapper) {
        return source -> mapper.toSimpleDto(source);
    }

    public static <Entity extends AbstractEntity, Dto extends AbstractDto, Request extends AbstractRequest>
    Converter<Entity, Dto> getFullConverter(AbstractMapper<Dto, Entity, Request> mapper) {
        return source -> mapper.toFullDto(source);
    }

}
