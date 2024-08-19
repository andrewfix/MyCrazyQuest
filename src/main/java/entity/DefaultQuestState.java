package entity;

public class DefaultQuestState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
        person.decStrength(1);
    }

    @Override
    public void afterShow(Player person) {

    }
}
