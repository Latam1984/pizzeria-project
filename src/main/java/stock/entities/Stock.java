package stock.entities;

import javax.persistence.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the Stock/Components.
 *
 * @author Aleksey
 */

@Entity
@Table(name = "stock")
public class Stock {
    /*
    The unique identifier for each component.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /*
    The name of component.
     */
    @Column(name = "COMPONENT_NAME")
    private String componentName;

    /*
    The weight of each component.
     */
    @Column(name = "WEIGHT")
    private float weight;

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

    public void setWeight(float weight) {
        this.weight = weight;
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
        if (!(object instanceof Stock)) return false;

        Stock stock = (Stock) object;

        if (id != stock.id) return false;
        if (Float.compare(stock.weight, weight) != 0) return false;
        return componentName.equals(stock.componentName);
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
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        return result;
    }

    /**
     * Returns a string representation of the component.
     */
    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", componentName='" + componentName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
