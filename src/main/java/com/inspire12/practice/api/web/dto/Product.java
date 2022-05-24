package com.inspire12.practice.api.web.dto;

import java.util.List;

public abstract class Product {

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
