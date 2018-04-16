package vn.uit.mobilestore.entities;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Linh Nguyen on 4/13/2018.
 */
@Entity
@Table(name = "order_details")
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "UPDATE order_details SET is_deleted = 1 WHERE id = ?", check = ResultCheckStyle.COUNT)
public class OrderDetail extends AbstractEntity {

    @ManyToOne
    private Item item;

    @Column
    private Long price;

    @Column
    private Long changePrice;

    @Column
    private Long total;

    @Column
    private String note;

    @ManyToOne
    private Order order;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(Long changePrice) {
        this.changePrice = changePrice;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
