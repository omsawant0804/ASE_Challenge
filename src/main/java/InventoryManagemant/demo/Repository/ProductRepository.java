package InventoryManagemant.demo.Repository;

import InventoryManagemant.demo.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    //This is repository connection between Services layer and Database

    @Query("Select p From Product p where p.stockQuantity<p.threShold")
    List<Product> getProductsBelowThreShold();
}
