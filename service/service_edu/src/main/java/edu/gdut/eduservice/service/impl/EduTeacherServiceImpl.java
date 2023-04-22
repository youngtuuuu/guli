package edu.gdut.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import edu.gdut.eduservice.entity.EduTeacher;
import edu.gdut.eduservice.mapper.EduTeacherMapper;
import edu.gdut.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-04-22
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Override
    public List<EduTeacher> list(Wrapper<EduTeacher> queryWrapper) {
        return super.list(queryWrapper);
    }
}
