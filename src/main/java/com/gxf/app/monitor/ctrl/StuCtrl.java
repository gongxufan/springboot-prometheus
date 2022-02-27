package com.gxf.app.monitor.ctrl;

import com.gxf.app.monitor.entity.Student;
import com.gxf.app.monitor.mapper.StudentMapper;
import com.gxf.app.monitor.service.StuService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/stu")
public class StuCtrl {

    @Autowired
    private StuService stuService;

    @GetMapping("/list")
    public List<Student> list(){
        List<Student> students = stuService.list();
        return students;
    }


    @GetMapping("/save")
    public String save(String city,String name,String school){
        stuService.save(city, name, school);
        return "success";
    }
}
