package org.julioju.lbstrawmap.domain;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.julioju.lbstrawmap.domain.enumeration.Cereale;
import org.julioju.lbstrawmap.domain.enumeration.IntegBaie;
import org.julioju.lbstrawmap.domain.enumeration.RevetExt;
import org.julioju.lbstrawmap.domain.enumeration.RevetInt;
import org.julioju.lbstrawmap.domain.enumeration.StructureComplementaire;
import org.julioju.lbstrawmap.domain.enumeration.SupportAncrage;
import org.julioju.lbstrawmap.domain.enumeration.TaillesBottes;
import org.julioju.lbstrawmap.domain.enumeration.UsageBatiment;
import org.julioju.lbstrawmap.domain.enumeration.YesNoPartial;

/**
 * A Batiment.
 */
@Entity
@Table(name = "batiment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Batiment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @DecimalMin(value = "-90")
    @DecimalMax(value = "90")
    @Column(name = "latitude", nullable = false)
    private Float latitude;

    @NotNull
    @DecimalMin(value = "-90")
    @DecimalMax(value = "90")
    @Column(name = "longitude", nullable = false)
    private Float longitude;

    /**
     * Nom du bâtiment
     */
    @ApiModelProperty(value = "Nom du bâtiment")
    @Size(max = 40)
    @Column(name = "nom_batiment", length = 40)
    private String nomBatiment;

    @Lob
    @Column(name = "photo_principale")
    private byte[] photoPrincipale;

    @Column(name = "photo_principale_content_type")
    private String photoPrincipaleContentType;

    @Size(max = 30)
    @Column(name = "photo_principale_legende", length = 30)
    private String photoPrincipaleLegende;

    @Column(name = "photo_principale_description")
    private String photoPrincipaleDescription;

    @Lob
    @Column(name = "photo_1")
    private byte[] photo1;

    @Column(name = "photo_1_content_type")
    private String photo1ContentType;

    @Size(max = 30)
    @Column(name = "photo_1_legende", length = 30)
    private String photo1Legende;

    @Column(name = "photo_1_description")
    private String photo1Description;

    @Lob
    @Column(name = "photo_2")
    private byte[] photo2;

    @Column(name = "photo_2_content_type")
    private String photo2ContentType;

    @Size(max = 30)
    @Column(name = "photo_2_legende", length = 30)
    private String photo2Legende;

    @Column(name = "photo_2_description")
    private String photo2Description;

    @Lob
    @Column(name = "photo_3")
    private byte[] photo3;

    @Column(name = "photo_3_content_type")
    private String photo3ContentType;

    @Size(max = 30)
    @Column(name = "photo_3_legende", length = 30)
    private String photo3Legende;

    @Column(name = "photo_3_description")
    private String photo3Description;

    @Lob
    @Column(name = "photo_4")
    private byte[] photo4;

    @Column(name = "photo_4_content_type")
    private String photo4ContentType;

    @Size(max = 30)
    @Column(name = "photo_4_legende", length = 30)
    private String photo4Legende;

    @Column(name = "photo_4_description")
    private String photo4Description;

    @Lob
    @Column(name = "photo_5")
    private byte[] photo5;

    @Column(name = "photo_5_content_type")
    private String photo5ContentType;

    @Size(max = 30)
    @Column(name = "photo_5_legende", length = 30)
    private String photo5Legende;

    @Column(name = "photo_5_description")
    private String photo5Description;

    /**
     * If true, anonymous users (not authenticated) could see photos. Search\nengine could  index it.
     */
    @ApiModelProperty(value = "If true, anonymous users (not authenticated) could see photos. Search\nengine could  index it.")
    @Column(name = "nom_batiment_et_photos_publics")
    private Boolean nomBatimentEtPhotosPublics;

    /**
     * Usage
     */
    @ApiModelProperty(value = "Usage")
    @Enumerated(EnumType.STRING)
    @Column(name = "usage_batiment")
    private UsageBatiment usageBatiment;

    /**
     * Si \"Autre\", préciser
     */
    @ApiModelProperty(value = "Si \"Autre\", préciser")
    @Column(name = "usage_batiment_autre")
    private String usageBatimentAutre;

    /**
     * Coût [€]
     */
    @ApiModelProperty(value = "Coût [€]")
    @Column(name = "cout")
    private Integer cout;

    /**
     * Surface de plancher [m²]
     */
    @ApiModelProperty(value = "Surface de plancher [m²]")
    @Column(name = "surface_plancher")
    private Integer surfacePlancher;

    /**
     * Nombre de niveaux du bâtiment (ex: RDC = 1, 1 étage = 2 niveau, sous-sol non compté)
     */
    @ApiModelProperty(value = "Nombre de niveaux du bâtiment (ex: RDC = 1, 1 étage = 2 niveau, sous-sol non compté)")
    @Min(value = 1)
    @Column(name = "niveaux")
    private Integer niveaux;

    /**
     * Neuf
     */
    @ApiModelProperty(value = "Neuf")
    @Column(name = "travaux_neuf")
    private Boolean travauxNeuf;

    /**
     * Extension
     */
    @ApiModelProperty(value = "Extension")
    @Column(name = "travaux_extension")
    private Boolean travauxExtension;

    /**
     * Rénovation
     */
    @ApiModelProperty(value = "Rénovation")
    @Column(name = "travaux_renov")
    private Boolean travauxRenov;

    /**
     * Isolation thermique par l'extérieure
     */
    @ApiModelProperty(value = "Isolation thermique par l'extérieure")
    @Column(name = "travaux_ite")
    private Boolean travauxIte;

    /**
     * Isolation thermique par l'intérieur
     */
    @ApiModelProperty(value = "Isolation thermique par l'intérieur")
    @Column(name = "travaux_iti")
    private Boolean travauxIti;

    /**
     * Début de construction
     */
    @ApiModelProperty(value = "Début de construction")
    @Column(name = "construction_debut")
    private LocalDate constructionDebut;

    /**
     * Achèvement
     */
    @ApiModelProperty(value = "Achèvement")
    @Column(name = "construction_fin")
    private LocalDate constructionFin;

    /**
     * Taille des bottes
     */
    @ApiModelProperty(value = "Taille des bottes")
    @Enumerated(EnumType.STRING)
    @Column(name = "bottes_taille")
    private TaillesBottes bottesTaille;

    /**
     * Si \"Autre\", préciser
     */
    @ApiModelProperty(value = "Si \"Autre\", préciser")
    @Column(name = "botte_taille_autre")
    private String botteTailleAutre;

    /**
     * Densité sur base sèche [kg/m³]
     */
    @ApiModelProperty(value = "Densité sur base sèche [kg/m³]")
    @Column(name = "bottes_densite")
    private Integer bottesDensite;

    /**
     * Céréale
     */
    @ApiModelProperty(value = "Céréale")
    @Enumerated(EnumType.STRING)
    @Column(name = "bottes_cereale")
    private Cereale bottesCereale;

    /**
     * Distance d'approvisionnement [km]
     */
    @ApiModelProperty(value = "Distance d'approvisionnement [km]")
    @Column(name = "distance_appro")
    private Integer distanceAppro;

    /**
     * Autoconstruction
     */
    @ApiModelProperty(value = "Autoconstruction")
    @Enumerated(EnumType.STRING)
    @Column(name = "autoconstruction")
    private YesNoPartial autoconstruction;

    /**
     * Participatif
     */
    @ApiModelProperty(value = "Participatif")
    @Enumerated(EnumType.STRING)
    @Column(name = "participatif")
    private YesNoPartial participatif;

    /**
     * Structure complémentaire à la structure en paille porteuse (poteau, mur de refend, …)
     */
    @ApiModelProperty(value = "Structure complémentaire à la structure en paille porteuse (poteau, mur de refend, …)")
    @Column(name = "struct_compl")
    private Boolean structCompl;

    /**
     * Nature de la structure complémentaire
     */
    @ApiModelProperty(value = "Nature de la structure complémentaire")
    @Enumerated(EnumType.STRING)
    @Column(name = "struct_compl_nature")
    private StructureComplementaire structComplNature;

    /**
     * Si \"Autre\", préciser
     */
    @ApiModelProperty(value = "Si \"Autre\", préciser")
    @Size(max = 512)
    @Column(name = "struct_compl_autre", length = 512)
    private String structComplAutre;

    /**
     * Infos sur la structure complémentaire
     */
    @ApiModelProperty(value = "Infos sur la structure complémentaire")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "struct_compl_infos")
    private String structComplInfos;

    /**
     * Longueur maximum sans mur de refend (mètre)
     */
    @ApiModelProperty(value = "Longueur maximum sans mur de refend (mètre)")
    @Column(name = "long_max_sans_mur_refend")
    private Float longMaxSansMurRefend;

    /**
     * Note de calcul
     */
    @ApiModelProperty(value = "Note de calcul")
    @Column(name = "note_calcul")
    private Boolean noteCalcul;

    /**
     * Nombre de rangs de bottes
     */
    @ApiModelProperty(value = "Nombre de rangs de bottes")
    @Column(name = "nbr_rang_de_bottes")
    private Integer nbrRangDeBottes;

    /**
     * Intégration des baies
     */
    @ApiModelProperty(value = "Intégration des baies")
    @Enumerated(EnumType.STRING)
    @Column(name = "integ_baie")
    private IntegBaie integBaie;

    /**
     * Si \"Autre\", préciser
     */
    @ApiModelProperty(value = "Si \"Autre\", préciser")
    @Column(name = "integ_baie_autre")
    private String integBaieAutre;

    /**
     * Nature du support d'ancrage
     */
    @ApiModelProperty(value = "Nature du support d'ancrage")
    @Enumerated(EnumType.STRING)
    @Column(name = "support_ancrage")
    private SupportAncrage supportAncrage;

    /**
     * Si \"Autre\", préciser
     */
    @ApiModelProperty(value = "Si \"Autre\", préciser")
    @Column(name = "support_ancrage_autre")
    private String supportAncrageAutre;

    /**
     * Revêtement intérieur
     */
    @ApiModelProperty(value = "Revêtement intérieur")
    @Enumerated(EnumType.STRING)
    @Column(name = "revet_int")
    private RevetInt revetInt;

    /**
     * Si \"Autre\", préciser
     */
    @ApiModelProperty(value = "Si \"Autre\", préciser")
    @Column(name = "revet_int_autre")
    private String revetIntAutre;

    /**
     * Revêtement extérieur
     */
    @ApiModelProperty(value = "Revêtement extérieur")
    @Enumerated(EnumType.STRING)
    @Column(name = "revet_ext")
    private RevetExt revetExt;

    /**
     * Si \"Autre\", préciser
     */
    @ApiModelProperty(value = "Si \"Autre\", préciser")
    @Column(name = "revet_ext_autre")
    private String revetExtAutre;

    /**
     * Maître d'ouvrage
     */
    @ApiModelProperty(value = "Maître d'ouvrage")
    @Size(max = 512)
    @Column(name = "maitre_d_ouvrage", length = 512)
    private String maitreDOuvrage;

    /**
     * Maître d'œuvre
     */
    @ApiModelProperty(value = "Maître d'œuvre")
    @Size(max = 512)
    @Column(name = "maitre_d_oeuvre", length = 512)
    private String maitreDOeuvre;

    /**
     * Architecte
     */
    @ApiModelProperty(value = "Architecte")
    @Size(max = 512)
    @Column(name = "architecte", length = 512)
    private String architecte;

    /**
     * Bureau d'étude Structure
     */
    @ApiModelProperty(value = "Bureau d'étude Structure")
    @Size(max = 512)
    @Column(name = "bureau_d_etude_structure", length = 512)
    private String bureauDEtudeStructure;

    /**
     * Bureau contrôle
     */
    @ApiModelProperty(value = "Bureau contrôle")
    @Size(max = 512)
    @Column(name = "bureau_control", length = 512)
    private String bureauControl;

    /**
     * Entreprise de mise en oeuvre des bottes, si autoconstruction le préciser
     */
    @ApiModelProperty(value = "Entreprise de mise en oeuvre des bottes, si autoconstruction le préciser")
    @Size(max = 512)
    @Column(name = "entreprise_bottes", length = 512)
    private String entrepriseBottes;

    /**
     * Entreprise de mise en oeuvre de la charpente, si autoconstruction le préciser
     */
    @ApiModelProperty(value = "Entreprise de mise en oeuvre de la charpente, si autoconstruction le préciser")
    @Size(max = 512)
    @Column(name = "entreprise_charpente", length = 512)
    private String entrepriseCharpente;

    /**
     * Entreprise de mise en oeuvre des enduits, si autoconstruction le préciser
     */
    @ApiModelProperty(value = "Entreprise de mise en oeuvre des enduits, si autoconstruction le préciser")
    @Size(max = 512)
    @Column(name = "entreprise_enduits", length = 512)
    private String entrepriseEnduits;

    /**
     * Description du projet
     */
    @ApiModelProperty(value = "Description du projet")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description_projet")
    private String descriptionProjet;

    /**
     * Difficultés rencontrés
     */
    @ApiModelProperty(value = "Difficultés rencontrés")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "difficultees")
    private String difficultees;

    /**
     * Trucs et astuces
     */
    @ApiModelProperty(value = "Trucs et astuces")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "astuces")
    private String astuces;

    /**
     * Autre commentaires
     */
    @ApiModelProperty(value = "Autre commentaires")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "divers")
    private String divers;

    /**
     * Nom
     */
    @ApiModelProperty(value = "Nom")
    @Column(name = "contact_nom")
    private String contactNom;

    /**
     * Mail
     */
    @ApiModelProperty(value = "Mail")
    @Column(name = "contact_mail")
    private String contactMail;

    /**
     * Téléphone
     */
    @ApiModelProperty(value = "Téléphone")
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * Code postal
     */
    @ApiModelProperty(value = "Code postal")
    @Size(max = 6)
    @Column(name = "code_postal", length = 6)
    private String codePostal;

    /**
     * Date de création de l'enregistrement dans la Base de donnée\n(autogénéré en back)
     */
    @ApiModelProperty(value = "Date de création de l'enregistrement dans la Base de donnée\n(autogénéré en back)")
    // START added by JulioJu
    @Column(name = "created_date", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY)
    // END added by JulioJu
    private Instant createdDate;

    /**
     * Date de modification de l'enregistrement dans la Base de donnée\n(autogénéré en back)
     */
    @ApiModelProperty(value = "Date de modification de l'enregistrement dans la Base de donnée\n(autogénéré en back)")
    @Column(name = "last_modified_date")
    // START added by JulioJu
    @org.hibernate.annotations.UpdateTimestamp
    @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY)
    // END added by JulioJu
    private Instant lastModifiedDate;

    /**
     * Only createdBy of a Batiment could update or delete it (set in back at creation)
     */
    @ApiModelProperty(value = "Only createdBy of a Batiment could update or delete it (set in back at creation)")
    @ManyToOne(optional = false)
    // START added by JulioJu
    @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY)
    // END added by JulioJu
    private User createdBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Batiment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getLatitude() {
        return this.latitude;
    }

    public Batiment latitude(Float latitude) {
        this.setLatitude(latitude);
        return this;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return this.longitude;
    }

    public Batiment longitude(Float longitude) {
        this.setLongitude(longitude);
        return this;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getNomBatiment() {
        return this.nomBatiment;
    }

    public Batiment nomBatiment(String nomBatiment) {
        this.setNomBatiment(nomBatiment);
        return this;
    }

    public void setNomBatiment(String nomBatiment) {
        this.nomBatiment = nomBatiment;
    }

    public byte[] getPhotoPrincipale() {
        return this.photoPrincipale;
    }

    public Batiment photoPrincipale(byte[] photoPrincipale) {
        this.setPhotoPrincipale(photoPrincipale);
        return this;
    }

    public void setPhotoPrincipale(byte[] photoPrincipale) {
        this.photoPrincipale = photoPrincipale;
    }

    public String getPhotoPrincipaleContentType() {
        return this.photoPrincipaleContentType;
    }

    public Batiment photoPrincipaleContentType(String photoPrincipaleContentType) {
        this.photoPrincipaleContentType = photoPrincipaleContentType;
        return this;
    }

    public void setPhotoPrincipaleContentType(String photoPrincipaleContentType) {
        this.photoPrincipaleContentType = photoPrincipaleContentType;
    }

    public String getPhotoPrincipaleLegende() {
        return this.photoPrincipaleLegende;
    }

    public Batiment photoPrincipaleLegende(String photoPrincipaleLegende) {
        this.setPhotoPrincipaleLegende(photoPrincipaleLegende);
        return this;
    }

    public void setPhotoPrincipaleLegende(String photoPrincipaleLegende) {
        this.photoPrincipaleLegende = photoPrincipaleLegende;
    }

    public String getPhotoPrincipaleDescription() {
        return this.photoPrincipaleDescription;
    }

    public Batiment photoPrincipaleDescription(String photoPrincipaleDescription) {
        this.setPhotoPrincipaleDescription(photoPrincipaleDescription);
        return this;
    }

    public void setPhotoPrincipaleDescription(String photoPrincipaleDescription) {
        this.photoPrincipaleDescription = photoPrincipaleDescription;
    }

    public byte[] getPhoto1() {
        return this.photo1;
    }

    public Batiment photo1(byte[] photo1) {
        this.setPhoto1(photo1);
        return this;
    }

    public void setPhoto1(byte[] photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto1ContentType() {
        return this.photo1ContentType;
    }

    public Batiment photo1ContentType(String photo1ContentType) {
        this.photo1ContentType = photo1ContentType;
        return this;
    }

    public void setPhoto1ContentType(String photo1ContentType) {
        this.photo1ContentType = photo1ContentType;
    }

    public String getPhoto1Legende() {
        return this.photo1Legende;
    }

    public Batiment photo1Legende(String photo1Legende) {
        this.setPhoto1Legende(photo1Legende);
        return this;
    }

    public void setPhoto1Legende(String photo1Legende) {
        this.photo1Legende = photo1Legende;
    }

    public String getPhoto1Description() {
        return this.photo1Description;
    }

    public Batiment photo1Description(String photo1Description) {
        this.setPhoto1Description(photo1Description);
        return this;
    }

    public void setPhoto1Description(String photo1Description) {
        this.photo1Description = photo1Description;
    }

    public byte[] getPhoto2() {
        return this.photo2;
    }

    public Batiment photo2(byte[] photo2) {
        this.setPhoto2(photo2);
        return this;
    }

    public void setPhoto2(byte[] photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto2ContentType() {
        return this.photo2ContentType;
    }

    public Batiment photo2ContentType(String photo2ContentType) {
        this.photo2ContentType = photo2ContentType;
        return this;
    }

    public void setPhoto2ContentType(String photo2ContentType) {
        this.photo2ContentType = photo2ContentType;
    }

    public String getPhoto2Legende() {
        return this.photo2Legende;
    }

    public Batiment photo2Legende(String photo2Legende) {
        this.setPhoto2Legende(photo2Legende);
        return this;
    }

    public void setPhoto2Legende(String photo2Legende) {
        this.photo2Legende = photo2Legende;
    }

    public String getPhoto2Description() {
        return this.photo2Description;
    }

    public Batiment photo2Description(String photo2Description) {
        this.setPhoto2Description(photo2Description);
        return this;
    }

    public void setPhoto2Description(String photo2Description) {
        this.photo2Description = photo2Description;
    }

    public byte[] getPhoto3() {
        return this.photo3;
    }

    public Batiment photo3(byte[] photo3) {
        this.setPhoto3(photo3);
        return this;
    }

    public void setPhoto3(byte[] photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto3ContentType() {
        return this.photo3ContentType;
    }

    public Batiment photo3ContentType(String photo3ContentType) {
        this.photo3ContentType = photo3ContentType;
        return this;
    }

    public void setPhoto3ContentType(String photo3ContentType) {
        this.photo3ContentType = photo3ContentType;
    }

    public String getPhoto3Legende() {
        return this.photo3Legende;
    }

    public Batiment photo3Legende(String photo3Legende) {
        this.setPhoto3Legende(photo3Legende);
        return this;
    }

    public void setPhoto3Legende(String photo3Legende) {
        this.photo3Legende = photo3Legende;
    }

    public String getPhoto3Description() {
        return this.photo3Description;
    }

    public Batiment photo3Description(String photo3Description) {
        this.setPhoto3Description(photo3Description);
        return this;
    }

    public void setPhoto3Description(String photo3Description) {
        this.photo3Description = photo3Description;
    }

    public byte[] getPhoto4() {
        return this.photo4;
    }

    public Batiment photo4(byte[] photo4) {
        this.setPhoto4(photo4);
        return this;
    }

    public void setPhoto4(byte[] photo4) {
        this.photo4 = photo4;
    }

    public String getPhoto4ContentType() {
        return this.photo4ContentType;
    }

    public Batiment photo4ContentType(String photo4ContentType) {
        this.photo4ContentType = photo4ContentType;
        return this;
    }

    public void setPhoto4ContentType(String photo4ContentType) {
        this.photo4ContentType = photo4ContentType;
    }

    public String getPhoto4Legende() {
        return this.photo4Legende;
    }

    public Batiment photo4Legende(String photo4Legende) {
        this.setPhoto4Legende(photo4Legende);
        return this;
    }

    public void setPhoto4Legende(String photo4Legende) {
        this.photo4Legende = photo4Legende;
    }

    public String getPhoto4Description() {
        return this.photo4Description;
    }

    public Batiment photo4Description(String photo4Description) {
        this.setPhoto4Description(photo4Description);
        return this;
    }

    public void setPhoto4Description(String photo4Description) {
        this.photo4Description = photo4Description;
    }

    public byte[] getPhoto5() {
        return this.photo5;
    }

    public Batiment photo5(byte[] photo5) {
        this.setPhoto5(photo5);
        return this;
    }

    public void setPhoto5(byte[] photo5) {
        this.photo5 = photo5;
    }

    public String getPhoto5ContentType() {
        return this.photo5ContentType;
    }

    public Batiment photo5ContentType(String photo5ContentType) {
        this.photo5ContentType = photo5ContentType;
        return this;
    }

    public void setPhoto5ContentType(String photo5ContentType) {
        this.photo5ContentType = photo5ContentType;
    }

    public String getPhoto5Legende() {
        return this.photo5Legende;
    }

    public Batiment photo5Legende(String photo5Legende) {
        this.setPhoto5Legende(photo5Legende);
        return this;
    }

    public void setPhoto5Legende(String photo5Legende) {
        this.photo5Legende = photo5Legende;
    }

    public String getPhoto5Description() {
        return this.photo5Description;
    }

    public Batiment photo5Description(String photo5Description) {
        this.setPhoto5Description(photo5Description);
        return this;
    }

    public void setPhoto5Description(String photo5Description) {
        this.photo5Description = photo5Description;
    }

    public Boolean getNomBatimentEtPhotosPublics() {
        return this.nomBatimentEtPhotosPublics;
    }

    public Batiment nomBatimentEtPhotosPublics(Boolean nomBatimentEtPhotosPublics) {
        this.setNomBatimentEtPhotosPublics(nomBatimentEtPhotosPublics);
        return this;
    }

    public void setNomBatimentEtPhotosPublics(Boolean nomBatimentEtPhotosPublics) {
        this.nomBatimentEtPhotosPublics = nomBatimentEtPhotosPublics;
    }

    public UsageBatiment getUsageBatiment() {
        return this.usageBatiment;
    }

    public Batiment usageBatiment(UsageBatiment usageBatiment) {
        this.setUsageBatiment(usageBatiment);
        return this;
    }

    public void setUsageBatiment(UsageBatiment usageBatiment) {
        this.usageBatiment = usageBatiment;
    }

    public String getUsageBatimentAutre() {
        return this.usageBatimentAutre;
    }

    public Batiment usageBatimentAutre(String usageBatimentAutre) {
        this.setUsageBatimentAutre(usageBatimentAutre);
        return this;
    }

    public void setUsageBatimentAutre(String usageBatimentAutre) {
        this.usageBatimentAutre = usageBatimentAutre;
    }

    public Integer getCout() {
        return this.cout;
    }

    public Batiment cout(Integer cout) {
        this.setCout(cout);
        return this;
    }

    public void setCout(Integer cout) {
        this.cout = cout;
    }

    public Integer getSurfacePlancher() {
        return this.surfacePlancher;
    }

    public Batiment surfacePlancher(Integer surfacePlancher) {
        this.setSurfacePlancher(surfacePlancher);
        return this;
    }

    public void setSurfacePlancher(Integer surfacePlancher) {
        this.surfacePlancher = surfacePlancher;
    }

    public Integer getNiveaux() {
        return this.niveaux;
    }

    public Batiment niveaux(Integer niveaux) {
        this.setNiveaux(niveaux);
        return this;
    }

    public void setNiveaux(Integer niveaux) {
        this.niveaux = niveaux;
    }

    public Boolean getTravauxNeuf() {
        return this.travauxNeuf;
    }

    public Batiment travauxNeuf(Boolean travauxNeuf) {
        this.setTravauxNeuf(travauxNeuf);
        return this;
    }

    public void setTravauxNeuf(Boolean travauxNeuf) {
        this.travauxNeuf = travauxNeuf;
    }

    public Boolean getTravauxExtension() {
        return this.travauxExtension;
    }

    public Batiment travauxExtension(Boolean travauxExtension) {
        this.setTravauxExtension(travauxExtension);
        return this;
    }

    public void setTravauxExtension(Boolean travauxExtension) {
        this.travauxExtension = travauxExtension;
    }

    public Boolean getTravauxRenov() {
        return this.travauxRenov;
    }

    public Batiment travauxRenov(Boolean travauxRenov) {
        this.setTravauxRenov(travauxRenov);
        return this;
    }

    public void setTravauxRenov(Boolean travauxRenov) {
        this.travauxRenov = travauxRenov;
    }

    public Boolean getTravauxIte() {
        return this.travauxIte;
    }

    public Batiment travauxIte(Boolean travauxIte) {
        this.setTravauxIte(travauxIte);
        return this;
    }

    public void setTravauxIte(Boolean travauxIte) {
        this.travauxIte = travauxIte;
    }

    public Boolean getTravauxIti() {
        return this.travauxIti;
    }

    public Batiment travauxIti(Boolean travauxIti) {
        this.setTravauxIti(travauxIti);
        return this;
    }

    public void setTravauxIti(Boolean travauxIti) {
        this.travauxIti = travauxIti;
    }

    public LocalDate getConstructionDebut() {
        return this.constructionDebut;
    }

    public Batiment constructionDebut(LocalDate constructionDebut) {
        this.setConstructionDebut(constructionDebut);
        return this;
    }

    public void setConstructionDebut(LocalDate constructionDebut) {
        this.constructionDebut = constructionDebut;
    }

    public LocalDate getConstructionFin() {
        return this.constructionFin;
    }

    public Batiment constructionFin(LocalDate constructionFin) {
        this.setConstructionFin(constructionFin);
        return this;
    }

    public void setConstructionFin(LocalDate constructionFin) {
        this.constructionFin = constructionFin;
    }

    public TaillesBottes getBottesTaille() {
        return this.bottesTaille;
    }

    public Batiment bottesTaille(TaillesBottes bottesTaille) {
        this.setBottesTaille(bottesTaille);
        return this;
    }

    public void setBottesTaille(TaillesBottes bottesTaille) {
        this.bottesTaille = bottesTaille;
    }

    public String getBotteTailleAutre() {
        return this.botteTailleAutre;
    }

    public Batiment botteTailleAutre(String botteTailleAutre) {
        this.setBotteTailleAutre(botteTailleAutre);
        return this;
    }

    public void setBotteTailleAutre(String botteTailleAutre) {
        this.botteTailleAutre = botteTailleAutre;
    }

    public Integer getBottesDensite() {
        return this.bottesDensite;
    }

    public Batiment bottesDensite(Integer bottesDensite) {
        this.setBottesDensite(bottesDensite);
        return this;
    }

    public void setBottesDensite(Integer bottesDensite) {
        this.bottesDensite = bottesDensite;
    }

    public Cereale getBottesCereale() {
        return this.bottesCereale;
    }

    public Batiment bottesCereale(Cereale bottesCereale) {
        this.setBottesCereale(bottesCereale);
        return this;
    }

    public void setBottesCereale(Cereale bottesCereale) {
        this.bottesCereale = bottesCereale;
    }

    public Integer getDistanceAppro() {
        return this.distanceAppro;
    }

    public Batiment distanceAppro(Integer distanceAppro) {
        this.setDistanceAppro(distanceAppro);
        return this;
    }

    public void setDistanceAppro(Integer distanceAppro) {
        this.distanceAppro = distanceAppro;
    }

    public YesNoPartial getAutoconstruction() {
        return this.autoconstruction;
    }

    public Batiment autoconstruction(YesNoPartial autoconstruction) {
        this.setAutoconstruction(autoconstruction);
        return this;
    }

    public void setAutoconstruction(YesNoPartial autoconstruction) {
        this.autoconstruction = autoconstruction;
    }

    public YesNoPartial getParticipatif() {
        return this.participatif;
    }

    public Batiment participatif(YesNoPartial participatif) {
        this.setParticipatif(participatif);
        return this;
    }

    public void setParticipatif(YesNoPartial participatif) {
        this.participatif = participatif;
    }

    public Boolean getStructCompl() {
        return this.structCompl;
    }

    public Batiment structCompl(Boolean structCompl) {
        this.setStructCompl(structCompl);
        return this;
    }

    public void setStructCompl(Boolean structCompl) {
        this.structCompl = structCompl;
    }

    public StructureComplementaire getStructComplNature() {
        return this.structComplNature;
    }

    public Batiment structComplNature(StructureComplementaire structComplNature) {
        this.setStructComplNature(structComplNature);
        return this;
    }

    public void setStructComplNature(StructureComplementaire structComplNature) {
        this.structComplNature = structComplNature;
    }

    public String getStructComplAutre() {
        return this.structComplAutre;
    }

    public Batiment structComplAutre(String structComplAutre) {
        this.setStructComplAutre(structComplAutre);
        return this;
    }

    public void setStructComplAutre(String structComplAutre) {
        this.structComplAutre = structComplAutre;
    }

    public String getStructComplInfos() {
        return this.structComplInfos;
    }

    public Batiment structComplInfos(String structComplInfos) {
        this.setStructComplInfos(structComplInfos);
        return this;
    }

    public void setStructComplInfos(String structComplInfos) {
        this.structComplInfos = structComplInfos;
    }

    public Float getLongMaxSansMurRefend() {
        return this.longMaxSansMurRefend;
    }

    public Batiment longMaxSansMurRefend(Float longMaxSansMurRefend) {
        this.setLongMaxSansMurRefend(longMaxSansMurRefend);
        return this;
    }

    public void setLongMaxSansMurRefend(Float longMaxSansMurRefend) {
        this.longMaxSansMurRefend = longMaxSansMurRefend;
    }

    public Boolean getNoteCalcul() {
        return this.noteCalcul;
    }

    public Batiment noteCalcul(Boolean noteCalcul) {
        this.setNoteCalcul(noteCalcul);
        return this;
    }

    public void setNoteCalcul(Boolean noteCalcul) {
        this.noteCalcul = noteCalcul;
    }

    public Integer getNbrRangDeBottes() {
        return this.nbrRangDeBottes;
    }

    public Batiment nbrRangDeBottes(Integer nbrRangDeBottes) {
        this.setNbrRangDeBottes(nbrRangDeBottes);
        return this;
    }

    public void setNbrRangDeBottes(Integer nbrRangDeBottes) {
        this.nbrRangDeBottes = nbrRangDeBottes;
    }

    public IntegBaie getIntegBaie() {
        return this.integBaie;
    }

    public Batiment integBaie(IntegBaie integBaie) {
        this.setIntegBaie(integBaie);
        return this;
    }

    public void setIntegBaie(IntegBaie integBaie) {
        this.integBaie = integBaie;
    }

    public String getIntegBaieAutre() {
        return this.integBaieAutre;
    }

    public Batiment integBaieAutre(String integBaieAutre) {
        this.setIntegBaieAutre(integBaieAutre);
        return this;
    }

    public void setIntegBaieAutre(String integBaieAutre) {
        this.integBaieAutre = integBaieAutre;
    }

    public SupportAncrage getSupportAncrage() {
        return this.supportAncrage;
    }

    public Batiment supportAncrage(SupportAncrage supportAncrage) {
        this.setSupportAncrage(supportAncrage);
        return this;
    }

    public void setSupportAncrage(SupportAncrage supportAncrage) {
        this.supportAncrage = supportAncrage;
    }

    public String getSupportAncrageAutre() {
        return this.supportAncrageAutre;
    }

    public Batiment supportAncrageAutre(String supportAncrageAutre) {
        this.setSupportAncrageAutre(supportAncrageAutre);
        return this;
    }

    public void setSupportAncrageAutre(String supportAncrageAutre) {
        this.supportAncrageAutre = supportAncrageAutre;
    }

    public RevetInt getRevetInt() {
        return this.revetInt;
    }

    public Batiment revetInt(RevetInt revetInt) {
        this.setRevetInt(revetInt);
        return this;
    }

    public void setRevetInt(RevetInt revetInt) {
        this.revetInt = revetInt;
    }

    public String getRevetIntAutre() {
        return this.revetIntAutre;
    }

    public Batiment revetIntAutre(String revetIntAutre) {
        this.setRevetIntAutre(revetIntAutre);
        return this;
    }

    public void setRevetIntAutre(String revetIntAutre) {
        this.revetIntAutre = revetIntAutre;
    }

    public RevetExt getRevetExt() {
        return this.revetExt;
    }

    public Batiment revetExt(RevetExt revetExt) {
        this.setRevetExt(revetExt);
        return this;
    }

    public void setRevetExt(RevetExt revetExt) {
        this.revetExt = revetExt;
    }

    public String getRevetExtAutre() {
        return this.revetExtAutre;
    }

    public Batiment revetExtAutre(String revetExtAutre) {
        this.setRevetExtAutre(revetExtAutre);
        return this;
    }

    public void setRevetExtAutre(String revetExtAutre) {
        this.revetExtAutre = revetExtAutre;
    }

    public String getMaitreDOuvrage() {
        return this.maitreDOuvrage;
    }

    public Batiment maitreDOuvrage(String maitreDOuvrage) {
        this.setMaitreDOuvrage(maitreDOuvrage);
        return this;
    }

    public void setMaitreDOuvrage(String maitreDOuvrage) {
        this.maitreDOuvrage = maitreDOuvrage;
    }

    public String getMaitreDOeuvre() {
        return this.maitreDOeuvre;
    }

    public Batiment maitreDOeuvre(String maitreDOeuvre) {
        this.setMaitreDOeuvre(maitreDOeuvre);
        return this;
    }

    public void setMaitreDOeuvre(String maitreDOeuvre) {
        this.maitreDOeuvre = maitreDOeuvre;
    }

    public String getArchitecte() {
        return this.architecte;
    }

    public Batiment architecte(String architecte) {
        this.setArchitecte(architecte);
        return this;
    }

    public void setArchitecte(String architecte) {
        this.architecte = architecte;
    }

    public String getBureauDEtudeStructure() {
        return this.bureauDEtudeStructure;
    }

    public Batiment bureauDEtudeStructure(String bureauDEtudeStructure) {
        this.setBureauDEtudeStructure(bureauDEtudeStructure);
        return this;
    }

    public void setBureauDEtudeStructure(String bureauDEtudeStructure) {
        this.bureauDEtudeStructure = bureauDEtudeStructure;
    }

    public String getBureauControl() {
        return this.bureauControl;
    }

    public Batiment bureauControl(String bureauControl) {
        this.setBureauControl(bureauControl);
        return this;
    }

    public void setBureauControl(String bureauControl) {
        this.bureauControl = bureauControl;
    }

    public String getEntrepriseBottes() {
        return this.entrepriseBottes;
    }

    public Batiment entrepriseBottes(String entrepriseBottes) {
        this.setEntrepriseBottes(entrepriseBottes);
        return this;
    }

    public void setEntrepriseBottes(String entrepriseBottes) {
        this.entrepriseBottes = entrepriseBottes;
    }

    public String getEntrepriseCharpente() {
        return this.entrepriseCharpente;
    }

    public Batiment entrepriseCharpente(String entrepriseCharpente) {
        this.setEntrepriseCharpente(entrepriseCharpente);
        return this;
    }

    public void setEntrepriseCharpente(String entrepriseCharpente) {
        this.entrepriseCharpente = entrepriseCharpente;
    }

    public String getEntrepriseEnduits() {
        return this.entrepriseEnduits;
    }

    public Batiment entrepriseEnduits(String entrepriseEnduits) {
        this.setEntrepriseEnduits(entrepriseEnduits);
        return this;
    }

    public void setEntrepriseEnduits(String entrepriseEnduits) {
        this.entrepriseEnduits = entrepriseEnduits;
    }

    public String getDescriptionProjet() {
        return this.descriptionProjet;
    }

    public Batiment descriptionProjet(String descriptionProjet) {
        this.setDescriptionProjet(descriptionProjet);
        return this;
    }

    public void setDescriptionProjet(String descriptionProjet) {
        this.descriptionProjet = descriptionProjet;
    }

    public String getDifficultees() {
        return this.difficultees;
    }

    public Batiment difficultees(String difficultees) {
        this.setDifficultees(difficultees);
        return this;
    }

    public void setDifficultees(String difficultees) {
        this.difficultees = difficultees;
    }

    public String getAstuces() {
        return this.astuces;
    }

    public Batiment astuces(String astuces) {
        this.setAstuces(astuces);
        return this;
    }

    public void setAstuces(String astuces) {
        this.astuces = astuces;
    }

    public String getDivers() {
        return this.divers;
    }

    public Batiment divers(String divers) {
        this.setDivers(divers);
        return this;
    }

    public void setDivers(String divers) {
        this.divers = divers;
    }

    public String getContactNom() {
        return this.contactNom;
    }

    public Batiment contactNom(String contactNom) {
        this.setContactNom(contactNom);
        return this;
    }

    public void setContactNom(String contactNom) {
        this.contactNom = contactNom;
    }

    public String getContactMail() {
        return this.contactMail;
    }

    public Batiment contactMail(String contactMail) {
        this.setContactMail(contactMail);
        return this;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public Batiment contactPhone(String contactPhone) {
        this.setContactPhone(contactPhone);
        return this;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCodePostal() {
        return this.codePostal;
    }

    public Batiment codePostal(String codePostal) {
        this.setCodePostal(codePostal);
        return this;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public Batiment createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public Batiment lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public User getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(User user) {
        this.createdBy = user;
    }

    public Batiment createdBy(User user) {
        this.setCreatedBy(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Batiment)) {
            return false;
        }
        return id != null && id.equals(((Batiment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Batiment{" +
            "id=" + getId() +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", nomBatiment='" + getNomBatiment() + "'" +
            ", photoPrincipale='" + getPhotoPrincipale() + "'" +
            ", photoPrincipaleContentType='" + getPhotoPrincipaleContentType() + "'" +
            ", photoPrincipaleLegende='" + getPhotoPrincipaleLegende() + "'" +
            ", photoPrincipaleDescription='" + getPhotoPrincipaleDescription() + "'" +
            ", photo1='" + getPhoto1() + "'" +
            ", photo1ContentType='" + getPhoto1ContentType() + "'" +
            ", photo1Legende='" + getPhoto1Legende() + "'" +
            ", photo1Description='" + getPhoto1Description() + "'" +
            ", photo2='" + getPhoto2() + "'" +
            ", photo2ContentType='" + getPhoto2ContentType() + "'" +
            ", photo2Legende='" + getPhoto2Legende() + "'" +
            ", photo2Description='" + getPhoto2Description() + "'" +
            ", photo3='" + getPhoto3() + "'" +
            ", photo3ContentType='" + getPhoto3ContentType() + "'" +
            ", photo3Legende='" + getPhoto3Legende() + "'" +
            ", photo3Description='" + getPhoto3Description() + "'" +
            ", photo4='" + getPhoto4() + "'" +
            ", photo4ContentType='" + getPhoto4ContentType() + "'" +
            ", photo4Legende='" + getPhoto4Legende() + "'" +
            ", photo4Description='" + getPhoto4Description() + "'" +
            ", photo5='" + getPhoto5() + "'" +
            ", photo5ContentType='" + getPhoto5ContentType() + "'" +
            ", photo5Legende='" + getPhoto5Legende() + "'" +
            ", photo5Description='" + getPhoto5Description() + "'" +
            ", nomBatimentEtPhotosPublics='" + getNomBatimentEtPhotosPublics() + "'" +
            ", usageBatiment='" + getUsageBatiment() + "'" +
            ", usageBatimentAutre='" + getUsageBatimentAutre() + "'" +
            ", cout=" + getCout() +
            ", surfacePlancher=" + getSurfacePlancher() +
            ", niveaux=" + getNiveaux() +
            ", travauxNeuf='" + getTravauxNeuf() + "'" +
            ", travauxExtension='" + getTravauxExtension() + "'" +
            ", travauxRenov='" + getTravauxRenov() + "'" +
            ", travauxIte='" + getTravauxIte() + "'" +
            ", travauxIti='" + getTravauxIti() + "'" +
            ", constructionDebut='" + getConstructionDebut() + "'" +
            ", constructionFin='" + getConstructionFin() + "'" +
            ", bottesTaille='" + getBottesTaille() + "'" +
            ", botteTailleAutre='" + getBotteTailleAutre() + "'" +
            ", bottesDensite=" + getBottesDensite() +
            ", bottesCereale='" + getBottesCereale() + "'" +
            ", distanceAppro=" + getDistanceAppro() +
            ", autoconstruction='" + getAutoconstruction() + "'" +
            ", participatif='" + getParticipatif() + "'" +
            ", structCompl='" + getStructCompl() + "'" +
            ", structComplNature='" + getStructComplNature() + "'" +
            ", structComplAutre='" + getStructComplAutre() + "'" +
            ", structComplInfos='" + getStructComplInfos() + "'" +
            ", longMaxSansMurRefend=" + getLongMaxSansMurRefend() +
            ", noteCalcul='" + getNoteCalcul() + "'" +
            ", nbrRangDeBottes=" + getNbrRangDeBottes() +
            ", integBaie='" + getIntegBaie() + "'" +
            ", integBaieAutre='" + getIntegBaieAutre() + "'" +
            ", supportAncrage='" + getSupportAncrage() + "'" +
            ", supportAncrageAutre='" + getSupportAncrageAutre() + "'" +
            ", revetInt='" + getRevetInt() + "'" +
            ", revetIntAutre='" + getRevetIntAutre() + "'" +
            ", revetExt='" + getRevetExt() + "'" +
            ", revetExtAutre='" + getRevetExtAutre() + "'" +
            ", maitreDOuvrage='" + getMaitreDOuvrage() + "'" +
            ", maitreDOeuvre='" + getMaitreDOeuvre() + "'" +
            ", architecte='" + getArchitecte() + "'" +
            ", bureauDEtudeStructure='" + getBureauDEtudeStructure() + "'" +
            ", bureauControl='" + getBureauControl() + "'" +
            ", entrepriseBottes='" + getEntrepriseBottes() + "'" +
            ", entrepriseCharpente='" + getEntrepriseCharpente() + "'" +
            ", entrepriseEnduits='" + getEntrepriseEnduits() + "'" +
            ", descriptionProjet='" + getDescriptionProjet() + "'" +
            ", difficultees='" + getDifficultees() + "'" +
            ", astuces='" + getAstuces() + "'" +
            ", divers='" + getDivers() + "'" +
            ", contactNom='" + getContactNom() + "'" +
            ", contactMail='" + getContactMail() + "'" +
            ", contactPhone='" + getContactPhone() + "'" +
            ", codePostal='" + getCodePostal() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
