package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.entities.Supplier;
import vn.uit.mobilestore.repositories.SupplierRepository;

@Service
public class SupplierService extends BaseService<SupplierRepository, Supplier, Integer> {
    /**
     * Controller
     *
     * @param repository repository
     */
    @Autowired
    SupplierService(SupplierRepository repository) {
        super(repository);
    }

    public Page<Supplier> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<Supplier> suppliers = findAll(pageRequest);
        return suppliers;
    }

    //CRUD method is provided by Base Service. Add another method as needed
}
