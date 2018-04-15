package vn.uit.mobilestore.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.dtos.AbstractDto;
import vn.uit.mobilestore.entities.AbstractEntity;
import vn.uit.mobilestore.mappers.AbstractMapper;
import vn.uit.mobilestore.requests.AbstractRequest;
import vn.uit.mobilestore.services.MapperService;
import vn.uit.mobilestore.utils.ReflectUtils;

import java.lang.reflect.Type;

@Service
public class MapperServiceImpl implements MapperService {

    private static Logger LOGGER = LoggerFactory.getLogger(MapperServiceImpl.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public <Dto extends AbstractDto, Entity extends AbstractEntity, Request extends AbstractRequest, Mapper extends AbstractMapper<Dto, Entity, Request>>
        Mapper getMapper(Class<Dto> dtoClass, Class<Entity> entityClass, Class<Request> requestClass) {

        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);
            if (bean instanceof AbstractMapper) {
                Class dtoType = (Class) ReflectUtils.getParameterType(bean.getClass(), 0);
                Class entityType = (Class) ReflectUtils.getParameterType(bean.getClass(), 1);
                Class requestType = (Class) ReflectUtils.getParameterType(bean.getClass(), 2);
                Type type = bean.getClass().getSuperclass();
                if (dtoClass.equals(dtoType) && entityClass.equals(entityType) && requestClass.equals(requestType)) {
                    return (Mapper) bean;
                }
            }
        }

        return null;
    }
}
