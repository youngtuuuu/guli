package edu.gdut.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdut.commonutils.R;
import edu.gdut.eduservice.entity.EduTeacher;
import edu.gdut.eduservice.entity.vo.TeacherQuery;
import edu.gdut.eduservice.mapper.EduTeacherMapper;
import edu.gdut.eduservice.service.EduTeacherService;
import edu.gdut.servicebase.myexception.MyExceptionTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public R findAll(){
        List<EduTeacher> eduTeachers = eduTeacherService.list(null);
        return R.ok().data("eduTeachers",eduTeachers);
    }

    @ApiOperation(value = "删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name="id",value="讲师id",required=true)  @PathVariable String id){
        boolean isRemoved = eduTeacherService.removeById(id);
        if(isRemoved){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "讲师分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        Page<EduTeacher> pageTeachers = new Page<>(current,limit);
        eduTeacherService.page(pageTeachers,null);
        long total = pageTeachers.getTotal();
        List<EduTeacher> records = pageTeachers.getRecords();

        Map map = new HashMap();
        map.put("total",total);
        map.put("records",records);

        return R.ok().data(map);
    }

    @ApiOperation(value = "讲师分页带条件查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){


        Page<EduTeacher> pageTeachers = new Page<>(current, limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        eduTeacherService.page(pageTeachers,wrapper);
        long total = pageTeachers.getTotal();
        List<EduTeacher> records = pageTeachers.getRecords();
        return R.ok().data("total",total).data("records",records);
    }

    @ApiOperation(value = "添加讲师模块")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean isAdded = eduTeacherService.save(eduTeacher);
        if (isAdded){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @ApiOperation(value = "根据id讲师")
    @GetMapping("getTeacher/{teacherId}")
    public R getTeacher(@PathVariable String teacherId){
        try{
            int i = 10/0;
        }catch (Exception e){
            throw new MyExceptionTest(20001,"自定义异常");
        }

        EduTeacher eduTeacher = eduTeacherService.getById(teacherId);
        return R.ok().data("teacher",eduTeacher);
    }


    @ApiOperation(value = "修改讲师")
    @PostMapping("updateTeacher/{id}")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean isUpdated = eduTeacherService.updateById(eduTeacher);
        if (isUpdated){
            return R.ok();
        }else{
            return R.error();
        }
    }
}



