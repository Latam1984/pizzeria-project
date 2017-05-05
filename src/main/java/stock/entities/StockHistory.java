package stock.entities;

import jdk.nashorn.internal.ir.annotations.Reference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Aleksey on 05.05.2017.
 */

@Entity
@Table(name = "stock_history")
public class StockHistory {
   @Id
   @Column(name = "ID")
   private int id;
   @Id
   @Column(name = "ID_COMPONENTS")
   private int id_components;
   @Column(name = "DATE")
   private String date;
   @Column(name = "WEIGHT")
   private float weight;
   @Column(name = "PRICE")
   private float price;

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
}
