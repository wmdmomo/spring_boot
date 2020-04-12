package com.example.demo.Dao;

import com.example.demo.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestDao extends CrudRepository<Restaurant,Integer> {
}