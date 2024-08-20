package entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PlayerItemType {
    KNIFE("Нож"),
    GUN("Пистолет"),
    KEY("Ключ"),
    MEDKIT("Аптечка"),
    GRENADE("Ручная граната");

    @Getter
    private final String displayName;

    @Override
    public String toString() {
        return displayName;
    }

}
