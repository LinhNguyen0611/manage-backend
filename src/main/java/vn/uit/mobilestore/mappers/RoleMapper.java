package vn.uit.mobilestore.mappers;

import org.mapstruct.*;
import vn.uit.mobilestore.dtos.RoleDto;
import vn.uit.mobilestore.entities.Role;
import vn.uit.mobilestore.requests.RoleRequest;

import java.util.List;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = { UserMapper.class }
)
public abstract class RoleMapper extends AbstractMapper<RoleDto, Role, RoleRequest> {

    @ToEntity
    protected abstract void mapEntity(RoleRequest request, @MappingTarget Role entity);

    @SimpleDto
    @Mappings({
            @Mapping(target = "users", ignore = true)
    })
    public abstract RoleDto toSimpleDto(Role entity);

    @SimpleDtos
    @IterableMapping(qualifiedBy = SimpleDto.class)
    public abstract List<RoleDto> toSimpleDtos(List<Role> entities);

    @FullDto
    @Mappings({
            @Mapping(target = "users", qualifiedBy = SimpleDtos.class)
    })
    public abstract RoleDto toFullDto(Role entity);

}
