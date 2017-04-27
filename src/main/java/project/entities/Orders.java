package project.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by Aleksey on 25.04.2017.
 */
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "DATE")
    private String date;
    @Column(name = "ORDER_PRICE")
    private float order_price;


}
