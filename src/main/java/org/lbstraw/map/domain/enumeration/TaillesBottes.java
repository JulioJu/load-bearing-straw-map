package org.lbstraw.map.domain.enumeration;

/**
 * Taille des bottes
 */
public enum TaillesBottes {
    /**
     *
     *       * 80 x 120 cm
     *
     */
    T_80_X_120_CM("80 x 120 cm"),
    /**
     *
     *       * 50 x 80 cm
     *
     */
    T_50_X_80_CM("50 x 80 cm"),
    /**
     *
     *       * 37 x 47 cm
     *
     */
    T_37_X_47_CM("37 x 47 cm"),
    /**
     *
     *       * 26 x 45 cm
     *
     */
    T_26_X_45_CM("26 x 45 cm"),
    /**
     *
     *       * Autre
     *
     */
    AUTRE("Autre");

    private final String value;

    TaillesBottes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
