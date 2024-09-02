package dev.ayush.productservice.DTOS;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenaralProductDTO {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
