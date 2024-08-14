package entity;

import entity.QuestState;

public class DefaultQuestState implements QuestState {
    @Override
    public void prepare() {
        System.out.println("Prepare.....");
    }

    @Override
    public void execute() {
        System.out.println("Execute!");
    }
}
