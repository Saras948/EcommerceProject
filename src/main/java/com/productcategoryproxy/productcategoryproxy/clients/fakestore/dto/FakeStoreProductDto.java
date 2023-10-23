package com.productcategoryproxy.productcategoryproxy.clients.fakestore.dto;

import com.productcategoryproxy.productcategoryproxy.clients.IClientDto;
import com.productcategoryproxy.productcategoryproxy.dtos.Ratings;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto implements IClientDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;
    private Ratings rating;
}
