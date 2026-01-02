package net.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import net.orderservice.model.Product;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private double price;
    private int quantity;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;
    @Transient
    private Product product;

    public ProductItem() {
    }

    public ProductItem(Long id, String productId, double price, int quantity, Order order, Product product) {
        this.id = id;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static ProductItemBuilder builder() {
        return new ProductItemBuilder();
    }

    public static class ProductItemBuilder {
        private Long id;
        private String productId;
        private double price;
        private int quantity;
        private Order order;
        private Product product;

        ProductItemBuilder() {
        }

        public ProductItemBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductItemBuilder productId(String productId) {
            this.productId = productId;
            return this;
        }

        public ProductItemBuilder price(double price) {
            this.price = price;
            return this;
        }

        public ProductItemBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductItemBuilder order(Order order) {
            this.order = order;
            return this;
        }

        public ProductItemBuilder product(Product product) {
            this.product = product;
            return this;
        }

        public ProductItem build() {
            return new ProductItem(id, productId, price, quantity, order, product);
        }
    }
}
