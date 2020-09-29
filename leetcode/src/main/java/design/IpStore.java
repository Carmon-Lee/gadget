package design;


public  interface IpStore {
    /**
     * 加载数据
     */
    void loadData(String path);
    /**
     * 根据IP地址获取省份
     */
    String proviceOfIp(String ip);
}
