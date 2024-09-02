package dev.ayush.productservice.service;

import dev.ayush.productservice.DTOS.GenaralProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface ProductService {
    public GenaralProductDTO getProductById(long id);
    public GenaralProductDTO createProduct(GenaralProductDTO product);
    public ArrayList<GenaralProductDTO> getAllProducts();
}
