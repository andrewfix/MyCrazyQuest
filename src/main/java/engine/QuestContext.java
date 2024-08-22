package engine;

import exception.IncorrectStateException;
import exception.NewQuestException;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;

public class QuestContext<T, V> {
    private final QuestStateNode initStateNodePrototype;
    @Getter
    private QuestStateNode currentStateNode;
    private QuestStateNode initStateNode;
    @Getter
    @Setter
    private V entity;
    @Setter
    private T defaultState;

    public QuestContext(QuestStateNode initStateNode) throws NewQuestException {
        this.initStateNodePrototype = initStateNode;
        this.defaultState = null;
    }

    public T createStateInstance() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (currentStateNode.getClassName() == null) {
            return defaultState;
        }
        Class<?> stateClass = Class.forName("entity." + currentStateNode.getClassName());
        return (T) stateClass.getDeclaredConstructor().newInstance();
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
        if ((stateName == null) || ((newStateNode = findAllStateNodeByName(stateName)) == null)) {
            throw new IncorrectStateException(stateName);
        }
        this.currentStateNode = newStateNode;
    }

    public void setCurrentStateNode(QuestStateNode newStateNode) throws Exception {
        if (newStateNode == null) {
            throw new IncorrectStateException("Null");
        }
        this.currentStateNode = newStateNode;
    }

    public boolean isQuestEnded() {
        return this.currentStateNode.getTransitions().isEmpty();
    }

    public void restart() throws NewQuestException {
        try {
            initStateNode = initStateNodePrototype.clone();
        } catch (CloneNotSupportedException e) {
            throw new NewQuestException(e);
        }
        currentStateNode = initStateNode;
        entity = null;
    }

}
