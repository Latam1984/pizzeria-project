package project.entities;

/**
 * The class implements a set of standard methods for working
 * with entity of the Components.
 *
 * @author Aleksey
 */
public class Components implements Model {

    /**
     * The unique identifier for each component.
     */
    private int id;

    /**
     * The name of this component.
     */
    private String componentName;

    /**
     * The weight of this component.
     */
    private int weight;

    /**
     * The weight of this component for sale.
     */
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
        result = 31 * result + weight;
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }

    public void setId(long id) {

    }
}
