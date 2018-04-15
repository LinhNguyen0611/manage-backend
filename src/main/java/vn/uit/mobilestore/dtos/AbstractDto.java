package vn.uit.mobilestore.dtos;

/**
 * Created by HieuNP on 13/04/2018.
 */
public class AbstractDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        if (!(obj instanceof AbstractDto)) {
            return false;
        }
        AbstractDto other = (AbstractDto) obj;
        if (getId() == null || other.getId() == null) {
            return false;
        }
        if (!getId().equals(other.getId())) {
            return false;
        }

        return true;
    }
}
