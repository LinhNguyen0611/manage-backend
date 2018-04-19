package vn.uit.mobilestore.entities;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Base Access Model
 */
@MappedSuperclass
public class BaseEntity {
    /**
     * The Creation date.
     */
    @Column(name = "created_date", updatable = false)
    protected Date createdDate;

    /**
     * The Modify date.
     */
    @Column(name = "modified_date")
    protected Date modifiedDate;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Column(name = "is_active")
    protected Boolean isActive = true;


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

    /**
     * Update time stamps.
     */
    @PreUpdate
    public void updateTimeStamps() {
        modifiedDate = new Date();
        if (createdDate == null) {
            createdDate = new Date();
        }
    }

    /**
     * Update time stamps for createdDate
     */
    @PrePersist
    public void updateCreationDate() {
        createdDate = new Date();
        modifiedDate = createdDate;
    }
}
