package InventoryManagemant.demo.DTOs;

import lombok.Data;

@Data
public class ProductRequest {

    private String productName;
    private String productDescription;
    private Long quantity;
    private Long threShold;

}
