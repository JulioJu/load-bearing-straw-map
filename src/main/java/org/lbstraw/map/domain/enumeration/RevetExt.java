package org.lbstraw.map.domain.enumeration;

/**
 * Revêtement extérieur
 */
public enum RevetExt {
    BARDAGE_VENTILE("Bardage ventilé"),
    ENDUIT_TERRE("Enduit terre"),
    ENDUIT_CHAUX("Enduit chaux"),
    ENDUIT_TERRE_ET_CHAUX("Enduit terre et chaux"),
    ENDUIT_PLATRE("Enduit plâtre"),
    ENDUIT_PANNEAU("Panneau"),
    AUTRE("Autre");

    private final String value;

    RevetExt(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
