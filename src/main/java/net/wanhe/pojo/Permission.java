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
public class Permission {
    private Integer permissionId;
    private String permissionName;
    private String url;
    private String permission;
    private Integer parentId;

}
