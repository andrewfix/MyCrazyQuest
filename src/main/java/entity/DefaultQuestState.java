package entity;

public class DefaultQuestState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {

    }

    @Override
    public void afterShow(Player person) {
        person.decStrength(1);
    }
}
