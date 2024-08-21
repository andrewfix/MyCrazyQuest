package entity;

public class UseMedkitState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
        person.deleteItems(PlayerItemType.MEDKIT);
        person.incStrength(50);
        person.decInsanity(20);
    }

    @Override
    public void afterShow(Player person) {
    }
}
