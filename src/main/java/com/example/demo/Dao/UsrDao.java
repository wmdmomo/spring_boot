package com.example.demo.Dao;
import com.example.demo.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface UsrDao extends CrudRepository<Student,Integer> {
}
