package project.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
