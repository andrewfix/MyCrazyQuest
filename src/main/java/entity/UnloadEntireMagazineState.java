package entity;

public class UnloadEntireMagazineState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {

    }

    @Override
    public void afterShow(Player person) {
        person.deleteItems(PlayerItemType.GUN);
    }
}
