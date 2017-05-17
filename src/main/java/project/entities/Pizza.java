package project.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entity of pizza
 *
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
    private float pizzaPrice;

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

    public float getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(float pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return id == pizza.id &&
                Float.compare(pizza.pizzaPrice, pizzaPrice) == 0 &&
                Objects.equals(pizzaName, pizza.pizzaName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pizzaName, pizzaPrice);
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
