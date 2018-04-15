package vn.uit.mobilestore.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.uit.mobilestore.dtos.AbstractDto;
import vn.uit.mobilestore.entities.AbstractEntity;
import vn.uit.mobilestore.requests.AbstractRequest;

import java.util.List;

public interface CrudService<Dto extends AbstractDto, Entity extends AbstractEntity, Request extends AbstractRequest>
        extends AbstractService<Entity> {

    List<Dto> getSimpleResources();

    Page<Dto> getSimpleResourcePage(Pageable pageable);

    Dto createSimpleResource(Request request);

    Dto readSimpleResource(String id);

    Dto updateSimpleResource(String id, Request request);

    Dto deleteSimpleResource(String id);

    List<Dto> getFullResources();

    Page<Dto> getFullResourcePage(Pageable pageable);

    Dto createFullResource(Request request);

    Dto readFullResource(String id);

    Dto updateFullResource(String id, Request request);

    Dto deleteFullResource(String id);
}
