package project.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity of pizza
 * <p>
 * Created by Andrey on 25.04.2017.
 */
@Entity
@Table(name = "pizza")
public class Pizza {
    /**
     * The unique identifier for each pizza.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /**
     * The name of pizza
     */
    @Column(name = "PIZZA_NAME")
    private String pizzaName;
    /**
     * The price of pizza
     */
    @Column(name = "PIZZA_PRICE")
    private BigDecimal pizzaPrice;

    /*
    *List <Ordes> Array list of orders
    */
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Orders> orders;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "COMPLICATE_PIZZA",
            joinColumns = @JoinColumn(name = "ID_COMPONENTS", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_PIZZA", referencedColumnName = "ID"))
    private List<Components> components;

    public Pizza(int id,String pizzaName, BigDecimal pizzaPrice) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.pizzaPrice = pizzaPrice;
    }

    public Pizza(int id, String pizzaName) {
        this.id = id;
        this.pizzaName = pizzaName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public BigDecimal getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(BigDecimal pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return id == pizza.id &&
                Objects.equals(pizzaName, pizza.pizzaName) &&
                Objects.equals(pizzaPrice, pizza.pizzaPrice) &&
                Objects.equals(orders, pizza.orders) &&
                Objects.equals(components, pizza.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pizzaName, pizzaPrice, orders, components);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", pizzaName='" + pizzaName + '\'' +
                ", pizzaPrice=" + pizzaPrice +
                '}';
    }
}
