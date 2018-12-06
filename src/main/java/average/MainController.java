package average;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MainController {


    void initAgents() {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.MAIN_PORT, "10100");
        p.setParameter(Profile.GUI, "true");
        ContainerController cc = rt.createMainContainer(p);

        List<AgentData> agentsData = createLinks();

        agentsData.forEach((data) -> {
            try {
                Object[] args = {data.getLinkedAgents(), data.getNumber()};
                AgentController agent = cc.createNewAgent(data.getName(), "average.DefaultAgent", args);
                agent.start();
            } catch (StaleProxyException e) {
                e.printStackTrace();
            }
        });


    }


    List<AgentData> createLinks() {
        List<AgentData> list = new ArrayList<>();


        list.add(new AgentData("01", Arrays.asList("02"), 100.00));
        list.add(new AgentData("02", Arrays.asList("01", "05", "03"), 200.00));
        list.add(new AgentData("03", Arrays.asList("02", "04"), 600.00));
        list.add(new AgentData("04", Arrays.asList("03","10"), 200.00));
        list.add(new AgentData("05", Arrays.asList("02", "07"), 150.00));
        list.add(new AgentData("06", Arrays.asList("07"), 800.00));
        list.add(new AgentData("07", Arrays.asList("05", "06", "08"), 215.00));
        list.add(new AgentData("08", Arrays.asList("07", "09"), 871.00));
        list.add(new AgentData("09", Arrays.asList("08", "10"), 364.00));
        list.add(new AgentData("10", Arrays.asList("04","09"), 115.00));


        return list;
    }
}