package org.lbstraw.map.domain;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.lbstraw.map.domain.enumeration.Cereale;
import org.lbstraw.map.domain.enumeration.IntegBaie;
import org.lbstraw.map.domain.enumeration.RevetExt;
import org.lbstraw.map.domain.enumeration.RevetInt;
import org.lbstraw.map.domain.enumeration.StructureSupplementaire;
import org.lbstraw.map.domain.enumeration.SupportAncrage;
import org.lbstraw.map.domain.enumeration.TaillesBottes;
import org.lbstraw.map.domain.enumeration.UsageBatiment;
import org.lbstraw.map.domain.enumeration.YesNoPartial;

/**
 * A Batiments.
 */
@Entity
@Table(name = "batiments")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Batiments implements Serializable {

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

    @Size(max = 50)
    @Column(name = "photo_principale_legende", length = 50)
    private String photoPrincipaleLegende;

    @Column(name = "photo_principale_description")
    private String photoPrincipaleDescription;

    @Lob
    @Column(name = "photo_1")
    private byte[] photo1;

    @Column(name = "photo_1_content_type")
    private String photo1ContentType;

    @Size(max = 50)
    @Column(name = "photo_1_legende", length = 50)
    private String photo1Legende;

    @Column(name = "photo_1_description")
    private String photo1Description;

    @Lob
    @Column(name = "photo_2")
    private byte[] photo2;

    @Column(name = "photo_2_content_type")
    private String photo2ContentType;

    @Size(max = 50)
    @Column(name = "photo_2_legende", length = 50)
    private String photo2Legende;

    @Column(name = "photo_2_description")
    private String photo2Description;

    @Lob
    @Column(name = "photo_3")
    private byte[] photo3;

    @Column(name = "photo_3_content_type")
    private String photo3ContentType;

    @Size(max = 50)
    @Column(name = "photo_3_legende", length = 50)
    private String photo3Legende;

    @Column(name = "photo_3_description")
    private String photo3Description;

    @Lob
    @Column(name = "photo_4")
    private byte[] photo4;

    @Column(name = "photo_4_content_type")
    private String photo4ContentType;

    @Size(max = 50)
    @Column(name = "photo_4_legende", length = 50)
    private String photo4Legende;

    @Column(name = "photo_4_description")
    private String photo4Description;

    @Lob
    @Column(name = "photo_5")
    private byte[] photo5;

    @Column(name = "photo_5_content_type")
    private String photo5ContentType;

    @Size(max = 50)
    @Column(name = "photo_5_legende", length = 50)
    private String photo5Legende;

    @Column(name = "photo_5_description")
    private String photo5Description;

    /**
     * Paille porteuse en technique secondaire
     */
    @ApiModelProperty(value = "Paille porteuse en technique secondaire")
    @Column(name = "technique_secondaire")
    private Boolean techniqueSecondaire;

    /**
     * Usage
     */
    @ApiModelProperty(value = "Usage")
    @Enumerated(EnumType.STRING)
    @Column(name = "usage_batiment")
    private UsageBatiment usageBatiment;

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
     * Nombre de niveaux du bâtiment
     */
    @ApiModelProperty(value = "Nombre de niveaux du bâtiment")
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
     * Si taille des bottes non standard
     */
    @ApiModelProperty(value = "Si taille des bottes non standard")
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
     * Structure supplémentaire
     */
    @ApiModelProperty(value = "Structure supplémentaire")
    @Column(name = "struct_suppl")
    private Boolean structSuppl;

    /**
     * Nature Structure supplémentaire
     */
    @ApiModelProperty(value = "Nature Structure supplémentaire")
    @Enumerated(EnumType.STRING)
    @Column(name = "struct_suppl_nature")
    private StructureSupplementaire structSupplNature;

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
     * Longueur maximum sans mur de refend (mètre)
     */
    @ApiModelProperty(value = "Longueur maximum sans mur de refend (mètre)")
    @Column(name = "long_max_sans_mur_refend")
    private Float longMaxSansMurRefend;

    /**
     * Intégration des baies
     */
    @ApiModelProperty(value = "Intégration des baies")
    @Enumerated(EnumType.STRING)
    @Column(name = "integ_baie")
    private IntegBaie integBaie;

    /**
     * Nature du support d'ancrage
     */
    @ApiModelProperty(value = "Nature du support d'ancrage")
    @Enumerated(EnumType.STRING)
    @Column(name = "support_ancrage")
    private SupportAncrage supportAncrage;

    /**
     * Nature du support d'ancrage précisions
     */
    @ApiModelProperty(value = "Nature du support d'ancrage précisions")
    @Column(name = "support_ancrage_precisions")
    private String supportAncragePrecisions;

    /**
     * Revêtement intérieur
     */
    @ApiModelProperty(value = "Revêtement intérieur")
    @Enumerated(EnumType.STRING)
    @Column(name = "revet_int")
    private RevetInt revetInt;

    /**
     * Revêtement extérieur
     */
    @ApiModelProperty(value = "Revêtement extérieur")
    @Enumerated(EnumType.STRING)
    @Column(name = "revet_ext")
    private RevetExt revetExt;

    /**
     * Revêtement extérieur autre
     */
    @ApiModelProperty(value = "Revêtement extérieur autre")
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
     * If true, anonymous users (not authenticated) could see photos. Search\nengine could  index it.
     */
    @ApiModelProperty(value = "If true, anonymous users (not authenticated) could see photos. Search\nengine could  index it.")
    @Column(name = "non_batiment_et_photos_publics")
    private Boolean nonBatimentEtPhotosPublics;

    /**
     * Date de création de l'enregistrement dans la Base de donnée\n(autogénéré en back)
     */
    @ApiModelProperty(value = "Date de création de l'enregistrement dans la Base de donnée\n(autogénéré en back)", required = true)
    @NotNull
    @Column(name = "date_creation_fiche", nullable = false)
    private LocalDate dateCreationFiche;

    /**
     * Date de modification de l'enregistrement dans la Base de donnée\n(autogénéré en back)
     */
    @ApiModelProperty(value = "Date de modification de l'enregistrement dans la Base de donnée\n(autogénéré en back)", required = true)
    @NotNull
    @Column(name = "date_modification_fiche", nullable = false)
    private LocalDate dateModificationFiche;

    /**
     * Only creator (set in back at creation) of a Batiment could update or delete it
     */
    @ApiModelProperty(value = "Only creator (set in back at creation) of a Batiment could update or delete it")
    @ManyToOne
    private User creator;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Batiments id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getLatitude() {
        return this.latitude;
    }

    public Batiments latitude(Float latitude) {
        this.setLatitude(latitude);
        return this;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return this.longitude;
    }

    public Batiments longitude(Float longitude) {
        this.setLongitude(longitude);
        return this;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getNomBatiment() {
        return this.nomBatiment;
    }

    public Batiments nomBatiment(String nomBatiment) {
        this.setNomBatiment(nomBatiment);
        return this;
    }

    public void setNomBatiment(String nomBatiment) {
        this.nomBatiment = nomBatiment;
    }

    public byte[] getPhotoPrincipale() {
        return this.photoPrincipale;
    }

    public Batiments photoPrincipale(byte[] photoPrincipale) {
        this.setPhotoPrincipale(photoPrincipale);
        return this;
    }

    public void setPhotoPrincipale(byte[] photoPrincipale) {
        this.photoPrincipale = photoPrincipale;
    }

    public String getPhotoPrincipaleContentType() {
        return this.photoPrincipaleContentType;
    }

    public Batiments photoPrincipaleContentType(String photoPrincipaleContentType) {
        this.photoPrincipaleContentType = photoPrincipaleContentType;
        return this;
    }

    public void setPhotoPrincipaleContentType(String photoPrincipaleContentType) {
        this.photoPrincipaleContentType = photoPrincipaleContentType;
    }

    public String getPhotoPrincipaleLegende() {
        return this.photoPrincipaleLegende;
    }

    public Batiments photoPrincipaleLegende(String photoPrincipaleLegende) {
        this.setPhotoPrincipaleLegende(photoPrincipaleLegende);
        return this;
    }

    public void setPhotoPrincipaleLegende(String photoPrincipaleLegende) {
        this.photoPrincipaleLegende = photoPrincipaleLegende;
    }

    public String getPhotoPrincipaleDescription() {
        return this.photoPrincipaleDescription;
    }

    public Batiments photoPrincipaleDescription(String photoPrincipaleDescription) {
        this.setPhotoPrincipaleDescription(photoPrincipaleDescription);
        return this;
    }

    public void setPhotoPrincipaleDescription(String photoPrincipaleDescription) {
        this.photoPrincipaleDescription = photoPrincipaleDescription;
    }

    public byte[] getPhoto1() {
        return this.photo1;
    }

    public Batiments photo1(byte[] photo1) {
        this.setPhoto1(photo1);
        return this;
    }

    public void setPhoto1(byte[] photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto1ContentType() {
        return this.photo1ContentType;
    }

    public Batiments photo1ContentType(String photo1ContentType) {
        this.photo1ContentType = photo1ContentType;
        return this;
    }

    public void setPhoto1ContentType(String photo1ContentType) {
        this.photo1ContentType = photo1ContentType;
    }

    public String getPhoto1Legende() {
        return this.photo1Legende;
    }

    public Batiments photo1Legende(String photo1Legende) {
        this.setPhoto1Legende(photo1Legende);
        return this;
    }

    public void setPhoto1Legende(String photo1Legende) {
        this.photo1Legende = photo1Legende;
    }

    public String getPhoto1Description() {
        return this.photo1Description;
    }

    public Batiments photo1Description(String photo1Description) {
        this.setPhoto1Description(photo1Description);
        return this;
    }

    public void setPhoto1Description(String photo1Description) {
        this.photo1Description = photo1Description;
    }

    public byte[] getPhoto2() {
        return this.photo2;
    }

    public Batiments photo2(byte[] photo2) {
        this.setPhoto2(photo2);
        return this;
    }

    public void setPhoto2(byte[] photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto2ContentType() {
        return this.photo2ContentType;
    }

    public Batiments photo2ContentType(String photo2ContentType) {
        this.photo2ContentType = photo2ContentType;
        return this;
    }

    public void setPhoto2ContentType(String photo2ContentType) {
        this.photo2ContentType = photo2ContentType;
    }

    public String getPhoto2Legende() {
        return this.photo2Legende;
    }

    public Batiments photo2Legende(String photo2Legende) {
        this.setPhoto2Legende(photo2Legende);
        return this;
    }

    public void setPhoto2Legende(String photo2Legende) {
        this.photo2Legende = photo2Legende;
    }

    public String getPhoto2Description() {
        return this.photo2Description;
    }

    public Batiments photo2Description(String photo2Description) {
        this.setPhoto2Description(photo2Description);
        return this;
    }

    public void setPhoto2Description(String photo2Description) {
        this.photo2Description = photo2Description;
    }

    public byte[] getPhoto3() {
        return this.photo3;
    }

    public Batiments photo3(byte[] photo3) {
        this.setPhoto3(photo3);
        return this;
    }

    public void setPhoto3(byte[] photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto3ContentType() {
        return this.photo3ContentType;
    }

    public Batiments photo3ContentType(String photo3ContentType) {
        this.photo3ContentType = photo3ContentType;
        return this;
    }

    public void setPhoto3ContentType(String photo3ContentType) {
        this.photo3ContentType = photo3ContentType;
    }

    public String getPhoto3Legende() {
        return this.photo3Legende;
    }

    public Batiments photo3Legende(String photo3Legende) {
        this.setPhoto3Legende(photo3Legende);
        return this;
    }

    public void setPhoto3Legende(String photo3Legende) {
        this.photo3Legende = photo3Legende;
    }

    public String getPhoto3Description() {
        return this.photo3Description;
    }

    public Batiments photo3Description(String photo3Description) {
        this.setPhoto3Description(photo3Description);
        return this;
    }

    public void setPhoto3Description(String photo3Description) {
        this.photo3Description = photo3Description;
    }

    public byte[] getPhoto4() {
        return this.photo4;
    }

    public Batiments photo4(byte[] photo4) {
        this.setPhoto4(photo4);
        return this;
    }

    public void setPhoto4(byte[] photo4) {
        this.photo4 = photo4;
    }

    public String getPhoto4ContentType() {
        return this.photo4ContentType;
    }

    public Batiments photo4ContentType(String photo4ContentType) {
        this.photo4ContentType = photo4ContentType;
        return this;
    }

    public void setPhoto4ContentType(String photo4ContentType) {
        this.photo4ContentType = photo4ContentType;
    }

    public String getPhoto4Legende() {
        return this.photo4Legende;
    }

    public Batiments photo4Legende(String photo4Legende) {
        this.setPhoto4Legende(photo4Legende);
        return this;
    }

    public void setPhoto4Legende(String photo4Legende) {
        this.photo4Legende = photo4Legende;
    }

    public String getPhoto4Description() {
        return this.photo4Description;
    }

    public Batiments photo4Description(String photo4Description) {
        this.setPhoto4Description(photo4Description);
        return this;
    }

    public void setPhoto4Description(String photo4Description) {
        this.photo4Description = photo4Description;
    }

    public byte[] getPhoto5() {
        return this.photo5;
    }

    public Batiments photo5(byte[] photo5) {
        this.setPhoto5(photo5);
        return this;
    }

    public void setPhoto5(byte[] photo5) {
        this.photo5 = photo5;
    }

    public String getPhoto5ContentType() {
        return this.photo5ContentType;
    }

    public Batiments photo5ContentType(String photo5ContentType) {
        this.photo5ContentType = photo5ContentType;
        return this;
    }

    public void setPhoto5ContentType(String photo5ContentType) {
        this.photo5ContentType = photo5ContentType;
    }

    public String getPhoto5Legende() {
        return this.photo5Legende;
    }

    public Batiments photo5Legende(String photo5Legende) {
        this.setPhoto5Legende(photo5Legende);
        return this;
    }

    public void setPhoto5Legende(String photo5Legende) {
        this.photo5Legende = photo5Legende;
    }

    public String getPhoto5Description() {
        return this.photo5Description;
    }

    public Batiments photo5Description(String photo5Description) {
        this.setPhoto5Description(photo5Description);
        return this;
    }

    public void setPhoto5Description(String photo5Description) {
        this.photo5Description = photo5Description;
    }

    public Boolean getTechniqueSecondaire() {
        return this.techniqueSecondaire;
    }

    public Batiments techniqueSecondaire(Boolean techniqueSecondaire) {
        this.setTechniqueSecondaire(techniqueSecondaire);
        return this;
    }

    public void setTechniqueSecondaire(Boolean techniqueSecondaire) {
        this.techniqueSecondaire = techniqueSecondaire;
    }

    public UsageBatiment getUsageBatiment() {
        return this.usageBatiment;
    }

    public Batiments usageBatiment(UsageBatiment usageBatiment) {
        this.setUsageBatiment(usageBatiment);
        return this;
    }

    public void setUsageBatiment(UsageBatiment usageBatiment) {
        this.usageBatiment = usageBatiment;
    }

    public Integer getCout() {
        return this.cout;
    }

    public Batiments cout(Integer cout) {
        this.setCout(cout);
        return this;
    }

    public void setCout(Integer cout) {
        this.cout = cout;
    }

    public Integer getSurfacePlancher() {
        return this.surfacePlancher;
    }

    public Batiments surfacePlancher(Integer surfacePlancher) {
        this.setSurfacePlancher(surfacePlancher);
        return this;
    }

    public void setSurfacePlancher(Integer surfacePlancher) {
        this.surfacePlancher = surfacePlancher;
    }

    public Integer getNiveaux() {
        return this.niveaux;
    }

    public Batiments niveaux(Integer niveaux) {
        this.setNiveaux(niveaux);
        return this;
    }

    public void setNiveaux(Integer niveaux) {
        this.niveaux = niveaux;
    }

    public Boolean getTravauxNeuf() {
        return this.travauxNeuf;
    }

    public Batiments travauxNeuf(Boolean travauxNeuf) {
        this.setTravauxNeuf(travauxNeuf);
        return this;
    }

    public void setTravauxNeuf(Boolean travauxNeuf) {
        this.travauxNeuf = travauxNeuf;
    }

    public Boolean getTravauxExtension() {
        return this.travauxExtension;
    }

    public Batiments travauxExtension(Boolean travauxExtension) {
        this.setTravauxExtension(travauxExtension);
        return this;
    }

    public void setTravauxExtension(Boolean travauxExtension) {
        this.travauxExtension = travauxExtension;
    }

    public Boolean getTravauxRenov() {
        return this.travauxRenov;
    }

    public Batiments travauxRenov(Boolean travauxRenov) {
        this.setTravauxRenov(travauxRenov);
        return this;
    }

    public void setTravauxRenov(Boolean travauxRenov) {
        this.travauxRenov = travauxRenov;
    }

    public Boolean getTravauxIte() {
        return this.travauxIte;
    }

    public Batiments travauxIte(Boolean travauxIte) {
        this.setTravauxIte(travauxIte);
        return this;
    }

    public void setTravauxIte(Boolean travauxIte) {
        this.travauxIte = travauxIte;
    }

    public Boolean getTravauxIti() {
        return this.travauxIti;
    }

    public Batiments travauxIti(Boolean travauxIti) {
        this.setTravauxIti(travauxIti);
        return this;
    }

    public void setTravauxIti(Boolean travauxIti) {
        this.travauxIti = travauxIti;
    }

    public LocalDate getConstructionDebut() {
        return this.constructionDebut;
    }

    public Batiments constructionDebut(LocalDate constructionDebut) {
        this.setConstructionDebut(constructionDebut);
        return this;
    }

    public void setConstructionDebut(LocalDate constructionDebut) {
        this.constructionDebut = constructionDebut;
    }

    public LocalDate getConstructionFin() {
        return this.constructionFin;
    }

    public Batiments constructionFin(LocalDate constructionFin) {
        this.setConstructionFin(constructionFin);
        return this;
    }

    public void setConstructionFin(LocalDate constructionFin) {
        this.constructionFin = constructionFin;
    }

    public TaillesBottes getBottesTaille() {
        return this.bottesTaille;
    }

    public Batiments bottesTaille(TaillesBottes bottesTaille) {
        this.setBottesTaille(bottesTaille);
        return this;
    }

    public void setBottesTaille(TaillesBottes bottesTaille) {
        this.bottesTaille = bottesTaille;
    }

    public String getBotteTailleAutre() {
        return this.botteTailleAutre;
    }

    public Batiments botteTailleAutre(String botteTailleAutre) {
        this.setBotteTailleAutre(botteTailleAutre);
        return this;
    }

    public void setBotteTailleAutre(String botteTailleAutre) {
        this.botteTailleAutre = botteTailleAutre;
    }

    public Integer getBottesDensite() {
        return this.bottesDensite;
    }

    public Batiments bottesDensite(Integer bottesDensite) {
        this.setBottesDensite(bottesDensite);
        return this;
    }

    public void setBottesDensite(Integer bottesDensite) {
        this.bottesDensite = bottesDensite;
    }

    public Cereale getBottesCereale() {
        return this.bottesCereale;
    }

    public Batiments bottesCereale(Cereale bottesCereale) {
        this.setBottesCereale(bottesCereale);
        return this;
    }

    public void setBottesCereale(Cereale bottesCereale) {
        this.bottesCereale = bottesCereale;
    }

    public Integer getDistanceAppro() {
        return this.distanceAppro;
    }

    public Batiments distanceAppro(Integer distanceAppro) {
        this.setDistanceAppro(distanceAppro);
        return this;
    }

    public void setDistanceAppro(Integer distanceAppro) {
        this.distanceAppro = distanceAppro;
    }

    public YesNoPartial getAutoconstruction() {
        return this.autoconstruction;
    }

    public Batiments autoconstruction(YesNoPartial autoconstruction) {
        this.setAutoconstruction(autoconstruction);
        return this;
    }

    public void setAutoconstruction(YesNoPartial autoconstruction) {
        this.autoconstruction = autoconstruction;
    }

    public YesNoPartial getParticipatif() {
        return this.participatif;
    }

    public Batiments participatif(YesNoPartial participatif) {
        this.setParticipatif(participatif);
        return this;
    }

    public void setParticipatif(YesNoPartial participatif) {
        this.participatif = participatif;
    }

    public Boolean getStructSuppl() {
        return this.structSuppl;
    }

    public Batiments structSuppl(Boolean structSuppl) {
        this.setStructSuppl(structSuppl);
        return this;
    }

    public void setStructSuppl(Boolean structSuppl) {
        this.structSuppl = structSuppl;
    }

    public StructureSupplementaire getStructSupplNature() {
        return this.structSupplNature;
    }

    public Batiments structSupplNature(StructureSupplementaire structSupplNature) {
        this.setStructSupplNature(structSupplNature);
        return this;
    }

    public void setStructSupplNature(StructureSupplementaire structSupplNature) {
        this.structSupplNature = structSupplNature;
    }

    public Boolean getNoteCalcul() {
        return this.noteCalcul;
    }

    public Batiments noteCalcul(Boolean noteCalcul) {
        this.setNoteCalcul(noteCalcul);
        return this;
    }

    public void setNoteCalcul(Boolean noteCalcul) {
        this.noteCalcul = noteCalcul;
    }

    public Integer getNbrRangDeBottes() {
        return this.nbrRangDeBottes;
    }

    public Batiments nbrRangDeBottes(Integer nbrRangDeBottes) {
        this.setNbrRangDeBottes(nbrRangDeBottes);
        return this;
    }

    public void setNbrRangDeBottes(Integer nbrRangDeBottes) {
        this.nbrRangDeBottes = nbrRangDeBottes;
    }

    public Float getLongMaxSansMurRefend() {
        return this.longMaxSansMurRefend;
    }

    public Batiments longMaxSansMurRefend(Float longMaxSansMurRefend) {
        this.setLongMaxSansMurRefend(longMaxSansMurRefend);
        return this;
    }

    public void setLongMaxSansMurRefend(Float longMaxSansMurRefend) {
        this.longMaxSansMurRefend = longMaxSansMurRefend;
    }

    public IntegBaie getIntegBaie() {
        return this.integBaie;
    }

    public Batiments integBaie(IntegBaie integBaie) {
        this.setIntegBaie(integBaie);
        return this;
    }

    public void setIntegBaie(IntegBaie integBaie) {
        this.integBaie = integBaie;
    }

    public SupportAncrage getSupportAncrage() {
        return this.supportAncrage;
    }

    public Batiments supportAncrage(SupportAncrage supportAncrage) {
        this.setSupportAncrage(supportAncrage);
        return this;
    }

    public void setSupportAncrage(SupportAncrage supportAncrage) {
        this.supportAncrage = supportAncrage;
    }

    public String getSupportAncragePrecisions() {
        return this.supportAncragePrecisions;
    }

    public Batiments supportAncragePrecisions(String supportAncragePrecisions) {
        this.setSupportAncragePrecisions(supportAncragePrecisions);
        return this;
    }

    public void setSupportAncragePrecisions(String supportAncragePrecisions) {
        this.supportAncragePrecisions = supportAncragePrecisions;
    }

    public RevetInt getRevetInt() {
        return this.revetInt;
    }

    public Batiments revetInt(RevetInt revetInt) {
        this.setRevetInt(revetInt);
        return this;
    }

    public void setRevetInt(RevetInt revetInt) {
        this.revetInt = revetInt;
    }

    public RevetExt getRevetExt() {
        return this.revetExt;
    }

    public Batiments revetExt(RevetExt revetExt) {
        this.setRevetExt(revetExt);
        return this;
    }

    public void setRevetExt(RevetExt revetExt) {
        this.revetExt = revetExt;
    }

    public String getRevetExtAutre() {
        return this.revetExtAutre;
    }

    public Batiments revetExtAutre(String revetExtAutre) {
        this.setRevetExtAutre(revetExtAutre);
        return this;
    }

    public void setRevetExtAutre(String revetExtAutre) {
        this.revetExtAutre = revetExtAutre;
    }

    public String getMaitreDOuvrage() {
        return this.maitreDOuvrage;
    }

    public Batiments maitreDOuvrage(String maitreDOuvrage) {
        this.setMaitreDOuvrage(maitreDOuvrage);
        return this;
    }

    public void setMaitreDOuvrage(String maitreDOuvrage) {
        this.maitreDOuvrage = maitreDOuvrage;
    }

    public String getMaitreDOeuvre() {
        return this.maitreDOeuvre;
    }

    public Batiments maitreDOeuvre(String maitreDOeuvre) {
        this.setMaitreDOeuvre(maitreDOeuvre);
        return this;
    }

    public void setMaitreDOeuvre(String maitreDOeuvre) {
        this.maitreDOeuvre = maitreDOeuvre;
    }

    public String getArchitecte() {
        return this.architecte;
    }

    public Batiments architecte(String architecte) {
        this.setArchitecte(architecte);
        return this;
    }

    public void setArchitecte(String architecte) {
        this.architecte = architecte;
    }

    public String getBureauDEtudeStructure() {
        return this.bureauDEtudeStructure;
    }

    public Batiments bureauDEtudeStructure(String bureauDEtudeStructure) {
        this.setBureauDEtudeStructure(bureauDEtudeStructure);
        return this;
    }

    public void setBureauDEtudeStructure(String bureauDEtudeStructure) {
        this.bureauDEtudeStructure = bureauDEtudeStructure;
    }

    public String getBureauControl() {
        return this.bureauControl;
    }

    public Batiments bureauControl(String bureauControl) {
        this.setBureauControl(bureauControl);
        return this;
    }

    public void setBureauControl(String bureauControl) {
        this.bureauControl = bureauControl;
    }

    public String getEntrepriseBottes() {
        return this.entrepriseBottes;
    }

    public Batiments entrepriseBottes(String entrepriseBottes) {
        this.setEntrepriseBottes(entrepriseBottes);
        return this;
    }

    public void setEntrepriseBottes(String entrepriseBottes) {
        this.entrepriseBottes = entrepriseBottes;
    }

    public String getEntrepriseCharpente() {
        return this.entrepriseCharpente;
    }

    public Batiments entrepriseCharpente(String entrepriseCharpente) {
        this.setEntrepriseCharpente(entrepriseCharpente);
        return this;
    }

    public void setEntrepriseCharpente(String entrepriseCharpente) {
        this.entrepriseCharpente = entrepriseCharpente;
    }

    public String getEntrepriseEnduits() {
        return this.entrepriseEnduits;
    }

    public Batiments entrepriseEnduits(String entrepriseEnduits) {
        this.setEntrepriseEnduits(entrepriseEnduits);
        return this;
    }

    public void setEntrepriseEnduits(String entrepriseEnduits) {
        this.entrepriseEnduits = entrepriseEnduits;
    }

    public String getDescriptionProjet() {
        return this.descriptionProjet;
    }

    public Batiments descriptionProjet(String descriptionProjet) {
        this.setDescriptionProjet(descriptionProjet);
        return this;
    }

    public void setDescriptionProjet(String descriptionProjet) {
        this.descriptionProjet = descriptionProjet;
    }

    public String getDifficultees() {
        return this.difficultees;
    }

    public Batiments difficultees(String difficultees) {
        this.setDifficultees(difficultees);
        return this;
    }

    public void setDifficultees(String difficultees) {
        this.difficultees = difficultees;
    }

    public String getAstuces() {
        return this.astuces;
    }

    public Batiments astuces(String astuces) {
        this.setAstuces(astuces);
        return this;
    }

    public void setAstuces(String astuces) {
        this.astuces = astuces;
    }

    public String getDivers() {
        return this.divers;
    }

    public Batiments divers(String divers) {
        this.setDivers(divers);
        return this;
    }

    public void setDivers(String divers) {
        this.divers = divers;
    }

    public String getContactNom() {
        return this.contactNom;
    }

    public Batiments contactNom(String contactNom) {
        this.setContactNom(contactNom);
        return this;
    }

    public void setContactNom(String contactNom) {
        this.contactNom = contactNom;
    }

    public String getContactMail() {
        return this.contactMail;
    }

    public Batiments contactMail(String contactMail) {
        this.setContactMail(contactMail);
        return this;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public Batiments contactPhone(String contactPhone) {
        this.setContactPhone(contactPhone);
        return this;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCodePostal() {
        return this.codePostal;
    }

    public Batiments codePostal(String codePostal) {
        this.setCodePostal(codePostal);
        return this;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Boolean getNonBatimentEtPhotosPublics() {
        return this.nonBatimentEtPhotosPublics;
    }

    public Batiments nonBatimentEtPhotosPublics(Boolean nonBatimentEtPhotosPublics) {
        this.setNonBatimentEtPhotosPublics(nonBatimentEtPhotosPublics);
        return this;
    }

    public void setNonBatimentEtPhotosPublics(Boolean nonBatimentEtPhotosPublics) {
        this.nonBatimentEtPhotosPublics = nonBatimentEtPhotosPublics;
    }

    public LocalDate getDateCreationFiche() {
        return this.dateCreationFiche;
    }

    public Batiments dateCreationFiche(LocalDate dateCreationFiche) {
        this.setDateCreationFiche(dateCreationFiche);
        return this;
    }

    public void setDateCreationFiche(LocalDate dateCreationFiche) {
        this.dateCreationFiche = dateCreationFiche;
    }

    public LocalDate getDateModificationFiche() {
        return this.dateModificationFiche;
    }

    public Batiments dateModificationFiche(LocalDate dateModificationFiche) {
        this.setDateModificationFiche(dateModificationFiche);
        return this;
    }

    public void setDateModificationFiche(LocalDate dateModificationFiche) {
        this.dateModificationFiche = dateModificationFiche;
    }

    public User getCreator() {
        return this.creator;
    }

    public void setCreator(User user) {
        this.creator = user;
    }

    public Batiments creator(User user) {
        this.setCreator(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Batiments)) {
            return false;
        }
        return id != null && id.equals(((Batiments) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Batiments{" +
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
            ", techniqueSecondaire='" + getTechniqueSecondaire() + "'" +
            ", usageBatiment='" + getUsageBatiment() + "'" +
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
            ", structSuppl='" + getStructSuppl() + "'" +
            ", structSupplNature='" + getStructSupplNature() + "'" +
            ", noteCalcul='" + getNoteCalcul() + "'" +
            ", nbrRangDeBottes=" + getNbrRangDeBottes() +
            ", longMaxSansMurRefend=" + getLongMaxSansMurRefend() +
            ", integBaie='" + getIntegBaie() + "'" +
            ", supportAncrage='" + getSupportAncrage() + "'" +
            ", supportAncragePrecisions='" + getSupportAncragePrecisions() + "'" +
            ", revetInt='" + getRevetInt() + "'" +
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
            ", nonBatimentEtPhotosPublics='" + getNonBatimentEtPhotosPublics() + "'" +
            ", dateCreationFiche='" + getDateCreationFiche() + "'" +
            ", dateModificationFiche='" + getDateModificationFiche() + "'" +
            "}";
    }
}
