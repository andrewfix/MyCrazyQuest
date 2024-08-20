package entity;

public class TakeGrenadeState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
        person.getQuestContext().findAllStateNodeByName("takeGrenade").setShowNode(false);
        person.addItems(PlayerItemType.GRENADE);
    }

    @Override
    public void afterShow(Player person) {
        int x=1;
    }
}
