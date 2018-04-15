package vn.uit.mobilestore.services.impl;

import org.springframework.stereotype.Service;
import vn.uit.mobilestore.dtos.BrandDto;
import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.requests.BrandRequest;
import vn.uit.mobilestore.services.BrandService;

/**
 * Created by HieuNP on 16/04/2018.
 */
@Service
public class BrandServiceImpl extends CrudServiceImpl<BrandDto, Brand, BrandRequest> implements BrandService {
}
