package vn.uit.mobilestore.entities;

import vn.uit.mobilestore.enums.OrderStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/13/2018.
 */
@Entity
@Table(name = "Order")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID", nullable = false)
    private Integer orderID;

    @Column(nullable = false)
    private Long total;

    @Column
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @Column
    private Date receivedDate;


    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "CustomerID", insertable = false, updatable = false)
    private Customer customer;

}
