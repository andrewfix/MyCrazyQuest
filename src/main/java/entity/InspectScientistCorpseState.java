package entity;

public class InspectScientistCorpseState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
        person.getQuestContext().findAllStateNodeByName("InspectScientistCorpse").setShowNode(false);
        person.addItems(PlayerItemType.KEY);
    }

    @Override
    public void afterShow(Player person) {
    }
}
