package net.wanhe.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2019/12/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Role {
    private Integer roleId;
    private String roleName;
    private List<Permission> permissions;
}
