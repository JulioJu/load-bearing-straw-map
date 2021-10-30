package org.lbstraw.map.domain.enumeration;

/**
 * Matériau de soubassement
 */
public enum MateriauSb {
    /**
     *
     *       * Béton armé
     *
     */
    BETON_ARME("Béton armé"),
    /**
     *
     *       * Parpaing de ciment
     *
     */
    PARPAING_DE_CIMENT("Parpaing de ciment"),
    /**
     *
     *       * Brique de terre cuite
     *
     */
    BRIQUE_DE_TERRE_CUITE("Brique de terre cuite"),
    /**
     *
     *       * Brique de pierre ponce
     *
     */
    BRIQUE_DE_PIERRE_PONCE("Brique de pierre ponce"),
    /**
     *
     *       * Béton léger isolant
     *
     */
    BETON_LEGER_ISOLANT("Béton léger isolant"),
    /**
     *
     *       * Pierre
     *
     */
    PIERRE("Pierre"),
    /**
     *
     *       * Autre
     *
     */
    AUTRE("Autre");

    private final String value;

    MateriauSb(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
