package service;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import engine.QuestStateNode;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class QuestScenarioLoaderService {
    private String initialState;
    @Getter
    private String questTitle;
    @Getter
    private String questDescription;
    private Map<String, QuestStateNode> states;

    public QuestScenarioLoaderService() {
        this.states = new HashMap<>();
    }

    public void loadFromYaml(URL url) throws IOException, Exception {
        YAMLMapper mapper = new YAMLMapper();
        try (InputStream inputStream = url.openStream()) {
            if (inputStream == null) {
                throw new IOException("File " + url + " not found!");
            }

            Map<String, Object> rawScenario = mapper.readValue(inputStream, Map.class);
            initialState = (String) rawScenario.get("initialState");
            questTitle = (String) rawScenario.get("title");
            questDescription = (String) rawScenario.get("description");
            Map<String, Map<String, Object>> rawStates = (Map<String, Map<String, Object>>) rawScenario.get("states");

            for (var entry : rawStates.entrySet()) {
                String stateKey = entry.getKey();
                Map<String, Object> stateData = entry.getValue();
                String name = stateKey;
                String description = (String) stateData.get("description");
                String className = (String) stateData.get("className");

                QuestStateNode stateNode = new QuestStateNode(name, description, className);
                states.put(stateKey, stateNode);
            }

            for (var entry : rawStates.entrySet()) {
                String stateKey = entry.getKey();
                Map<String, Object> stateData = entry.getValue();
                Map<String, String> transitions = (Map<String, String>) stateData.get("transitions");

                QuestStateNode stateNode = states.get(stateKey);
                if (transitions != null) {
                    for (Map.Entry<String, String> transition : transitions.entrySet()) {
                        var key = states.get(transition.getValue());
                        if (key == null) {
                               throw new Exception("Нет узла " + transition.getValue() + " для \"" + transition.getKey() + "\"");
                        }
                        stateNode.addTransition(key, transition.getKey());
                    }
                }
            }

        }
    }

    public QuestStateNode getInitialState() {
        return states.get(initialState);
    }
}
