package entity;

public class TakeMedkitState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
    }

    @Override
    public void afterShow(Player person) {
        person.getQuestContext().findStateNodeByName("takeMedkit").setShowNode(false);
        person.addItems(PlayerItemType.MEDKIT);
    }
}
