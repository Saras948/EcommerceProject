package com.productcategoryproxy.productcategoryproxy.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    private Categories category;
    private String imageUrl;

    @JsonManagedReference
    public Categories getCategory() {
        return category;
    }


}
