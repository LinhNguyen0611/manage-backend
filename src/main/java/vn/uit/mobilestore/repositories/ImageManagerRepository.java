package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.ImageManager;

/**
 * Created by Linh Nguyen on 5/5/2018.
 */
@Repository
public interface ImageManagerRepository extends JpaRepository<ImageManager, Integer> {
}
