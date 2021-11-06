package org.lbstraw.map.domain.enumeration;

/**
 * The UsageBatiment enumeration.
 */
public enum UsageBatiment {
    LOGEMENT_COLLECTIF("Logement collectif"),
    LOGEMENT_INDIVIDUEL("Logement individuel"),
    LOGEMENT_INDIVIDUEL_GROUPE("Logement individuel groupé"),
    BATIMENT_ADMINISTRATIF("Bâtiment administratif"),
    BATIMENT_COMMERCIAL("Bâtiment commercial"),
    BATIMENT_INDUSTRIEL("Bâtiment industriel"),
    BATIMENT_DE_LOISIRS("Bâtiment de loisirs"),
    BATIMENT_DE_SANTE("Bâtiment de santé"),
    BATIMENT_DE_RETRAITE("Bâtiment de retraite"),
    BATIMENT_EDUCATIF("Bâtiment éducatif"),
    BATIMENT_SOCIO_CULTUREl("Bâtiment socio-culturel"),
    BATIMENT_AGRICOLE("Bâtiment agricole"),
    OUVRAGE_EXCEPTIONNEL("Ouvrage exceptionnel"),
    AUTRE("Autre");

    private final String value;

    UsageBatiment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
