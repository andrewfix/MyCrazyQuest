package entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PlayerItemType {
    GUN("Пистолет"),
    KEY("Ключ"),
    MEDKIT("Аптечка"),
    GRENADE("Ручная граната"),
    ANTIVIRUS("Антивирус");

    @Getter
    private final String displayName;

    @Override
    public String toString() {
        return displayName;
    }

}
