public class Host {
    private String mac;          // 主机的 MAC 地址
    private String ipAddresses;  // 逗号分隔的 IP 地址字符串
    private String locations;    // 逗号分隔的 location 字符串 (elementId:port)

    // 构造方法
    public Host(String mac, String ipAddresses, String locations) {
        this.mac = mac;
        this.ipAddresses = ipAddresses;
        this.locations = locations;
    }

    // Getter 和 Setter 方法
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(String ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    // toString 方法：方便打印对象信息
    @Override
    public String toString() {
        return "Host{" +
                "mac='" + mac + '\'' +
                ", ipAddresses='" + ipAddresses + '\'' +
                ", locations='" + locations + '\'' +
                '}';
    }
}