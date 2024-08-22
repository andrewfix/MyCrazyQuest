package entity;

public class InspectFlasksState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
        person.getQuestContext().findAllStateNodeByName("inspectFlasks").setShowNode(false);
    }

    @Override
    public void afterShow(Player person) {
        person.decStrength(40);
        person.incInsanity(25);
    }
}
