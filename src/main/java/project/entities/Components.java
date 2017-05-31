package project.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * The class implements a set of standard methods for working
 * with entity of the Components.
 *
 * @author Aleksey
 */
@Entity
@Table(name = "components")
public class Components {

    /**
     * The unique identifier for each component.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The name of this component.
     */
    @Column(name = "COMPONENT_NAME")
    private String componentName;

    /**
     * The weight of this component.
     */
    @Column(name = "WEIGHT")
    private BigDecimal weight;

    /**
     * The weight of this component for sale.
     */
    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Pizza> pizzas;



    /**
     * The default constructor of this component for sale.
     */
    public Components() {
    }

    /**
     * Constructor
     *
     * @param id            a unique identifier for components.
     * @param componentName a name of component.
     * @param weight        of component.
     * @param price         sale prise of components.
     */
    public Components(int id, String componentName, BigDecimal weight, BigDecimal price) {
        this.id = id;
        this.componentName = componentName;
        this.weight = weight;
        this.price = price;
    }

    /**
     * Constructor
     *
     * @param id            a unique identifier for components.
     * @param componentName a name of component.
     */
    public Components(int id, String componentName) {
        this.id = id;
        this.componentName = componentName;
    }




    /**
     * Getters and setters methods by all fields of Components.
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return Returns true if this component is the same as the object
     * argument, otherwise returns false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Components that = (Components) o;
        return id == that.id &&
                Objects.equals(componentName, that.componentName) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(price, that.price) &&
                Objects.equals(pizzas, that.pizzas);
    }

    /**
     * Check a hash code value for the component.
     *
     * @return A hash code value for this component.
     */

    @Override
    public int hashCode() {
        return Objects.hash(id, componentName, weight, price, pizzas);
    }

    /**
     * Returns a string representation of the component.
     */
    @Override
    public String toString() {
        return "Components{" +
                "id=" + id +
                ", componentName='" + componentName + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }
}
