import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BandwidthUtilizationCalculator {
    private final long linkCapacityBps;

    // 构造方法，传入链路容量
    public BandwidthUtilizationCalculator(long linkCapacityBps) {
        this.linkCapacityBps = linkCapacityBps;
    }

    // 计算链路带宽利用率的方法
    public Map<String, LinkBandwidth> calculateLinkBandwidthUtilization(List<Link> linksData, Map<String, Port> oldPortStates, Map<String, Port> newPortStates) {
        Map<String, LinkBandwidth> linkBandwidthMap = new HashMap<>();

        for (Link link : linksData) {
            String sourcePortKey = link.getSource(); // 源端口 key
            String destinationPortKey = link.getDestination(); // 目标端口 key

            // 获取新旧端口状态
            Port oldSourcePort = oldPortStates.get(sourcePortKey);
            Port newSourcePort = newPortStates.get(sourcePortKey);

            if (oldSourcePort != null && newSourcePort != null) {
                // 计算时间间隔
                long deltaTime = newSourcePort.getDurationSec() - oldSourcePort.getDurationSec();
                if (deltaTime > 0) {
                    // 计算发送字节数变化
                    long bytesSent = newSourcePort.getBytesSent() - oldSourcePort.getBytesSent();
                    long actualBandwidth = (bytesSent * 8) / deltaTime; // 计算实际带宽（bps）
                    long availableBandwidth = linkCapacityBps - actualBandwidth; // 计算可用带宽
                    double utilization = (double) actualBandwidth / linkCapacityBps * 100.0; // 计算利用率

                    // 创建 LinkBandwidth 对象
                    LinkBandwidth linkBandwidth = new LinkBandwidth(actualBandwidth, availableBandwidth, utilization);
                    linkBandwidthMap.put(sourcePortKey + " -> " + destinationPortKey, linkBandwidth);
                }
            }
        }
        return linkBandwidthMap;
    }
}
