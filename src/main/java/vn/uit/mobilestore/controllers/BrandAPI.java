package vn.uit.mobilestore.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;
import vn.uit.mobilestore.dtos.BrandDto;
import vn.uit.mobilestore.dtos.UserDto;
import vn.uit.mobilestore.requests.users.UserCreateRequest;
import vn.uit.mobilestore.services.BrandService;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
@RestController
@RequestMapping("/brands")
public class BrandAPI {

    @Autowired
    private BrandService brandService;

    @GetMapping
    @ApiOperation("Get brands with pagination")
    public Page<BrandDto> list(@PageableDefault Pageable pageable) {
        return brandService.getSimpleResourcePage(pageable);
    }
}
