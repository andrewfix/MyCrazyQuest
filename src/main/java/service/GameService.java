package service;

import engine.QuestContext;
import entity.DefaultQuestState;
import entity.QuestState;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GameService {
    @Getter
    private String gameTitle;
    @Getter
    private String gameDescription;
    private QuestScenarioLoaderService scenarioLoaderService;
    private QuestContext quest;

    public GameService(URL fileName) throws IOException {
        scenarioLoaderService = new QuestScenarioLoaderService();
        scenarioLoaderService.loadFromYaml(fileName);

        var scenario = scenarioLoaderService.getInitialState();
        this.gameTitle = scenarioLoaderService.getQuestTitle();
        this.gameDescription = scenarioLoaderService.getQuestDescription();

        quest = new QuestContext<QuestState>(scenario);
        quest.setDefaultState(new DefaultQuestState());

    }

    public String getStateNodeDescriptions() {
        return quest.getCurrentStateNode().getDescription();
    }

    public QuestState createStateInstance() throws Exception {
        return (QuestState) quest.createStateInstance();
    }

    public Map<String, String> getStateNodeTransitions() {
        Map<String, String> map = new HashMap<>();
        this.quest.getCurrentStateNode().getTransitions().entrySet().forEach(x -> {
            map.put(x.getKey().getName(), x.getValue());
        });
        return map;
    }

    public void newGame() {
        quest.restart();
    }

    public void goTo(String stateName) throws Exception {
        quest.setCurrentStateNode(stateName);
    }

    public boolean isGameEnded() {
        return quest.isQuestEnded();
    }
}
