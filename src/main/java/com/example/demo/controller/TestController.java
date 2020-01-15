package com.example.demo.controller;

import com.example.demo.Dao.UsrDao;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "test")
@CrossOrigin
public class TestController {
    @Autowired
    private UsrDao usrDao;
    @GetMapping(value = "studentMan")

    public Iterable<Student> getStudent(){
        return usrDao.findAll();
    }
    @PostMapping(value = "addStu")
    public String saveUsr(@RequestParam String name,@RequestParam String tel,@RequestParam String gender){
        Student stu=new Student();
        stu.setName(name);
        stu.setGender(gender);
        stu.setTel(tel);
        usrDao.save(stu);
        return "suc";
    }
}
