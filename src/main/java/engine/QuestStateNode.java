package engine;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class QuestStateNode {
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private Map<QuestStateNode, String> transitions;
    @Getter
    private String className;

    public QuestStateNode(String name, String description, String className) {
        this.name = name;
        this.description = description;
        this.className = className;
        this.transitions = new HashMap<>();
    }

    public void addTransition(QuestStateNode nextState, String action) {
        this.transitions.put(nextState, action);
    }
}
