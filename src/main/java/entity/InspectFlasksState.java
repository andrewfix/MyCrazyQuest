package entity;

public class InspectFlasksState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
        person.decStrength(1);
        person.getQuestContext().findAllStateNodeByName("inspectFlasks").setShowNode(false);
    }

    @Override
    public void afterShow(Player person) {
        person.decStrength(10);
        person.incInsanity(15);


    }
}
