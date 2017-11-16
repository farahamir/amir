package com.model;

import com.fasterxml.jackson.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category{

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> products;

    public Category(){
    }

    public Category(String name){
        this.name = name;
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
    public void setProducts(Set<Product> products){
        this.products = products;
    }

    @JsonIgnore
    public Set<Product> getProducts(){
        return this.products;
    }

    public String toString(){
        String info = "";
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("name",this.name);

        JSONArray productArray = new JSONArray();
        if(this.products != null){
            this.products.forEach(product->{
                JSONObject subJson = new JSONObject();
                subJson.put("name", product.getName());
                productArray.put(subJson);
            });
        }
        jsonInfo.put("products", productArray);
        info = jsonInfo.toString();
        return info;
    }


}
