package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //也要加
    private int id;
    private String name;
    private Float rating;
    private Integer order;
    @OneToMany
    private List<Act> act;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getOrder() {
        return order;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Act> getAct() {
        return act;
    }

    public void setAct(List<Act> act) {
        this.act = act;
    }
}
