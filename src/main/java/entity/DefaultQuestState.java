package entity;

public class DefaultQuestState implements QuestState<Person> {
    @Override
    public void before(Person person) {
        person.decStrength(5);
    }

    @Override
    public void after(Person person) {

    }
}
