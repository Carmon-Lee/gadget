package http.resource;

import java.io.IOException;

import com.alibaba.excel.EasyExcel;

import http.resource.model.CloudResourceUsage;
import http.resource.model.DemoDataListener;

/**
 * @author liguang
 * Created on 2021-08-27
 */
public class ResourceProcess {

    public static void main(String[] args) throws IOException {
        String fileName = "/Users/liguang/IdeaProjects/gadget/framework/src/main/resources/list.xlsx";
        EasyExcel.read(fileName, CloudResourceUsage.class, new DemoDataListener()).sheet().doRead();
    }
}
