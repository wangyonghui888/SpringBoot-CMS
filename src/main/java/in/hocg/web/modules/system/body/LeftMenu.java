package in.hocg.web.modules.system.body;

import in.hocg.web.modules.system.domain.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2017/11/14.
 * email: hocgin@gmail.com
 */
@Data
@Deprecated
public class LeftMenu implements Serializable {
    private List<SysMenu> root;
    private Map<String, List<SysMenu>> children;
}
