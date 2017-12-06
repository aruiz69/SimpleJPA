/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author alvaro.ruiz
 */
@Entity(name="Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PERSONA_ID", nullable = false)
    private Long personaId;
    
    private String name;
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy="person", targetEntity=Order.class,cascade = CascadeType.PERSIST,  fetch=FetchType.EAGER)
    private Collection<Order> orders;
    
    public Long getId() {
        return personaId;
    }

    public void setId(Long id) {
        this.personaId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaId != null ? personaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.personaId == null && other.personaId != null) || (this.personaId != null && !this.personaId.equals(other.personaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Person{" + "personaId=" + personaId + ", name=" + name + ", phoneNumber=" + phoneNumber + ", address=" + address + " '}'";
    }

    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getAddress() {
        return this.address;
    }

    /**
     * @return the orders
     */
    public Collection<Order> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(Collection<Order> orders) {
        for(Order order : orders){
            order.setPerson(this);
        }
        this.orders = orders;
    }
 
}
