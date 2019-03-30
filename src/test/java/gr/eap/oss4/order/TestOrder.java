package gr.eap.oss4.order;

import gr.eap.oss4.model.Order;
import gr.eap.oss4.model.ProductItem;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thodoris
 */
public class TestOrder {

    static Order order1;
    static Order order2;
    static final String ORDER_DETAIL = "Order by Teo Haik";
    static final BigDecimal EXPECTED_TOTAL = new BigDecimal("15.0");

    @BeforeClass
    public static void setUpClass() {
        ProductItem item1 = new ProductItem(1L, "A Book", new BigDecimal("5.5"));
        ProductItem item2 = new ProductItem(2L, "Book 2", new BigDecimal("5.5"));
        ProductItem item3 = new ProductItem(3L, "Super Book3", new BigDecimal("4.0"));

        order1 = new Order();
        order1.setId(1L);
        order1.setDetails(ORDER_DETAIL);
        order1.addOrderItem(item1);
        order1.addOrderItem(item2);
        order1.addOrderItem(item3);
        
    }

    @Test
    public void testOrderDetails() {
        String details = order1.getDetails();
        assertEquals(ORDER_DETAIL, details);
    }

    @Test
    public void testOrderTotal() {
        BigDecimal actual = order1.calculateOrderTotal();
        assertEquals(EXPECTED_TOTAL, actual);
    }

    @Test
    public void testOrderId() {
        Long id = order1.getId();
        assertTrue(id.equals(1L));
    }

    @Test
    public void testSaveOrder() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ossPU");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        
        List<Order> resultList = em.createQuery("select o from Order o", Order.class).getResultList();
        
        if(resultList.isEmpty()){
            em.persist(order1);
        }
        Order saved = resultList.get(0);
        
        assertTrue(saved.getId().equals(1L));
        assertTrue(ORDER_DETAIL.equals(saved.getDetails()));
        assertEquals(3, saved.getItems().size());
        
        BigDecimal total = order1.calculateOrderTotal();
        
        order1.setTotalAmount(total);
        
        em.merge(order1); 
                
        em.getTransaction().commit();
    }

    
   
}
