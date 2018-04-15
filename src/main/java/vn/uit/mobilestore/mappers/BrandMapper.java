package vn.uit.mobilestore.mappers;

import org.mapstruct.*;
import vn.uit.mobilestore.dtos.BrandDto;
import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.entities.Role;
import vn.uit.mobilestore.requests.BrandRequest;

import java.util.List;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class BrandMapper extends AbstractMapper<BrandDto, Brand, BrandRequest> {

    @ToEntity
    protected abstract void mapEntity(BrandRequest request, @MappingTarget Brand entity);

    @SimpleDto
    public abstract BrandDto toSimpleDto(Brand entity);

    @SimpleDtos
    @IterableMapping(qualifiedBy = SimpleDto.class)
    public abstract List<BrandDto> toSimpleDtos(List<Brand> entities);

    @FullDto
    public abstract BrandDto toFullDto(Role entity);

}
