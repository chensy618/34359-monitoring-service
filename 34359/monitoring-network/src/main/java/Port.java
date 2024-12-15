public class Port{
    private String devicePort;
    private int packetsReceived; // 接收的包数
    private int packetsSent; // 发送的包数
    private long bytesReceived; // 接收的字节数
    private long bytesSent; // 发送的字节数
    private long durationSec; // 端口活动时长（秒）

    public Port(String devicePort, int packetsReceived, int packetsSent,
                long bytesReceived, long bytesSent, long durationSec) {
        this.devicePort = devicePort;
        this.packetsReceived = packetsReceived;
        this.packetsSent = packetsSent;
        this.bytesReceived = bytesReceived;
        this.bytesSent = bytesSent;
        this.durationSec = durationSec;
    }

    // Getter 和 Setter
    public String getDevicePort() {
        return devicePort;
    }

    public void setDevicePort(String devicePort) {
        this.devicePort = devicePort;
    }

    public int getPacketsReceived() {
        return packetsReceived;
    }

    public void setPacketsReceived(int packetsReceived) {
        this.packetsReceived = packetsReceived;
    }

    public int getPacketsSent() {
        return packetsSent;
    }

    public void setPacketsSent(int packetsSent) {
        this.packetsSent = packetsSent;
    }

    public long getBytesReceived() {
        return bytesReceived;
    }

    public void setBytesReceived(long bytesReceived) {
        this.bytesReceived = bytesReceived;
    }

    public long getBytesSent() {
        return bytesSent;
    }

    public void setBytesSent(long bytesSent) {
        this.bytesSent = bytesSent;
    }

    public long getDurationSec() {
        return durationSec;
    }

    public void setDurationSec(long durationSec) {
        this.durationSec = durationSec;
    }

    @Override
    public String toString() {
        return "Port{" +
                "devicePort='" + devicePort + '\'' +
                ", packetsReceived=" + packetsReceived +
                ", packetsSent=" + packetsSent +
                ", bytesReceived=" + bytesReceived +
                ", bytesSent=" + bytesSent +
                ", durationSec=" + durationSec +
                '}';
    }
}