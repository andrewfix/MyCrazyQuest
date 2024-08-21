package entity;

public class AttackWithFistsState implements QuestState<Player> {
    @Override
    public void beforeExit(Player person) {
    }

    @Override
    public void afterShow(Player person) {
        person.decStrength(40);
        person.incInsanity(25);
    }
}
