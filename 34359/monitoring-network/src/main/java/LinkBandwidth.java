public class LinkBandwidth {
    private long actualBandwidth; // 实际使用带宽（bps）
    private long availableBandwidth; // 可用带宽（bps）
    private double utilization; // 链路利用率（百分比）

    public LinkBandwidth(long actualBandwidth, long availableBandwidth, double utilization) {
        this.actualBandwidth = actualBandwidth;
        this.availableBandwidth = availableBandwidth;
        this.utilization = utilization;
    }

    public long getActualBandwidth() {
        return actualBandwidth;
    }

    public long getAvailableBandwidth() {
        return availableBandwidth;
    }

    public double getUtilization() {
        return utilization;
    }

    @Override
    public String toString() {
        return "LinkBandwidth{" +
                "actualBandwidth=" + actualBandwidth +
                ", availableBandwidth=" + availableBandwidth +
                ", utilization=" + utilization +
                "%}";
    }
}
