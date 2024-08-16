package entity;

import lombok.Getter;

public class Person {
    @Getter
    int strength;

    public Person() {
        this.strength = 100;
    }

    public void incStrength(int val) {
        strength += val;
        if (strength > 100) {
            strength = 100;
        }
    }

    public void decStrength(int val) {
        strength -= val;
        if (strength < 0) {
            strength = 0;
        }
    }
}
