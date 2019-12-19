package net.wanhe.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/12/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String salt;
}
