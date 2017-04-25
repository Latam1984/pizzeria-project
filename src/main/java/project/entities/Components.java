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
     * @param id a unique identifier fo
     */
    public Components(int id, String componentName, int weight, float price) {
        this.id = id;
        this.componentName = componentName;
        this.weight = weight;
        this.price = price;
    }

    public void setId(long id) {

    }
}
