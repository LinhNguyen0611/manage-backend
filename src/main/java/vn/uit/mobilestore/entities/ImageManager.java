package vn.uit.mobilestore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by Linh Nguyen on 5/5/2018.
 */
@Entity
@Table(name = "ImageManager")
public class ImageManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImageManagerID", nullable = false)
    private Integer imageManagerID;

    @Column(name = "ImageURL")
    private String imageURL;

    @Column(name = "VariantID")
    private Integer variantID;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "VariantID", insertable = false, updatable =  false)
    private Variant variant;

    public ImageManager() {
    }

    public Integer getImageManagerID() {
        return imageManagerID;
    }

    public void setImageManagerID(Integer imageManagerID) {
        this.imageManagerID = imageManagerID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Integer getVariantID() {
        return variantID;
    }

    public void setVariantID(Integer variantID) {
        this.variantID = variantID;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }
}
