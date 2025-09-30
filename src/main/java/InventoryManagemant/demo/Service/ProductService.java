package InventoryManagemant.demo.Service;

import InventoryManagemant.demo.DTOs.ProductRequest;
import InventoryManagemant.demo.DTOs.ProductResponse;
import InventoryManagemant.demo.Entity.Product;
import InventoryManagemant.demo.Repository.ProductRepository;
import InventoryManagemant.demo.Utility.Response;
import io.micrometer.common.util.StringUtils;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.List;

@Data
@Service
public class ProductService {

    // Here it will do Dependency Injection using Constructor and we are not writing constructor bcoz we are using lombok @Data which will insert constructor Auto which make our code clean
    private final ProductRepository productRepository;

    //Add new Product
    public Response<ProductResponse> addProduct(ProductRequest ProductRequest) {
        try {
            System.out.println(ProductRequest);
            if (StringUtils.isBlank(ProductRequest
.getProductName())
                    || StringUtils.isBlank(ProductRequest
.getProductDescription())
                    || ProductRequest
.getQuantity() == null || ProductRequest
.getQuantity() < 0
                    || ProductRequest
.getThreShold() == null || ProductRequest
.getThreShold() < 0) {

                return new Response<>(false, "Check given fields are valid!", null);
            }

            Product product = new Product();
            product.setProductName(ProductRequest.getProductName());
            product.setProductDescription(ProductRequest.getProductDescription());
            product.setStockQuantity(ProductRequest.getQuantity());
            product.setThreShold(ProductRequest.getThreShold());

            // Here we save the product...
            Product savedProduct = productRepository.save(product);

            return new Response<>(true, "Product Saved Successfully", List.of(new ProductResponse(savedProduct)));

        } catch (Exception e) {
            return new Response<>(false, "Product Failed to save !", null);
        }

    }

    //Update Product
    public Response<ProductResponse> updateProduct(String id, ProductRequest updateProductRequestDTO) {
        try {
            if (id == null || id.isBlank()) {
                return new Response<>(false, "Id is required", null);
            }

            Product product = productRepository.findById(id).orElse(null);

            if (product == null) {
                return new Response<>(false, "Product does not exist!", null);
            }

            boolean updated = false;
            //Name Update...
            if (updateProductRequestDTO.getProductName() != null && !updateProductRequestDTO.getProductName().isBlank()) {
                product.setProductName(updateProductRequestDTO.getProductName().trim());
                updated = true;
            }
            //Desc Update...
            if (updateProductRequestDTO
                    .getProductDescription() != null && !updateProductRequestDTO
                    .getProductDescription().isBlank()) {
                product.setProductDescription(updateProductRequestDTO
                        .getProductDescription().trim());
                updated = true;
            }

            //Quantity Update
            if (updateProductRequestDTO
                    .getQuantity() != null) {
                if (updateProductRequestDTO
                        .getQuantity() < 0) {
                    return new Response<>(false, "Quantity cannot be negative", null);
                }
                product.setStockQuantity(updateProductRequestDTO
                        .getQuantity());
                updated = true;
            }
            //ThreadShold Update
            if(updateProductRequestDTO.getThreShold() != null) {
                if (updateProductRequestDTO.getThreShold() < 0) {
                    return new Response<>(false, "ThreadShold cannot be negative", null);
                }
                product.setThreShold(updateProductRequestDTO.getThreShold());
                updated = true;
            }

            if (!updated) {
                return new Response<>(false, "No valid fields provided to update", null);
            }
            //saving here
            Product savedProduct=productRepository.save(product);
            return new Response<>(true, "Product updated successfully", List.of(new ProductResponse(savedProduct)));

        } catch (Exception e) {
            return new Response<>(false, "Product failed to update!", null);
        }
    }


    public Response<Product> getAllProducts(){
        try{
            List<Product> products = productRepository.findAll();
            if(products.isEmpty()){
                return new Response<>(true, "No products available!", products);
            }

            return new Response<>(true,"Products Fetched", products);

        } catch (Exception e) {
            return new Response<>(false, "Something went wrong!", null);
        }
    }

    // Get Particular Product
    public Response<ProductResponse> getParticularProduct(String id){
        try{
            if (id == null || id.isBlank()) {
                return new Response<>(false, "Id is required", null);
            }

            Product product = productRepository.findById(id).orElse(null);
            if (product == null) {
                return new Response<>(false, "Product does not exist!", null);
            }
            return new Response<>(true,"Product Fetched", List.of(new ProductResponse(product)));

        } catch (Exception e) {
            return new Response<>(false, "Something went wrong!", null);
        }
    }

    //Delete Product
    public Response<ProductResponse> deleteProduct(String id){
        try{
            if (id == null || id.isBlank()) {
                return new Response<>(false, "Id is required", null);
            }
            Product product = productRepository.findById(id).orElse(null);
            if (product == null) {
                return new Response<>(false, "Product does not exist!", null);
            }
            productRepository.delete(product);
            return new Response<>(true, "Product Deleted", List.of(new ProductResponse(product)));
        } catch (Exception e) {
            return new Response<>(false, "Something went wrong!", null);
        }
    }


}

