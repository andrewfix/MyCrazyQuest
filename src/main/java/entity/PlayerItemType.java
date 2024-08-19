package entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PlayerItemType {
    KNIFE("Нож"),
    GUN("Пистолет"),
    KEY("Ключ"),
    MEDKIT("Аптечка");

    @Getter
    private final String displayName;

    @Override
    public String toString() {
        return displayName;
    }

}
