package gr.eap.oss4.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 *
 * @author Theodore Chaikalis
 */

@Entity
@Table(name = "TEOHAIK_ORDER")
public class Order implements Serializable{

    @Id
    private Long id;
    
    @Column(name="ORDER_DETAILS")
    private String details;    
    
    @Column(name="DATE_CREATED")
    private Date dateCreated;
    
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ProductItem> items;
    
    @Column(name="TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    
    public Order(){
        dateCreated = new Date();
        items = new ArrayList<>();
    }
    
    public void addOrderItem(ProductItem item){
        items.add(item);
        item.setOrder(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDateCreated() {
        return dateCreated = new Date();
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<ProductItem> getItems() {
        return items;
    }

    public void setItems(List<ProductItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public BigDecimal calculateOrderTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for(ProductItem item : getItems()){
            total = total.add(item.getPrice());
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", "
                + "details=" + details + ", "
                + "dateCreated = " + dateCreated + ", "
                + "totalAmount = " + totalAmount + '}';
    }
        
}
