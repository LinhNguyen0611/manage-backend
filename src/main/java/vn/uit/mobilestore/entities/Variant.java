package vn.uit.mobilestore.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/9/2018.
 */
@Entity
@Table(name = "variants")
public class Variant extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String storage;

    @Column(nullable = false)
    private Long priceSold;

    @ManyToOne
    @JoinColumn (insertable = false, updatable = false)
    private Model model;

    @OneToMany(mappedBy = "variant")
    private List<Item> itemList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Long getPriceSold() {
        return priceSold;
    }

    public void setPriceSold(Long priceSold) {
        this.priceSold = priceSold;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
