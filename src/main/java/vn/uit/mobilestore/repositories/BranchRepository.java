package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.Branch;

/**
 * Created by Linh Nguyen on 4/3/2018.
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

}
