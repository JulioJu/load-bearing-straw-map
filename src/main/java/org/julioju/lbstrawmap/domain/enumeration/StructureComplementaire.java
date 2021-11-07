package org.julioju.lbstrawmap.domain.enumeration;

/**
 * Nature support complémentaire
 */
public enum StructureComplementaire {
    BOIS("Bois"),
    BETON_ARME("Béton armé"),
    METAL("Métal"),
    MACONNERIE("Maçonnerie [brique - parpaing - pierre - …]"),
    AUTRE("Autre");

    private final String value;

    StructureComplementaire(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
