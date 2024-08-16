package entity;

public interface QuestState<V> {
    void before(V v);
    void after(V v);
}
