package com.inspire12.practice.api.domain.product;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    public List<String> images;
    public String thumbnail;
    public String category;
    public String brand;
    public int stock;
    public double rating;
    public double discountPercentage;
    public int price;
    public String description;
    public String title;
    public int id;
}
