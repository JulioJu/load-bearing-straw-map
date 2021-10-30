package org.lbstraw.map.domain.enumeration;

/**
 * Revêtement intérieur
 */
public enum RevetInt {
    /**
     *
     *       * Plaque de plâtre
     *
     */
    PLAQUE_DE_PLATRE("Plaque de plâtre"),
    /**
     *
     *       * Lambris
     *
     */
    LAMBRIS("Lambris"),
    /**
     *
     *       * Enduit terre
     *
     */
    ENDUIT_TERRE("Enduit terre"),
    /**
     *
     *       * Enduit chaux
     *
     */
    ENDUIT_CHAUX("Enduit chaux"),
    /**
     *
     *       * Enduit terre et chaux
     *
     */
    ENDUIT_TERRE_ET_CHAUX("Enduit terre et chaux"),
    /**
     *
     *       * Enduit plâtre
     *
     */
    ENDUIT_PLATRE("Enduit plâtre"),
    /**
     *
     *       * Autre
     *
     */
    AUTRE("Autre");

    private final String value;

    RevetInt(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
