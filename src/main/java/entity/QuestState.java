package entity;

public interface QuestState<V> {
    void beforeExit(V v);

    void afterShow(V v);
}
