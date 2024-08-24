package entity;

public class UnloadEntireMagazineState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
        // TODO document why this method is empty
    }

    @Override
    public void afterShow(Player person) {
        person.deleteItems(PlayerItemType.GUN);
    }
}
