package average;

import jade.core.Agent;

import java.util.List;

public class DefaultAgent extends Agent {
    private double number;

    @Override
    protected void setup() {
        Object[] args =  getArguments();
        number = (double) args[1];
        System.out.println("Agent name - " + getLocalName() + "  Initial number - " + number);
        addBehaviour(new FindAverage(this, /*TimeUnit.SECONDS.toMillis(1)*/100, (List<String>)args[0]));
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }
}

