package in.hocg.web.modules.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

/**
 * Created by hocgin on 2017/10/24.
 * email: hocgin@gmail.com
 */
@Data
@Document(collection = "Role")
public class Role extends BaseDomain{
    
    @Id
    private String id;
    
    private String name; // 名称: 管理员
    
    private String role; // 角色标志: admin
    
    private String description; // 角色描述
    
    private Boolean available = Boolean.FALSE; // 是否启用, 默认保留, 不分配。
    
    private Boolean builtIn = Boolean.FALSE; // 是否为内置, 默认False
    
    @DBRef
    private Department department;
    
    @DBRef
    private Collection<SysMenu> permissions;
    
    public Role() {
    }
    
    
    // 用户
    public static final String ROLE_USER = "ROLE_USER";
    // 管理员
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
}
