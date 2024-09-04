package dev.ayush.productservice.Controllers;

import dev.ayush.productservice.DTOS.GenaralProductDTO;
import dev.ayush.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("{id}")
    public GenaralProductDTO getProductById(@PathVariable("id") long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public GenaralProductDTO createProduct(@RequestBody GenaralProductDTO product){
        return productService.createProduct(product);
    }

    @GetMapping
    public ArrayList<GenaralProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }
    @DeleteMapping("{id}")
    public GenaralProductDTO deleteProduct(@PathVariable("id") long id){
        return productService.deleteProduct(id);
    }
}
