package edu.gdut.eduservice.controller;


import edu.gdut.eduservice.entity.EduTeacher;
import edu.gdut.eduservice.mapper.EduTeacherMapper;
import edu.gdut.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-04-22
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("findAll")
    public List<EduTeacher> findAll(){
        List<EduTeacher> eduTeachers = eduTeacherService.list(null);
        return eduTeachers;
    }
}



