/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejpa;

import entities.Order;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alvaro.ruiz
 */
public class SimpleJPADemo {

    EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("SimpleJPAPU");
    EntityManager em = emf.createEntityManager();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("Hendro Steven");
        p.setAddress("Salatiga, Indonesia");
        p.setPhoneNumber("+6281390989669");
        Order order1 = new Order("Description1");
        Order order2 = new Order("Description2");
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        p.setOrders(orders);
        
        SimpleJPADemo demo = new SimpleJPADemo();
        demo.persist(p);
        demo.consulPersona(p.getId());
        
    }

    public void persist(Person person) {
        em.getTransaction().begin();
        try {
            em.persist(person);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    private void consulPersona(Long id) {
        em = emf.createEntityManager();
        Person persona = em.find(Person.class, id);
        System.out.println("Persona =" +persona);
        persona.getOrders().stream().forEach(or -> {
            System.out.println("Order " + or);
        });
        em.close();
    }
}
