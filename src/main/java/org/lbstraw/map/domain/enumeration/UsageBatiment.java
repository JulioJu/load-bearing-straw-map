package org.lbstraw.map.domain.enumeration;

/**
 * The UsageBatiment enumeration.
 */
public enum UsageBatiment {
    /**
     *
     *       * Logement collectif
     *
     */
    LOGEMENT_COLLECTIF("Logement collectif"),
    /**
     *
     *       * Logement individuel
     *
     */
    LOGEMENT_INDIVIDUEL("Logement individuel"),
    /**
     *
     *       * Logement individuel groupé
     *
     */
    LOGEMENT_INDIVIDUEL_GROUPE("Logement individuel groupé"),
    /**
     *
     *       * Bâtiment administratif
     *
     */
    BATIMENT_ADMINISTRATIF("Bâtiment administratif"),
    /**
     *
     *       * Bâtiment commercial
     *
     */
    BATIMENT_COMMERCIAL("Bâtiment commercial"),
    /**
     *
     *       * Bâtiment industriel
     *
     */
    BATIMENT_INDUSTRIEL("Bâtiment industriel"),
    /**
     *
     *       * Bâtiment de loisirs
     *
     */
    BATIMENT_DE_LOISIRS("Bâtiment de loisirs"),
    /**
     *
     *       * Bâtiment de santé
     *
     */
    BATIMENT_DE_SANTE("Bâtiment de santé"),
    /**
     *
     *       * Bâtiment de retraite
     *
     */
    BATIMENT_DE_RETRAITE("Bâtiment de retraite"),
    /**
     *
     *       * Bâtiment éducatif
     *
     */
    BATIMENT_EDUCATIF("Bâtiment éducatif"),
    /**
     *
     *       * Bâtiment socio-culturel
     *
     */
    BATIMENT_SOCIO_CULTUREl("Bâtiment socio-culturel"),
    /**
     *
     *       * Bâtiment agricole
     *
     */
    BATIMENT_AGRICOLE("Bâtiment agricole"),
    /**
     *
     *       * Ouvrage exceptionnel
     *
     */
    OUVRAGE_EXCEPTIONNEL("Ouvrage exceptionnel"),
    /**
     *
     *       * Autre
     *
     */
    AUTRE("Autre");

    private final String value;

    UsageBatiment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
