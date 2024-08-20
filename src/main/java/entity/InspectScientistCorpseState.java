package entity;

public class InspectScientistCorpseState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
        person.getQuestContext().findAllStateNodeByName("inspectScientistCorpse").setShowNode(false);
        person.getQuestContext().findAllStateNodeByName("darkCorridorContinuation").setShowNode(true);
        person.addItems(PlayerItemType.KEY);
    }

    @Override
    public void afterShow(Player person) {
    }
}
