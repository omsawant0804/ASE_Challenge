package InventoryManagemant.demo.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="tbl_products")
public class Product {

    // Here this below are the columns of our table.

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name = "id", unique = true, nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
        private String id;

        @Column(name = "product_name",nullable = false, columnDefinition = "VARCHAR(255)")
        private String productName;

        @Column(name = "product_dec", nullable = false, columnDefinition = "TEXT")
        private String productDescription;

        @Column(name="stock_quantity", nullable = false, columnDefinition = "INT")
        private Long stockQuantity;

        @Column(name="threshold", nullable = false, columnDefinition = "INT")
        private Long threShold;


}
