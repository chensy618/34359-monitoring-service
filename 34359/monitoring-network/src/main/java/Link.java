public class Link {
    private String source;
    private String destination;
    private String type;
    private String state;

    // Constructor
    public Link(String source, String destination, String type, String state) {
        this.source = source;
        this.destination = destination;
        this.type = type;
        this.state = state;
    }

    // Getters and setters
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Link{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

