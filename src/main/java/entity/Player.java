package entity;

import engine.QuestContext;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Player {
    @Getter
    private int health;
    @Getter
    private int insanity;
    @Getter
    private final QuestContext questContext;

    @Getter
    private final ArrayList<PlayerItemType> items;

    public Player(QuestContext questContext) {
        this.health = 100;
        this.insanity = 0;
        this.items = new ArrayList<>();
        this.questContext = questContext;
    }

    public void addItems(PlayerItemType itemType) {
        items.add(itemType);
    }

    public void incStrength(int val) {
        health += val;
        if (health > 100) {
            health = 100;
        }
    }

    public void decStrength(int val) {
        health -= val;
        if (health < 0) {
            health = 0;
        }
    }

    public void incInsanity(int val) {
        insanity += val;
        if (insanity > 100) {
            insanity = 100;
        }
    }

    public void decInsanity(int val) {
        insanity -= val;
        if (insanity < 0) {
            insanity = 0;
        }
    }
}
