package net.wanhe.service;

import com.github.pagehelper.PageInfo;
import net.wanhe.pojo.Student;

import java.util.List;

/**
 * Created by Administrator on 2019/12/10.
 */
public interface StudentServiceI {

    List<Student> getAllStus();

    void delStu(Integer id);

    void addStu(Student student);

    Student getStuById(Integer id);
    void updateStudent(Student student);

    PageInfo<Student> getAllStusPage(Integer pageNum, String gender, String address);
}
