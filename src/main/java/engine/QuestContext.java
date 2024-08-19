package engine;

import lombok.Getter;
import lombok.Setter;

public class QuestContext<T, V> {
    @Getter
    private QuestStateNode currentStateNode;
    private final QuestStateNode initStateNode;
    @Getter
    @Setter
    private V entity;
    @Setter
    private T defaultState;

    public QuestContext(QuestStateNode initStateNode) {
        this.currentStateNode = initStateNode;
        this.initStateNode = initStateNode;
        this.defaultState = null;
        this.entity = null;
    }

    public T createStateInstance() throws Exception {
        try {
            Class<?> stateClass = Class.forName("entity." + currentStateNode.getClassName());
            return (T) stateClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return defaultState;
        }
    }

    public QuestStateNode findStateNodeByName(String name) {
        try {
            return currentStateNode.find(x -> x.getName().equals(name)).stream().findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }

    public QuestStateNode findAllStateNodeByName(String name) {
        try {
            return initStateNode.find(x -> x.getName().equals(name)).stream().findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }

    public void setCurrentStateNode(String stateName) throws Exception {
        QuestStateNode newStateNode;
        if ((stateName == null) || ((newStateNode = findStateNodeByName(stateName)) == null)) {
            throw new Exception("Incorrect state!");
        }
        this.currentStateNode = newStateNode;
    }

    public boolean isQuestEnded() {
        return this.currentStateNode.getTransitions().isEmpty();
    }

    public void restart() {
        currentStateNode = initStateNode;
        entity = null;
    }

}
