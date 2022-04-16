package ch.bbw.webshop.entity;

import com.sun.istack.Nullable;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * @author Lewin Gerber
 * @version 21.11.2020
 * webshop
 */

@Entity(name = "Product")
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    //name of the product
    private String name;
    //price of the product
    private double price;
    //description of the product
    private String description;
    //amount of products in stock
    private int stock;
    //quantity for products in cart => in order to simplify process
    private int quantity;
    //image of the product
    private String image;

    public Product(){}

    public Product(String name, double price, String description, int stock, String image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.image = image;
    }

    //GETTER SETTER

    public int getId() {
        return id;
    }

    //no setter for id needed since it's value is determined by the DB

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
