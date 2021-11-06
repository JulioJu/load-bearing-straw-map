package org.lbstraw.map.domain.enumeration;

/**
 * Nature support supplémentaire
 */
public enum StructureSupplementaire {
    BOIS("Bois"),
    BETON_ARME("Béton armé"),
    METAL("Métal"),
    MACONNERIE("Maçonnerie [brique - parpaing - pierre - …]");

    private final String value;

    StructureSupplementaire(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
