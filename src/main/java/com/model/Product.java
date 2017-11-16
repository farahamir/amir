package com.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.json.JSONObject;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private Double price;

    @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(){
    }

    public Product(String name, Category category, Double price){
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // products
    public void setCategory(Category category){
        this.category = category;
    }

    public Category getCategory(){
        return this.category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString(){
        String info = "";

        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("name",this.name);

        JSONObject categoryObj = new JSONObject();
        categoryObj.put("name", this.category.getName());
        jsonInfo.put("category", categoryObj);

        info = jsonInfo.toString();
        return info;
    }


}
