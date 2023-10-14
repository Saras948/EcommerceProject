package com.productcategoryproxy.productcategoryproxy.models;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseModel {
    private Long Id;
    private Date CreatedAt;
    private Date updatedAt;
    private boolean isDeleted;
}
