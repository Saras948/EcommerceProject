package com.productcategoryproxy.productcategoryproxy.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product extends BaseModel{
    private String titile;
    private String description;
    private double price;
    private Categories category;
    private String imageUrl;
}
