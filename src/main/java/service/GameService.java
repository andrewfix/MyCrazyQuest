package service;

import engine.QuestContext;
import entity.DefaultQuestState;
import entity.Player;
import entity.QuestState;
import exception.NewQuestException;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GameService {
    @Getter
    private final String gameTitle;
    @Getter
    private final String gameDescription;
    private final QuestScenarioLoaderService scenarioLoaderService;
    private final QuestContext<QuestState<Player>, Player> quest;
    private Player person;

    public GameService(URL fileName) throws Exception, NewQuestException {
        scenarioLoaderService = new QuestScenarioLoaderService();
        scenarioLoaderService.loadFromYaml(fileName);

        var scenario = scenarioLoaderService.getInitialState();
        this.gameTitle = scenarioLoaderService.getQuestTitle();
        this.gameDescription = scenarioLoaderService.getQuestDescription();

        quest = new QuestContext<>(scenario);
        quest.setDefaultState(new DefaultQuestState());
    }

    public String getStateNodeDescriptions() {
        return quest.getCurrentStateNode().getDescription();
    }

    public Map<String, String> getStateNodeTransitions() {
        Map<String, String> map = new HashMap<>();
        this.quest.getCurrentStateNode().getTransitions()
                .entrySet()
                .stream().filter(x -> x.getKey().isShowNode())
                .forEach(x -> map.put(x.getKey().getName(), x.getValue()));
        return map;
    }

    public Map<String, String> getEntityInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("Здоровье", person.getHealth() + "%");
        map.put("Невменяемость", person.getInsanity() + "%");
        map.put("Предметы в наличии", person.getItems().toString());
        return map;
    }

    public void newGame() throws NewQuestException {
        quest.restart();
        person = new Player(quest);
        quest.setEntity(person);
    }

    public void goTo(String stateName) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        quest.createStateInstance().beforeExit(quest.getEntity());
        quest.setCurrentStateNode(stateName);
        quest.createStateInstance().afterShow(quest.getEntity());
        var forward = quest.getCurrentStateNode().getForward();
        if (forward != null) {
            quest.createStateInstance().beforeExit(quest.getEntity());
            quest.setCurrentStateNode(forward);
        }
        if (quest.getEntity().getHealth() == 0) {
            quest.setCurrentStateNode("finishDeath");
        }
    }

    public boolean isGameEnded() {
        return quest.isQuestEnded();
    }
}
