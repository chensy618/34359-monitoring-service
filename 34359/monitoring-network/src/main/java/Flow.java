import java.util.List;

public class Flow {
    private String deviceId;                  // 设备 ID
    private List<Instruction> instructions;  // 处理指令列表
    private List<Criteria> criteria;        // 匹配条件列表

    public Flow(String deviceId, List<Instruction> instructions, List<Criteria> criteria) {
        this.deviceId = deviceId;
        this.instructions = instructions;
        this.criteria = criteria;
    }

    // Getters 和 Setters
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public List<Criteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "deviceId='" + deviceId + '\'' +
                ", instructions=" + instructions +
                ", criteria=" + criteria +
                '}';
    }
}
