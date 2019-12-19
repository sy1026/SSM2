package net.wanhe.test;

import net.wanhe.pojo.Student;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/12/18.
 */
public class TestPoi {
    @Test
    public void importStusFromExcel() throws Exception {
        InputStream inputStream = new FileInputStream("D:/学生表.xls");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        List<Student> list = new ArrayList<>();
        for (Row row : sheet) {
            if(row.getRowNum()==0){
                continue;
            }
            Cell nameCell = row.getCell(0);
            nameCell.setCellType(CellType.STRING);
            String sname = nameCell.getStringCellValue();
            Cell ageCell = row.getCell(1);
            ageCell.setCellType(CellType.STRING);
            String age = ageCell.getStringCellValue();
            Cell genderCell = row.getCell(2);
            genderCell.setCellType(CellType.STRING);
            String gender = genderCell.getStringCellValue();
            Cell addressCell = row.getCell(3);
            addressCell.setCellType(CellType.STRING);
            String address = addressCell.getStringCellValue();
            Student student = new Student(null,sname,Integer.parseInt(age),gender,address);
            list.add(student);
        }
        System.out.println(list);
    }
}
