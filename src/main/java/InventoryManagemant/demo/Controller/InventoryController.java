package InventoryManagemant.demo.Controller;

import InventoryManagemant.demo.DTOs.InventoryRequest;
import InventoryManagemant.demo.Service.InventoryService;
import InventoryManagemant.demo.Utility.Response;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/increase-stock/{id}")
    public ResponseEntity<Response> increaseStock(@PathVariable String id, @RequestBody InventoryRequest inventoryRequest) {
        try{
            Response res=inventoryService.increaseStock(id,inventoryRequest);
            if(res.isSuccess()){
                return ResponseEntity.ok().body(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(false,e.toString(),null));

        }
    }

    @PostMapping("/decrease-stock/{id}")
    public ResponseEntity<Response> decreaseStack(@PathVariable String id,@RequestBody InventoryRequest inventoryRequest) {
        try{
            Response res=inventoryService.decreaseStock(id,inventoryRequest);
            if(res.isSuccess()){
                return ResponseEntity.ok().body(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(false,e.toString(),null));
        }
    }

    @GetMapping("/below-threshold")
    public ResponseEntity<Response> belowThreshold() {
        try{
            Response res=inventoryService.getBelowThreShouldProducts();
            if(res.isSuccess()){
                return ResponseEntity.ok().body(res);
            }
            return ResponseEntity.badRequest().body(res);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(false,e.toString(),null));
        }
    }
}
