package edu.gdut.eduservice.controller;


import edu.gdut.eduservice.entity.EduTeacher;
import edu.gdut.eduservice.mapper.EduTeacherMapper;
import edu.gdut.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-04-22
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public List<EduTeacher> findAll(){
        List<EduTeacher> eduTeachers = eduTeacherService.list(null);
        return eduTeachers;
    }

    @ApiOperation(value = "删除讲师")
    @DeleteMapping("{id}")
    public Boolean removeTeacher(@ApiParam(name="id",value="讲师id",required=true)  @PathVariable String id){
        boolean isRemoved = eduTeacherService.removeById(id);
        return isRemoved;
    }
}



