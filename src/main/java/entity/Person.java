package entity;

import lombok.Getter;

public class Person {
    @Getter
    int health;

    public Person() {
        this.health = 100;
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
}
