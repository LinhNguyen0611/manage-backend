package vn.uit.mobilestore.services;

import vn.uit.mobilestore.dtos.BrandDto;
import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.requests.BrandRequest;

/**
 * Created by HieuNP on 16/04/2018.
 */
public interface BrandService extends CrudService<BrandDto, Brand, BrandRequest> {
}
