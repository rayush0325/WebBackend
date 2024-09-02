package dev.ayush.productservice.service;

        import dev.ayush.productservice.DTOS.*;
        import dev.ayush.productservice.models.*;
        import org.springframework.boot.web.client.RestTemplateBuilder;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Service;
        import org.springframework.web.client.RestTemplate;
        import java.util.*;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    private String requestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String getAllProductsRequestUrl = "https://fakestoreapi.com/products";
    private GenaralProductDTO convertFakeProductIntoGeneralProduct(FakeStoreProductDTO fakeStoreProductDTO){
        GenaralProductDTO product = new GenaralProductDTO();

        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setCategory(fakeStoreProductDTO.getCategory());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImage(fakeStoreProductDTO.getImage());

        return product;
    }
    @Override
    public GenaralProductDTO getProductById(long id){

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO> response =  restTemplate.getForEntity(requestUrl, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();

        return convertFakeProductIntoGeneralProduct(fakeStoreProductDTO);
    }

    public GenaralProductDTO createProduct(GenaralProductDTO product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenaralProductDTO> response = restTemplate.postForEntity(createProductRequestUrl, product, GenaralProductDTO.class);

        return  response.getBody();
    }

    public ArrayList<GenaralProductDTO> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity(
                getAllProductsRequestUrl, FakeStoreProductDTO[].class
        );
        FakeStoreProductDTO[] products;
        products = response.getBody();
        System.out.println(products.getClass().getName());
        ArrayList<GenaralProductDTO> answer = new  ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO : products){

            answer.add(convertFakeProductIntoGeneralProduct(fakeStoreProductDTO));
        }
        return  answer;
    }
}


