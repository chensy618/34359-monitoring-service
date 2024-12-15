public class Instruction {
    private String type;  // 指令类型
    private String port;  // 端口信息

    public Instruction(String type, String port) {
        this.type = type;
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "type='" + type + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}