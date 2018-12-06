package average;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FindAverage extends TickerBehaviour {
    private final DefaultAgent agent;
    private int currentStep;
    private final int MAX_STEPS = 70;
    private List<AID> agents;
    private double lambda;

    FindAverage(DefaultAgent agent, long period, List<String> linkedAgents) {
        super(agent, period);
        this.setFixedPeriod(true);
        this.agent = agent;
        this.currentStep = 0;
        lambda = 0.15;


        agents = new ArrayList<>();
        for (String agentName : linkedAgents) {
            agents.add(new AID(agentName, AID.ISLOCALNAME));
        }

    }

    @Override
    protected void onTick() {
        if (currentStep < MAX_STEPS) {
            double number = agent.getNumber();

            // отправляем сообщение
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            agents.forEach(msg::addReceiver);
            msg.setContent(Double.toString(number));
            agent.send(msg);

            //получаем сообщение, делаем расчеты
            double tmp = number;
            for (int i = 0; i < 40; i++) {
                ACLMessage inputMsg = agent.receive();
                if (Objects.nonNull(inputMsg)) {
                    double outerNumber = Double.valueOf(inputMsg.getContent());
                    outerNumber += Math.random() * 20.0 - 11.0;  //имитируем погрешность
                    double difference = number - outerNumber;
                    tmp -= difference * lambda;
                }
            }
            number = tmp;


            System.out.println("Agent name - " + agent.getLocalName() + " Step - " + currentStep + "   number - " + number);
            agent.setNumber(number);
            this.currentStep++;
        } else {
            System.out.println("Agent name - " + agent.getLocalName() + " Final number - " + agent.getNumber());
            this.stop();
        }
    }

}
