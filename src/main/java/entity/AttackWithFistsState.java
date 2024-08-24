package entity;

public class AttackWithFistsState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
        // TODO document why this method is empty
    }

    @Override
    public void afterShow(Player person) {
        person.decStrength(40);
        person.incInsanity(25);
    }
}
