package vn.uit.mobilestore.mappers;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.uit.mobilestore.dtos.UserDto;
import vn.uit.mobilestore.entities.User;
import vn.uit.mobilestore.requests.UserRequest;
import vn.uit.mobilestore.requests.users.UserCreateRequest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = { RoleMapper.class }
)
public abstract class UserMapper extends AbstractMapper<UserDto, User, UserRequest> {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @CreateUser
    @Mappings({
            @Mapping(target = "password", expression = "java(passwordEncoder.encode(userCreateRequest.getPassword()))")
    })
    public abstract User createUser(UserCreateRequest userCreateRequest);

    @ToEntity
    protected abstract void mapEntity(UserRequest request, @MappingTarget User entity);

    @SimpleDto
    @Mappings({
            @Mapping(target = "roles", ignore = true)
    })
    public abstract UserDto toSimpleDto(User entity);

    @SimpleDtos
    @IterableMapping(qualifiedBy = SimpleDto.class)
    public abstract List<UserDto> toSimpleDtos(List<User> entities);

    @FullDto
    @Mappings({
            @Mapping(target = "roles", qualifiedBy = SimpleDtos.class)
    })
    public abstract UserDto toFullDto(User entity);

    @FullDtos
    @IterableMapping(qualifiedBy = FullDto.class)
    public abstract List<UserDto> toFullDtos(List<User> entities);

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface CreateUser {
    }
}
