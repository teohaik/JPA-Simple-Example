package gr.eap.oss4.app;

import gr.eap.oss4.model.Order;
import gr.eap.oss4.model.ProductItem;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Theodore Chaikalis
 */
public class Main {
    
    
    public static void main(String args[]){
         EntityManagerFactory factory = Persistence.createEntityManagerFactory("ossPU");
        EntityManager em = factory.createEntityManager();
      
    }
    
   

}
