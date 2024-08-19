package entity;

public class CheckSurroundingsState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
        person.decStrength(1);
        person.getQuestContext().findAllStateNodeByName("checkSurroundings").setShowNode(false);
    }

    @Override
    public void afterShow(Player person) {
        person.decStrength(20);
        person.incInsanity(10);


    }
}
