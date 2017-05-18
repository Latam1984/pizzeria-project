package project.entities;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (name = "ORDERS_CONTAINS",
    joinColumns = @JoinColumn(name = "ID_ORDERS", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "ID_PIZZA", referencedColumnName = "ID"))
    private List<Pizza> pizzas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getOrder_price() {
        return order_price;
    }

    public void setOrder_price(float order_price) {
        this.order_price = order_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id &&
                Float.compare(orders.order_price, order_price) == 0 &&
                Objects.equals(date, orders.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, order_price);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", order_price=" + order_price +
                '}';
    }
}
