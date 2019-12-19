package net.wanhe.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/12/4.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Student {

    private Integer id;
    private String sname;
    private Integer age;
    private String gender;
    private String address;

}
