import java.util.List;

public class Statistic {
    private String deviceId;
    private List<Port> ports; // 设备上的端口列表

    // 构造方法
    public Statistic(String deviceId, List<Port> ports) {
        this.deviceId = deviceId;
        this.ports = ports;
    }

    // Getter 和 Setter
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                ", deviceId=" + deviceId +
                ", ports=" + ports +
                '}';
    }
}
