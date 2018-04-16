package vn.uit.mobilestore.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.uit.mobilestore.config.UrlConfig;
import vn.uit.mobilestore.dtos.BrandDto;
import vn.uit.mobilestore.requests.BrandRequest;
import vn.uit.mobilestore.services.BrandService;

import javax.validation.Valid;

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
    @PreAuthorize("hasRole('ADMIN')")
    public Page<BrandDto> list(@PageableDefault Pageable pageable) {
        return brandService.getSimpleResourcePage(pageable);
    }

    @PostMapping
    @ApiOperation("Create brand")
    @PreAuthorize("hasRole('ADMIN')")
    public BrandDto create(@Valid @RequestBody BrandRequest brandRequest) {
        return brandService.createSimpleResource(brandRequest);
    }

    @GetMapping(UrlConfig.ID_PATH)
    @ApiOperation("Get brand by id")
    @PreAuthorize("hasRole('ADMIN')")
    public BrandDto read(@PathVariable Long id) {
        return brandService.readFullResource(id);
    }

    @PutMapping(UrlConfig.ID_PATH)
    @ApiOperation("Update brand by id")
    @PreAuthorize("hasRole('ADMIN')")
    public BrandDto update(@PathVariable Long id, @Valid @RequestBody BrandRequest brandRequest) {
        return brandService.updateFullResource(id, brandRequest);
    }

    @DeleteMapping(UrlConfig.ID_PATH)
    @ApiOperation("Update brand by id")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public BrandDto delete(@PathVariable Long id) {
        return brandService.deleteSimpleResource(id);
    }
}
