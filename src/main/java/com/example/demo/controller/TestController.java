package com.example.demo.controller;

import com.example.demo.Dao.*;
import com.example.demo.Restful.RetCode;
import com.example.demo.Restful.RetResult;
import com.example.demo.entity.*;
import com.example.demo.service.ShopService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;



import java.util.*;

@RestController
@RequestMapping(value = "test")
@CrossOrigin
public class TestController {


    private UsrDao usrDao;
    private StaDao staDao;
    private BookDao bookDao;
    private NoteDao noteDao;
    private ShopService shopService;
    

    public TestController(UsrDao usrDao, StaDao staDao, BookDao bookDao, NoteDao noteDao,ShopService shopService) {
        this.usrDao = usrDao;
        this.staDao = staDao;
        this.bookDao = bookDao;
        this.noteDao=noteDao;
        this.shopService=shopService;

    }

    @GetMapping(value = "studentMan")

    public RetResult getStudent() {
        //这里比原来的接口返回数据多了code msg 返回RetResult结果 map 是因为data : student{...}
        Map<String, Object> test = new HashMap<String, Object>();
        test.put("student", usrDao.findAll());
        return new RetResult(RetCode.SUCCESS.getCode(), test);
    }

    //得到豆瓣前几的书 用页码按键来分页获取
    @GetMapping(value = "getTop")
    public RetResult getTop(@RequestParam Integer page) {
        Map<String, Object> res = new HashMap<>();
        List<Integer> arr = new ArrayList<>();
//        @RequestParam(value = "page", required = false, defaultValue = "1") Integer page
        Integer start = 25 * page - 24;
        for (int i = 0; i < 25; i++) {
            arr.add(start);
            start=start+1;
        }
        res.put("books", bookDao.findAllById(arr));
        return new RetResult(RetCode.SUCCESS.getCode(), res);
    }
    //得到今天的记事本内容
    @GetMapping(value = "getNote")
    public RetResult getNote(@RequestParam(name="time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date time)
    {
        Map<String, Object> res = new HashMap<>();
        res.put("noteList",noteDao.findByTimeAndAndFlag(time,0));
        res.put("doneList",noteDao.findByTimeAndAndFlag(time,1));
        res.put("undoList",noteDao.findByTimeAndAndFlag(time,2));
        return new RetResult(RetCode.SUCCESS.getCode(),res);
    }
    //未完成0 已完成1 已取消2
    @PostMapping(value = "addNote")
    public RetResult addNote(@RequestBody Note body){
        Map<String, Object> res = new HashMap<>();
        Note note=new Note();
        note.setTitle(body.getTitle());
        note.setTime(body.getTime());
        note.setFlag(0);
        noteDao.save(note);
        res.put("notes","ok");
        res.put("id",note.getId());
        return new RetResult(RetCode.SUCCESS.getCode(),res);
    }
    @PostMapping(value = "changeState")
    public RetResult changeState(@RequestParam Integer id,@RequestParam Integer flag){
        Map<String, Object> res = new HashMap<>();
        Optional<Note> note=noteDao.findById(id);
        if(note.isPresent()){
            Note note1=note.get();
            note1.setFlag(flag);
            noteDao.save(note1);
            res.put("change","ok");
        }else{
            res.put("change","The id is not exist");
        }

        return new RetResult(RetCode.SUCCESS.getCode(),res);
    }

    @PostMapping(value = "addStu")
    public String saveUsr(@RequestParam String name, @RequestParam String tel, @RequestParam String gender) {
        Student stu = new Student();
        stu.setName(name);
        stu.setGender(gender);
        stu.setTel(tel);
        usrDao.save(stu);
        return "suc";
    }

    @GetMapping(value = "getState")
    public Iterable<State> getState() {
        return staDao.findAll();
    }

    @GetMapping(value = "getShop")
    public RetResult getShop(){
        Map<String, Object> res = new HashMap<>();
        List<Shop> list=new ArrayList<>();
        list=shopService.getAll();
        res.put("restaurant",list);
        return new RetResult(RetCode.SUCCESS.getCode(), res);
    }
}
