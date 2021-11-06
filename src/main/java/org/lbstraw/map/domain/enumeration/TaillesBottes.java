package org.lbstraw.map.domain.enumeration;

/**
 * Taille des bottes
 */
public enum TaillesBottes {
    T_70_X_120_X_230_CM("70 x 120 x 230"),
    T_50_X_80_X_110_a_200_CM("50 x 80 x 110 à 200"),
    T_36_X_46_X_70_a_120_CM("36 x 46 x 70 à 120"),
    AUTRE("Autre");

    private final String value;

    TaillesBottes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
