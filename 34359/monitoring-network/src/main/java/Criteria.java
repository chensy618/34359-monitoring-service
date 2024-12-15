public class Criteria {
    private String type;       // 匹配条件类型
    private String ethType;    // 以太网类型
    private String mac;
    private String port;

    public Criteria(String type, String ethType, String mac, String port) {
        this.type = type;
        this.ethType = ethType;
        this.mac = mac;
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEthType() {
        return ethType;
    }

    public void setEthType(String ethType) {
        this.ethType = ethType;
    }

    public String getMac() {
        return ethType;
    }

    public void setMac(String mac) {
        this.ethType = ethType;
    }

    public String getPort() {
        return ethType;
    }

    public void setPort(String port) {
        this.ethType = ethType;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "type='" + type + '\'' +
                ", ethType='" + ethType + '\'' +
                ", mac='" + mac + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}

