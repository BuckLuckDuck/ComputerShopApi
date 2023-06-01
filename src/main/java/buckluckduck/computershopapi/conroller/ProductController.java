package buckluckduck.computershopapi.conroller;

import buckluckduck.computershopapi.model.Product;
import buckluckduck.computershopapi.service.ProductService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (!product.getId().equals(id))
            throw new IllegalArgumentException("Product ID in the path and in the request body must be the same");

        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + id + " not found"));
        return ResponseEntity.ok(product);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Product>> getProductsByType(@PathVariable String type) {
        List<Product> products = productService.getProductsByType(type);
        return ResponseEntity.ok(products);
    }

    // For test
    @PostConstruct
    private void createSomeProducts() {
        // Create desktop computers
        productService.addProduct(new Product("desktop-serial-001", "Desktop Inc.", 1500.99, 20, "desktop", "monoblock"));
        productService.addProduct(new Product("desktop-serial-002", "Desktop Inc.", 1600.99, 15, "desktop", "desktop"));
        productService.addProduct(new Product("desktop-serial-003", "Desktop Co.", 1400.99, 10, "desktop", "nettop"));

        // Create laptops
        productService.addProduct(new Product("laptop-serial-001", "Laptop Co.", 1299.99, 15, "laptop", "13"));
        productService.addProduct(new Product("laptop-serial-002", "Laptop Corp.", 1399.99, 12, "laptop", "14"));
        productService.addProduct(new Product("laptop-serial-003", "Laptop Ltd.", 1499.99, 10, "laptop", "15"));
        productService.addProduct(new Product("laptop-serial-004", "Laptop Inc.", 1599.99, 8, "laptop", "17"));

        // Create monitors
        productService.addProduct(new Product("monitor-serial-001", "Monitor Corp.", 399.99, 30, "monitor", "27"));
        productService.addProduct(new Product("monitor-serial-002", "Monitor Co.", 499.99, 20, "monitor", "32"));
        productService.addProduct(new Product("monitor-serial-003", "Monitor Ltd.", 599.99, 10, "monitor", "40"));

        // Create hard drives
        productService.addProduct(new Product("harddrive-serial-001", "HardDrive Ltd.", 79.99, 50, "hardDrive", "1000"));
        productService.addProduct(new Product("harddrive-serial-002", "HardDrive Co.", 89.99, 40, "hardDrive", "2000"));
        productService.addProduct(new Product("harddrive-serial-003", "HardDrive Corp.", 99.99, 30, "hardDrive", "3000"));
        productService.addProduct(new Product("harddrive-serial-004", "HardDrive Inc.", 109.99, 20, "hardDrive", "4000"));
        productService.addProduct(new Product("harddrive-serial-005", "HardDrive SA.", 119.99, 10, "hardDrive", "5000"));
    }

}

