package org.lbstraw.map.domain.enumeration;

/**
 * Nature du support d&#39;ancrage
 */
public enum SupportAncrage {
    BOIS("Bois"),
    BETON_ARME("Béton armé"),
    METAL("Métal"),
    MACONNERIE("Maçonnerie [brique - parpaing - pierre - …]"),
    AUTRE("Autre");

    private final String value;

    SupportAncrage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
