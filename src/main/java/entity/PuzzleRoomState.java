package entity;

public class PuzzleRoomState implements QuestState<Person> {
    @Override
    public void before(Person person) {

    }

    @Override
    public void after(Person person) {
        person.decStrength(25);
    }
}
