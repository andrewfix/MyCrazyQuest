package entity;

public class OfficeRoomState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {

    }

    @Override
    public void afterShow(Player person) {
        person.getQuestContext().findAllStateNodeByName("unloadEntireMagazine")
                .setShowNode(person.getItems().contains(PlayerItemType.GUN));
        person.getQuestContext().findAllStateNodeByName("throwGrenade")
                .setShowNode(person.getItems().contains(PlayerItemType.GRENADE));
        person.decInsanity(10);
    }
}
