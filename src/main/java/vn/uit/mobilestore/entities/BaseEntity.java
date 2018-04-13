package vn.uit.mobilestore.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Base Access Model
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column
    protected Boolean isDeleted = Boolean.FALSE;
    /**
     * The Creation date.
     */
    @Column(updatable = false)
    protected Date createdDate;

    /**
     * The Modify date.
     */
    @Column
    protected Date modifiedDate;


    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets creation date.
     *
     * @param createdDate the creation date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets modify date.
     *
     * @return the modify date
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets modify date.
     *
     * @param modifiedDate the modify date
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
