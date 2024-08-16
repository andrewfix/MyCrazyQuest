package service;

import engine.QuestContext;
import entity.DefaultQuestState;
import entity.Person;
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
    private final String gameDescription;
    private final QuestScenarioLoaderService scenarioLoaderService;
    private final QuestContext quest;
    private Person person;

    public GameService(URL fileName) throws IOException {
        scenarioLoaderService = new QuestScenarioLoaderService();
        scenarioLoaderService.loadFromYaml(fileName);

        var scenario = scenarioLoaderService.getInitialState();
        this.gameTitle = scenarioLoaderService.getQuestTitle();
        this.gameDescription = scenarioLoaderService.getQuestDescription();

        quest = new QuestContext<QuestState<Person>, Person>(scenario);
        quest.setDefaultState(new DefaultQuestState());
        newGame();
    }

    public String getStateNodeDescriptions() {
        return quest.getCurrentStateNode().getDescription();
    }

    public Map<String, String> getStateNodeTransitions() {
        Map<String, String> map = new HashMap<>();
        this.quest.getCurrentStateNode().getTransitions().entrySet().forEach(x -> {
            map.put(x.getKey().getName(), x.getValue());
        });
        return map;
    }

    public Map<String, String> getEntityInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("Health",String.valueOf(person.getHealth()));
        return map;
    }

    public void newGame() {
        quest.restart();
        person = new Person();
        quest.setEntity(person);
    }

    public void goTo(String stateName) throws Exception {

        QuestState<Person> stateInstance = (QuestState<Person>) quest.createStateInstance();
        stateInstance.before((Person) quest.getEntity());
        quest.setCurrentStateNode(stateName);
        stateInstance.after((Person) quest.getEntity());
    }

    public boolean isGameEnded() {
        return quest.isQuestEnded();
    }
}
