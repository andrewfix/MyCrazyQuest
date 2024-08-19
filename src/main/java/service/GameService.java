package service;

import engine.QuestContext;
import entity.DefaultQuestState;
import entity.Player;
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
    private final QuestContext<QuestState<Player>, Player> quest;
    private Player person;

    public GameService(URL fileName) throws IOException, Exception {
        scenarioLoaderService = new QuestScenarioLoaderService();
        scenarioLoaderService.loadFromYaml(fileName);

        var scenario = scenarioLoaderService.getInitialState();
        this.gameTitle = scenarioLoaderService.getQuestTitle();
        this.gameDescription = scenarioLoaderService.getQuestDescription();

        quest = new QuestContext<>(scenario);
        quest.setDefaultState(new DefaultQuestState());
        newGame();
    }

    public String getStateNodeDescriptions() {
        return quest.getCurrentStateNode().getDescription();
    }

    public Map<String, String> getStateNodeTransitions() {
        Map<String, String> map = new HashMap<>();
        this.quest.getCurrentStateNode().getTransitions()
                .entrySet()
                .stream().filter(x -> x.getKey().isShowNode())
                .forEach(x -> {
                    map.put(x.getKey().getName(), x.getValue());
                });
        return map;
    }

    public Map<String, String> getEntityInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("Здоровье", String.valueOf(person.getHealth()));
        map.put("Невменяемость", String.valueOf(person.getInsanity()));
        map.put("Предметы в наличии", person.getItems().toString());
        return map;
    }

    public void newGame() {
        quest.restart();
        person = new Player(quest);
        quest.setEntity(person);
    }

    public void goTo(String stateName) throws Exception {

        ((QuestState<Player>) quest.createStateInstance()).beforeExit((Player) quest.getEntity());
        quest.setCurrentStateNode(stateName);
        ((QuestState<Player>) quest.createStateInstance()).afterShow((Player) quest.getEntity());
    }

    public boolean isGameEnded() {
        return quest.isQuestEnded();
    }
}
