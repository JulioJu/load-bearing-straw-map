package org.julioju.lbstrawmap.domain.enumeration;

/**
 * Céréale
 */
public enum Cereale {
    BLE("Blé"),
    ORGE("Orge"),
    AVOINE("Avoine"),
    SEIGLE("Seigle"),
    TRITICALE("Triticale"),
    RIZ("Riz"),
    AUTRE("Autre");

    private final String value;

    Cereale(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
