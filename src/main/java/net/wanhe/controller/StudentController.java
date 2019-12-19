package net.wanhe.controller;

import com.github.pagehelper.PageInfo;
import net.wanhe.pojo.Student;
import net.wanhe.service.StudentServiceI;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/12/12.
 */
@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentServiceI studentServiceI;

    @RequestMapping("stus")
    public String getAllStus(Map map){
        List<Student> stus = studentServiceI.getAllStus();
        map.put("stus",stus);
        return "student";
    }


    @RequestMapping("getAllStus")
    public String getAllStus(Map map,@RequestParam(defaultValue = "1") Integer pageNum,String gender,String address){
        PageInfo<Student> pageInfo = studentServiceI.getAllStusPage(pageNum,gender,address);
        map.put("pageInfo", pageInfo);
        map.put("gender", gender);
        map.put("address", address);
        return "student";
    }

    @RequestMapping("delStus/{id}")
    public String delStus( @PathVariable("id") Integer id){
        studentServiceI.delStu(id);
        return "redirect:/student/getAllStus";
    }
    @RequestMapping("toAddStu")
    public String toAddStu(){
        return "addStu";
    }

    @RequestMapping("addStu")
    public String addStu( Student student){
        System.out.println(student);
        studentServiceI.addStu(student);
        return "redirect:/student/getAllStus";
    }

    @RequestMapping("toUpdateStu/{id}")
    public String toUpdateStu(Map map,@PathVariable("id") Integer id){
        Student student = studentServiceI.getStuById(id);
        map.put("stu",student);
        return "updateStu";
    }

    @RequiresPermissions("student:update")
    @RequestMapping("updateStu")
    public String updateStu(Student student){
        studentServiceI.updateStudent(student);
        return "redirect:/student/getAllStus";
    }

    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        List<Student> allStus = studentServiceI.getAllStus();

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet();
        HSSFRow titlerRow  = sheet.createRow(0);
        titlerRow.createCell(0).setCellValue("学号");
        titlerRow.createCell(1).setCellValue("姓名");
        titlerRow.createCell(2).setCellValue("年龄");
        titlerRow.createCell(3).setCellValue("性别");
        titlerRow.createCell(4).setCellValue("地址");

        for (Student stus : allStus) {
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow row = sheet.createRow(lastRowNum + 1);
            row.createCell(0).setCellValue(stus.getId());
            row.createCell(1).setCellValue(stus.getSname());
            row.createCell(2).setCellValue(stus.getAge());
            row.createCell(3).setCellValue(stus.getGender());
            row.createCell(4).setCellValue(stus.getAddress());
        }

        String filename="student.xls";
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            //7.获取mimeType
            String mimeType = request.getServletContext().getMimeType(filename);
            //9.设置信息头
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition","attachment;filename="+filename);
            hssfWorkbook.write(outputStream);
            outputStream.close();
            hssfWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
