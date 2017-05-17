package project.entities;

import javax.persistence.*;

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
    private float weight;

    /**
     * The weight of this component for sale.
     */
    @Column(name = "PRICE")
    private float price;

    /**
     * The default constructor of this component for sale.
     */
    public Components() {
    }

    /**
     * Constructor
     *
     * @param id a unique identifier for components.
     * @param componentName a name of component.
     * @param weight of component.
     * @param price sale prise of components.
     *
     */
    public Components(int id, String componentName, int weight, float price) {
        this.id = id;
        this.componentName = componentName;
        this.weight = weight;
        this.price = price;
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


    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this component is the same as the object
     * argument, otherwise returns false.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Components)) return false;

        Components that = (Components) object;

        if (id != that.id) return false;
        if (weight != that.weight) return false;
        if (Float.compare(that.price, price) != 0) return false;
        return componentName.equals(that.componentName);
    }


    /**
     * Check a hash code value for the component.
     *
     * @return A hash code value for this component.
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + componentName.hashCode();
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
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

    public float getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
