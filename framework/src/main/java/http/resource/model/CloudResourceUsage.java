package http.resource.model;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 * @author Jiaju Zhuang
 **/
@Data
public class CloudResourceUsage {
    @ExcelProperty("节点名")
    private String nodeName;
    @ExcelProperty("归属组")
    private String ft;
}
