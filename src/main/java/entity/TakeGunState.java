package entity;

public class TakeGunState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
        person.getQuestContext().findAllStateNodeByName("takeGun").setShowNode(false);
        person.addItems(PlayerItemType.GUN);
    }

    @Override
    public void afterShow(Player person) {
        // TODO document why this method is empty
    }
}
