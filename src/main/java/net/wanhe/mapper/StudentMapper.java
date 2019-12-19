package net.wanhe.mapper;

import net.wanhe.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/12/10.
 */
public interface StudentMapper {

    List<Student> getAllStus();

    void delStu(Integer id);

    void addStu(Student student);

    Student getStuById(Integer id);

    void updateStudent(Student student);

    List<Student> getAllStusPage(@Param("gender") String gender, @Param("address") String address);
}
