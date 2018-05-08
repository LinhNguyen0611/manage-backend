package vn.uit.mobilestore.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.uit.mobilestore.entities.BaseEntity;

import java.io.Serializable;

/**
 * Created by Linh Nguyen on 4/19/2018.
 */
public interface IService<R extends JpaRepository<E, ID>, E extends BaseEntity, ID extends Serializable> {
    E saveData(E entity);

    E updateData(E entity);

    E getById(ID id);

    Page<E> findAll(Pageable pageable);

    E deleteById(ID id);

    Page<E> listAll(Integer page, Integer size);

    void deleteOne(ID id);
}
