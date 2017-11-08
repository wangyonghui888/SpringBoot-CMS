package in.hocg.web.filter;

import in.hocg.web.filter.group.Insert;
import in.hocg.web.filter.group.Update;
import in.hocg.web.modules.domain.Variable;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hocgin on 2017/11/6.
 * email: hocgin@gmail.com
 */
@Data
public class VariableFilter implements Serializable {
    /**
     * 仅更新拥有
     */
    @NotEmpty(message = "ID异常", groups = {Update.class})
    protected String id;
    
    /**
     * 更新 与 增加 均拥有
     */
    @NotBlank(message = "字段名不能为空", groups = {Update.class, Insert.class})
    @Pattern(regexp = "^\\w+$", message = "字段名只允许由英文、数字、下划线组成", groups = {Update.class, Insert.class})
    protected String key;
    @NotBlank(message = "值不能为空", groups = {Update.class, Insert.class})
    protected String value;
    protected String note;
    
    public Variable get() {
        Variable variable = new Variable();
        variable.setKey(key);
        variable.setValue(value);
        variable.setNote(note);
        
        variable.setCreatedAt(new Date());
        return variable;
    }
    
    public Variable update(Variable variable) {
        variable.setValue(value);
        variable.setNote(note);
    
        variable.setUpdatedAt(new Date());
        return variable;
    }
}
