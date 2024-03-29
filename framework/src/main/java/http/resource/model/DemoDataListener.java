package http.resource.model;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;

/**
 * 模板的读取类
 *
 * @author Jiaju Zhuang
 */
// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class DemoDataListener extends AnalysisEventListener<CloudResourceUsage> {
    private Map<String, String> ftMapping = new HashMap<>();

    @Override
    public void invoke(CloudResourceUsage data, AnalysisContext context) {
        if (!StringUtils.isEmpty(data.getFt())) {
            ftMapping.put(data.getNodeName(), data.getFt());
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        System.out.println(ftMapping);
    }
}
