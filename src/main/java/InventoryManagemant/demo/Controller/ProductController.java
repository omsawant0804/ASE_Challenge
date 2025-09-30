package InventoryManagemant.demo.Controller;

import InventoryManagemant.demo.DTOs.ProductRequest;
import InventoryManagemant.demo.DTOs.ProductResponse;
import InventoryManagemant.demo.Service.ProductService;
import InventoryManagemant.demo.Utility.Response;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
public class ProductController {
    private final ProductService productService;

    // Add new Product Endpoint
    @PostMapping("/add-product")
    public ResponseEntity<Response<ProductResponse>> addProduct(@RequestBody ProductRequest ProductRequest) {
        try {
            Response<ProductResponse> res = productService.addProduct(ProductRequest);
            if (res.isSuccess()) {
                return ResponseEntity.ok(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(false,e.toString(),null));
        }
    }

    //Update Product endpoint
    @PatchMapping("/update-product/{id}")
    public ResponseEntity<Response> updateProduct(@PathVariable String id, @RequestBody ProductRequest updateProductRequestDTO) {
        try{
            Response res=productService.updateProduct(id,updateProductRequestDTO);
            if (res.isSuccess()) {
                return ResponseEntity.ok(res);
            }
            return ResponseEntity.badRequest().body(res);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(false,e.toString(),null));
        }
    }

    //Get All Products endpoint
    @GetMapping("/get-all-products")
    public ResponseEntity<Response> getAllProducts() {
        try{
            Response res=productService.getAllProducts();
            if (res.isSuccess()) {
                return ResponseEntity.ok(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(false,e.toString(),null));
        }
    }

    //Get Particular Product endpoint
    @GetMapping("/get-product/{id}")
    public ResponseEntity<Response> getProduct(@PathVariable String id) {
        try{
            Response res=productService.getParticularProduct(id);
            if (res.isSuccess()) {
                return ResponseEntity.ok(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(false,e.toString(),null));
        }
    }

    // Delete particular Product endpoint
    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable String id) {
        try{
            Response res=productService.deleteProduct(id);
            if (res.isSuccess()) {
                return ResponseEntity.ok(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(false,e.toString(),null));
        }
    }


}
