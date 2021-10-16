package hellojpa;

import hellojpa.domain.Item;
import hellojpa.domain.Order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class OrderItem {
    
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private hellojpa.domain.Order order;
    
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    
    private int orderPrice;
    
    private int count;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public hellojpa.domain.Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    public Item getItem() {
        return item;
    }
    
    public void setItem(Item item) {
        this.item = item;
    }
    
    public int getOrderPrice() {
        return orderPrice;
    }
    
    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
}
