package InventoryManagemant.demo.DTOs;

import InventoryManagemant.demo.Entity.Product;
import lombok.Data;

@Data
public class ProductResponse {
        private String productName;
        private String productDescription;
        private Long quantity;
        private Long threShold;

        public ProductResponse(Product product) {
            this.productName = product.getProductName();
            this.productDescription = product.getProductDescription();
            this.quantity=product.getStockQuantity();
            this.threShold=product.getThreShold();
        }
}
