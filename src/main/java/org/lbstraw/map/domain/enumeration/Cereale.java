package org.lbstraw.map.domain.enumeration;

/**
 * Céréale
 */
public enum Cereale {
    /**
     *
     *       * Blé
     *
     */
    BLE("Blé"),
    /**
     *
     *       * Orge
     *
     */
    ORGE("Orge"),
    /**
     *
     *       * Avoine
     *
     */
    AVOINE("Avoine"),
    /**
     *
     *       * Seigle
     *
     */
    SEIGLE("Seigle"),
    /**
     *
     *       * Triticale
     *
     */
    TRITICALE("Triticale"),
    /**
     *
     *       * Riz
     *
     */
    RIZ("Riz"),
    /**
     *
     *       * Autre
     *
     */
    AUTRE("Autre");

    private final String value;

    Cereale(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
