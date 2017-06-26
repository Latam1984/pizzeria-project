package project.entities;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * The class implements a set of standard methods for working
 * with entity of the Orders.
 *
 * @author Aleksey
 */

@Entity
@Table(name = "orders")
public class Orders {

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
    private Timestamp date;

    /**
     * The price of order
     */
    @Column(name = "ORDER_PRICE")
    private BigDecimal order_price;

    /**
     * Entity of user
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ORDERS_CONTAINS",
            joinColumns = @JoinColumn(name = "ID_ORDERS", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_PIZZA", referencedColumnName = "ID"))

    private List<Pizza> pizzas;

    /**
     * The default constructor of entities orders.
     */
    public Orders() {
    }

    /**
     * Constructor
     * @param id          a unique identifier for order.
     * @param order_price display general price of order of user.
     */
    public Orders(Integer id, BigDecimal order_price) {
        this.id = id;
        this.order_price = order_price;
    }

    /**
     * Constructor
     *
     * @param id          a unique identifier for order.
     * @param date        a date of order.
     * @param order_price display general price of order of user.
     */
    public Orders(Integer id, Timestamp date, BigDecimal order_price) {
        this.id = id;
        this.date = date;
        this.order_price = order_price;
    }


    /**
     * Getters and setters methods by all fields of Orders.
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public BigDecimal getOrder_price() {
        return order_price;
    }

    public void setOrder_price(BigDecimal order_price) {
        this.order_price = order_price;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this user is the same as the object
     * argument, otherwise returns false.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Orders)) return false;

        Orders orders = (Orders) object;

        if (getId() != orders.getId()) return false;
        if (!getDate().equals(orders.getDate())) return false;
        if (!getOrder_price().equals(orders.getOrder_price())) return false;
        if (!getUser().equals(orders.getUser())) return false;
        return getPizzas().equals(orders.getPizzas());
    }

    /**
     * Check a hash code value for the order.
     *
     * @return A hash code value for this order.
     */
    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getOrder_price().hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getPizzas().hashCode();
        return result;
    }

    /**
     * Returns a string representation of the order.
     */
    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", order_price=" + order_price +
                '}';
    }
}
