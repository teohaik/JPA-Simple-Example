package gr.eap.oss4.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Theodore Chaikalis
 */
@Entity
@Table(name ="TEOHAIK_ITEM")
public class ProductItem implements Serializable {

    @Id
    private Long id;
    
    @Column
    private String description;
    
    @ManyToOne
    private Order order;
    
    @Column
    private BigDecimal price;
    
    public ProductItem(){
        
    }

    public ProductItem(Long id, String description, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductItem{" + "id=" + id + ", description=" + description;
    }
    
}
