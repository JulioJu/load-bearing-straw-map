package org.julioju.lbstrawmap.domain.enumeration;

/**
 * Revêtement intérieur
 */
public enum RevetInt {
    PLAQUE_DE_PLATRE("Plaque de plâtre"),
    LAMBRIS("Lambris"),
    ENDUIT_TERRE("Enduit terre"),
    ENDUIT_CHAUX("Enduit chaux"),
    ENDUIT_TERRE_ET_CHAUX("Enduit terre et chaux"),
    ENDUIT_PLATRE("Enduit plâtre"),
    AUTRE("Autre");

    private final String value;

    RevetInt(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
