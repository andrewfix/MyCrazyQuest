package entity;

public class OpenSafeState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
        person.getQuestContext().findAllStateNodeByName("openSafe").setShowNode(false);
        person.addItems(PlayerItemType.ANTIVIRUS);
    }

    @Override
    public void afterShow(Player person) {
    }
}
