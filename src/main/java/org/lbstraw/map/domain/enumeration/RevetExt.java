package org.lbstraw.map.domain.enumeration;

/**
 * Revêtement extérieur
 */
public enum RevetExt {
    /**
     *
     *       * Bardage ventilé
     *
     */
    BARDAGE_VENTILE("Bardage ventilé"),
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

    RevetExt(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
