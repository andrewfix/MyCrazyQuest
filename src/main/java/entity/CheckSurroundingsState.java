package entity;

public class CheckSurroundingsState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
        person.getQuestContext().findAllStateNodeByName("checkSurroundings").setShowNode(false);
    }

    @Override
    public void afterShow(Player person) {
        person.decStrength(30);
        person.incInsanity(25);
    }
}
