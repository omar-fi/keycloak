package net.orderservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    private String id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;

    public Order() {
    }

    public Order(String id, LocalDate date, OrderState state, List<ProductItem> productItems) {
        this.id = id;
        this.date = date;
        this.state = state;
        this.productItems = productItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private String id;
        private LocalDate date;
        private OrderState state;
        private List<ProductItem> productItems;

        OrderBuilder() {
        }

        public OrderBuilder id(String id) {
            this.id = id;
            return this;
        }

        public OrderBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public OrderBuilder state(OrderState state) {
            this.state = state;
            return this;
        }

        public OrderBuilder productItems(List<ProductItem> productItems) {
            this.productItems = productItems;
            return this;
        }

        public Order build() {
            return new Order(id, date, state, productItems);
        }

        public String toString() {
            return "Order.OrderBuilder(id=" + this.id + ", date=" + this.date + ", state=" + this.state
                    + ", productItems=" + this.productItems + ")";
        }
    }
}
