package project.entities;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private LocalDateTime date;
    /**
     * The price of order
     */
    @Column(name = "ORDER_PRICE")
    private BigDecimal order_price;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getOrder_price() {
        return order_price;
    }

    public void setOrder_price(BigDecimal order_price) {
        this.order_price = order_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id &&
                Objects.equals(date, orders.date) &&
                Objects.equals(order_price, orders.order_price) &&
                Objects.equals(user, orders.user) &&
                Objects.equals(pizzas, orders.pizzas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, order_price, user, pizzas);
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
