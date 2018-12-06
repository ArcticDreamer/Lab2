package average;

import java.util.List;

public class AgentData {
    private String name;
    private List<String> linkedAgents;
    private double number;

    public AgentData(String name, List<String> linkedAgents, double number) {
        this.name = name;
        this.linkedAgents = linkedAgents;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public List<String> getLinkedAgents() {
        return linkedAgents;
    }

    public double getNumber() {
        return number;
    }
}
