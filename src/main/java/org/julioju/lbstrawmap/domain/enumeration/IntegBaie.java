package org.julioju.lbstrawmap.domain.enumeration;

/**
 * Intégration des baies
 */
public enum IntegBaie {
    PRE_CADRE_FLOTTANT("Pré-cadre flottant"),
    COULISSANT("Élément coulissant"),
    FIXE("Élément fixe [poteau - montant - …]"),
    AUTRE("Autre");

    private final String value;

    IntegBaie(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
