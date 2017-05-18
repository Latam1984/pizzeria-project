package stock.entities;

import jdk.nashorn.internal.ir.annotations.Reference;

import javax.persistence.*;
import java.util.Objects;

/**
 * The class implements a set of standard methods for working
 * with entity of the StockHistory.
 *
 * @author Aleksey
 */

@Entity
@Table(name = "stock_history")
public class StockHistory {

   /**
    * The unique identifier for each entry about delivery components on stock.
    */
   @Id
   @Column(name = "ID")
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;

   /*
    The unique identifier for each component.
     */
   @Id
   @Column(name = "ID_COMPONENTS")
   private int id_components;

   /*
   Date of delivery component on the stock.
    */
   @Column(name = "DATE")
   private String date;

   /*
    The weight of each component.
     */
   @Column(name = "WEIGHT")
   private float weight;

   /*
    The price of each component.
     */
   @Column(name = "PRICE")
   private float price;


   /**
    * Getters and setters methods by all fields of Components.
    */
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getId_components() {
      return id_components;
   }

   public void setId_components(int id_components) {
      this.id_components = id_components;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public float getWeight() {
      return weight;
   }

   public void setWeight(float weight) {
      this.weight = weight;
   }

   public float getPrice() {
      return price;
   }

   public void setPrice(float price) {
      this.price = price;
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
      if (!(object instanceof StockHistory)) return false;

      StockHistory that = (StockHistory) object;

      if (getId() != that.getId()) return false;
      if (getId_components() != that.getId_components()) return false;
      if (Float.compare(that.getWeight(), getWeight()) != 0) return false;
      if (Float.compare(that.getPrice(), getPrice()) != 0) return false;
      return getDate().equals(that.getDate());
   }

   /**
    * Check a hash code value for the component.
    *
    * @return A hash code value for this component.
    */
   @Override
   public int hashCode() {
      int result = getId();
      result = 31 * result + getId_components();
      result = 31 * result + getDate().hashCode();
      result = 31 * result + (getWeight() != +0.0f ? Float.floatToIntBits(getWeight()) : 0);
      result = 31 * result + (getPrice() != +0.0f ? Float.floatToIntBits(getPrice()) : 0);
      return result;
   }

   /**
    * Returns a string representation of the component.
    */
   @Override
   public String toString() {
      return "StockHistory{" +
              "id=" + id +
              ", id_components=" + id_components +
              ", date='" + date + '\'' +
              ", weight=" + weight +
              ", price=" + price +
              '}';
   }
}
