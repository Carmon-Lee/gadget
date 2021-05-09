//package design;
//
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//
////19.2.32.1,19.2.33.1,广东
////18.3.54.1,18.5.32.1, 广西
////20.43.54.1,25.43.55.1,北京
////...
////
////文件解析：每一行表示一个ip范围段及其对应的省份，以逗号分隔成三端，第一段为IP的起始地址(ipv4),第二段为结束地址(ipv4),第三段为省份，ip范围连续，范围之间无重叠。
////
////查询频率非常高，需要实现快速高效的查询
//public class IpStoreImpl implements IpStore {
//
//    ArrayList<IpAddr> addrStartList = null;
//
//    @Override
//    public void loadData(String path) {
//        //  ... 读取文件数据
//        // 输入的数据
//        String date = "";
//        String[] lines = date.split("\n");
//        Map<Long, IpAddr> cache = new HashMap<>();
//        for (String line : lines) {
//            String[] ipRange = line.split(",");
//            if (ipRange.length < 2) {
//                continue;
//            }
//            IpAddr ipAddr = new IpAddr();
//            ipAddr.start = ip2Long(ipRange[0]);
//            ipAddr.end = ip2Long(ipRange[1]);
//            ipAddr.addr = ipRange[2];
//            cache.put(ipAddr.start, ipAddr);
//        }
//
//        addrStartList = new ArrayList<>(cache.values());
//        addrStartList.sort((o1, o2) -> (int) (o1.start - o2.start));
//
//    }
//
//
//    private long ip2Long(String ipAddr) {
//        String[] parts = ipAddr.split(".");
//        long ip = 0;
//        for (String part : parts) {
//            ip <<= 8;
//            ip += Integer.parseInt(part);
//        }
//        return ip;
//    }
//
//    @Override
//    public String proviceOfIp(String ip) {
//        if (addrStartList==null) {
//            loadData("xxxx");
//        }
//
//        long ipvalue = ip2Long(ip);
//        IpAddr ipAddr = new IpAddr();
//        ipAddr.start = ipvalue;
//        int startIdx = binarySearch(addrStartList, ipvalue);
//        // 不存在
//        if (startIdx == -1) {
//            return "";
//        }
//        // 开始区间没找到，结束区间找到了
//        if (addrStartList.get(startIdx).end > ipvalue) {
//            return addrStartList.get(startIdx).addr;
//        }
//        return "";
//    }
//
//
//    private int binarySearch(ArrayList<IpAddr> addrStartList, long value) {
//        int start = 0;
//        int end = addrStartList.size() - 1;
//        int idx = -1;
//        while (start < end) {
//            int mid = (start + end) / 2;
//            if (addrStartList.get(mid).start > value) {
//                end = mid;
//            } else if (addrStartList.get(mid).start == value) {
//                return mid;
//            } else {
//                start = mid;
//            }
//        }
//        return idx;
//    }
//
//    @Data
//    static class IpAddr  {
//        long start;
//        long end;
//        String addr;
//    }
//}
