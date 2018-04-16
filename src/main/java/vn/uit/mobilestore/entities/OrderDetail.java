package vn.uit.mobilestore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Linh Nguyen on 4/13/2018.
 */
@Entity
@Table(name = "order_details")
public class OrderDetail {

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
