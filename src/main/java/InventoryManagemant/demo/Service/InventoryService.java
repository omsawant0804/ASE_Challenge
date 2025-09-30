package InventoryManagemant.demo.Service;

import InventoryManagemant.demo.DTOs.InventoryRequest;
import InventoryManagemant.demo.DTOs.ProductResponse;
import InventoryManagemant.demo.Entity.Product;
import InventoryManagemant.demo.Repository.ProductRepository;
import InventoryManagemant.demo.Utility.Response;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class InventoryService {
    private final ProductRepository productRepository;

    public Response<ProductResponse> increaseStock(String id,InventoryRequest inventoryRequestDTO) {
        try{
            if(inventoryRequestDTO.getQuantity()<=0){
                return new Response<>(false,"Check the required fileds are valid",null);
            }
            Product product=productRepository.findById(id).orElse(null);
            if(product==null){
                return new Response<>(false,"Product Not Found",null);
            }
            product.setStockQuantity(product.getStockQuantity()+inventoryRequestDTO.getQuantity());
            Product savedProduct=productRepository.save(product);
            return new Response<>(true,"Product Stock Increased Successfully", List.of(new ProductResponse(savedProduct)));
        } catch (Exception e) {
            return new Response<>(false,"Something Went Wrong while updating",null);
        }
    }

    public Response<ProductResponse> decreaseStock(String id,InventoryRequest inventoryRequestDTO) {
        try{
            if(inventoryRequestDTO.getQuantity()<=0){
                return new Response<>(false,"Check the required fileds are valid",null);
            }
            Product product=productRepository.findById(id).orElse(null);
            if(product==null){
                return new Response<>(false,"Product Not Found",null);
            }
            Long stockQuantity=product.getStockQuantity()-inventoryRequestDTO.getQuantity();
            if(stockQuantity<0){
                return new Response<>(false,"Product Stock is Less as compared to input given",null);
            }
            product.setStockQuantity(stockQuantity);
            Product savedProduct=productRepository.save(product);
            return new Response<>(true,"Product Stock Decreased Successfully", List.of(new ProductResponse(savedProduct)));
        } catch (Exception e) {
            return new Response<>(false,"Something Went Wrong while updating",null);
        }
    }


    public Response<ProductResponse> getBelowThreShouldProducts(){
        try{
            List<Product> products=productRepository.getProductsBelowThreShold();
            if(products==null || products.isEmpty()){
                return new Response<>(true,"No Products Below ThreShold",null);
            }
            return new Response<>(true,"Product fetched below threshold",products.stream().map(ProductResponse::new).toList());
        } catch (Exception e) {
            return new Response<>(false,"Something Went Wrong while fetching",null);
        }
    }


}
