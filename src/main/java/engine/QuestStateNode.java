package engine;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.Predicate;

public class QuestStateNode {
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private final Map<QuestStateNode, String> transitions;
    @Getter
    private String className;
    @Getter
    @Setter
    private boolean showNode;

    public QuestStateNode(String name, String description, String className) {
        this.name = name;
        this.description = description;
        this.className = className;
        this.transitions = new HashMap<>();
        this.showNode = true;
    }

    public void addTransition(QuestStateNode nextState, String action) {
        this.transitions.put(nextState, action);
    }

    public Set<QuestStateNode> find(Predicate<QuestStateNode> predicate) {
        Set<QuestStateNode> result = new HashSet<>();
        Set<QuestStateNode> visitedNodes = new HashSet<>();
        dfs(this, visitedNodes, result, predicate);
        return result;
    }

    private void dfs(QuestStateNode node, Set<QuestStateNode> visitedNodes, Set<QuestStateNode> result, Predicate<QuestStateNode> predicate) {
        if (visitedNodes.contains(node)) {
            return;
        }
        visitedNodes.add(node);
        if (predicate.test(node)) {
            result.add(node);
        }

        for (QuestStateNode next : node.transitions.keySet()) {
            dfs(next, visitedNodes, result, predicate);
        }
    }

}
