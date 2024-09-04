package dev.ayush.productservice.service;

        import dev.ayush.productservice.DTOS.*;
        import dev.ayush.productservice.models.*;
        import org.springframework.boot.web.client.RestTemplateBuilder;
        import org.springframework.http.HttpEntity;
        import org.springframework.http.HttpMethod;
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
    private String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String getProductsBaseRequestUrl = "https://fakestoreapi.com/products";
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

        ResponseEntity<FakeStoreProductDTO> response = restTemplate.execute(
                specificProductRequestUrl,
                HttpMethod.GET,
                restTemplate.httpEntityCallback(null, FakeStoreProductDTO.class),
                restTemplate.responseEntityExtractor(FakeStoreProductDTO.class),
                id
        );
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        System.out.println("hello your error");
        System.out.println(fakeStoreProductDTO);
        return convertFakeProductIntoGeneralProduct(fakeStoreProductDTO);
    }

    public GenaralProductDTO createProduct(GenaralProductDTO product){
        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<GenaralProductDTO> response = restTemplate.exchange(
//                getProductsBaseRequestUrl,
//                HttpMethod.POST,
//                new HttpEntity<>(product),
//                GenaralProductDTO.class
//        );
        ResponseEntity<GenaralProductDTO> response = restTemplate.execute(
                getProductsBaseRequestUrl,
                HttpMethod.POST,
                restTemplate.httpEntityCallback(product, GenaralProductDTO.class),
                restTemplate.responseEntityExtractor(GenaralProductDTO.class)
        );

        return  response.getBody();
    }

    public ArrayList<GenaralProductDTO> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();

//        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.exchange(
//                getProductsBaseRequestUrl,
//                HttpMethod.GET,
//                null,
//                FakeStoreProductDTO[].class
//        );
        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.execute(
                getProductsBaseRequestUrl,
                HttpMethod.GET,
                restTemplate.httpEntityCallback(null, FakeStoreProductDTO[].class),
                restTemplate.responseEntityExtractor(FakeStoreProductDTO[].class)
        );


        FakeStoreProductDTO[] products;
        products = response.getBody();
        ArrayList<GenaralProductDTO> answer = new  ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO : products){

            answer.add(convertFakeProductIntoGeneralProduct(fakeStoreProductDTO));
        }
        return  answer;
    }
    public  GenaralProductDTO deleteProduct(long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.execute(
                specificProductRequestUrl,
                HttpMethod.DELETE,
                restTemplate.acceptHeaderRequestCallback( FakeStoreProductDTO.class),
                restTemplate.responseEntityExtractor(FakeStoreProductDTO.class),
                id
        );
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        System.out.println(fakeStoreProductDTO);
        return convertFakeProductIntoGeneralProduct(fakeStoreProductDTO);
    }
}


