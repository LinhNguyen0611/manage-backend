package vn.uit.mobilestore.requests;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by HieuNP on 16/04/2018.
 */
public class BrandRequest extends AbstractRequest {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String country;

    @NotNull
    @NotBlank
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
