package project.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Entity of orders
 *
 * Created by Andrey on 25.04.2017.
 */
@Entity
@Table(name = "orders")
public class Orders{
    /**
     * The unique identifier for each order.
     */
    @Id
    @Column(name = "ID")
    private int id;
    /**
     * Date when order was created
     */
    @Column(name = "DATE")
    private String date;
    /**
     * The price of order
     */
    @Column(name = "ORDER_PRICE")
    private float order_price;




}
