package org.lbstraw.map.domain.enumeration;

/**
 * Intégration des baies
 */
public enum IntegBaie {
    /**
     *
     *       * Pré-cadre flottant
     *
     */
    PRE_CADRE_FLOTTANT("Pré-cadre flottant"),
    /**
     *
     *       * Coulissant
     *
     */
    COULISSANT("Coulissant"),
    /**
     *
     *       * Bloqueurs
     *
     */
    BLOQUEURS("Bloqueurs"),
    /**
     *
     *       * Autre
     *
     */
    AUTRE("Autre");

    private final String value;

    IntegBaie(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
