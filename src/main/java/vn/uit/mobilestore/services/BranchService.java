package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.Branch;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.models.BranchModel;
import vn.uit.mobilestore.models.ModelModel;
import vn.uit.mobilestore.repositories.BranchRepository;
import vn.uit.mobilestore.repositories.ItemRepository;

/**
 * Created by Linh Nguyen on 4/3/2018.
 */
@Service
public class BranchService extends BaseService<BranchRepository, Branch, Integer>{

    @Autowired
    BranchService(BranchRepository repository) {
        super(repository);
    }

    public Page<Branch> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<Branch> branches = findAll(pageRequest);
        return branches;
    }

    public Branch updateBranch(Integer id, BranchModel branchModel) {
        // Find item
        Branch branch = repository.findOne(id);
        // Update
        branch = branch.updateBranch(branchModel);
        this.updateData(branch);
        return branch;
    }
}
