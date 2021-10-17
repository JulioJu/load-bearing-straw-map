package org.lbstraw.map.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.lbstraw.map.domain.enumeration.Cereale;
import org.lbstraw.map.domain.enumeration.IntegBaie;
import org.lbstraw.map.domain.enumeration.MateriauSb;
import org.lbstraw.map.domain.enumeration.RevetExt;
import org.lbstraw.map.domain.enumeration.RevetInt;
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

    @Size(max = 40)
    @Column(name = "nom", length = 40)
    private String nom;

    @Column(name = "contact_nom")
    private String contactNom;

    @Column(name = "contact_mail")
    private String contactMail;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "construction_debut")
    private LocalDate constructionDebut;

    @Column(name = "construction_fin")
    private LocalDate constructionFin;

    @Column(name = "surface")
    private Integer surface;

    @Column(name = "cout")
    private Integer cout;

    @Enumerated(EnumType.STRING)
    @Column(name = "bottes_taille")
    private TaillesBottes bottesTaille;

    @Enumerated(EnumType.STRING)
    @Column(name = "autoconstruction")
    private YesNoPartial autoconstruction;

    @Size(max = 128)
    @Column(name = "concepteur", length = 128)
    private String concepteur;

    @Size(max = 512)
    @Column(name = "realisateur", length = 512)
    private String realisateur;

    @Enumerated(EnumType.STRING)
    @Column(name = "participatif")
    private YesNoPartial participatif;

    @Enumerated(EnumType.STRING)
    @Column(name = "usage")
    private UsageBatiment usage;

    @Column(name = "note_calcul")
    private Boolean noteCalcul;

    @Column(name = "travaux_neuf")
    private Boolean travauxNeuf;

    @Column(name = "travaux_extension")
    private Boolean travauxExtension;

    @Column(name = "travaux_renov")
    private Boolean travauxRenov;

    @Column(name = "travaux_ite")
    private Boolean travauxIte;

    @Column(name = "travaux_iti")
    private Boolean travauxIti;

    @Column(name = "niveaux")
    private Integer niveaux;

    @Column(name = "bottes_densite")
    private Integer bottesDensite;

    @Column(name = "distance_appro")
    private Integer distanceAppro;

    @Enumerated(EnumType.STRING)
    @Column(name = "bottes_cereale")
    private Cereale bottesCereale;

    @Column(name = "struct_suppl")
    private Boolean structSuppl;

    @Enumerated(EnumType.STRING)
    @Column(name = "revet_int")
    private RevetInt revetInt;

    @Enumerated(EnumType.STRING)
    @Column(name = "revet_ext")
    private RevetExt revetExt;

    @Column(name = "technique_secondaire")
    private Boolean techniqueSecondaire;

    @Size(max = 6)
    @Column(name = "code_postal", length = 6)
    private String codePostal;

    @Enumerated(EnumType.STRING)
    @Column(name = "integ_baie")
    private IntegBaie integBaie;

    @Enumerated(EnumType.STRING)
    @Column(name = "materiau_sb")
    private MateriauSb materiauSb;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description")
    private String description;

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

    public String getNom() {
        return this.nom;
    }

    public Batiments nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public Integer getSurface() {
        return this.surface;
    }

    public Batiments surface(Integer surface) {
        this.setSurface(surface);
        return this;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
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

    public String getConcepteur() {
        return this.concepteur;
    }

    public Batiments concepteur(String concepteur) {
        this.setConcepteur(concepteur);
        return this;
    }

    public void setConcepteur(String concepteur) {
        this.concepteur = concepteur;
    }

    public String getRealisateur() {
        return this.realisateur;
    }

    public Batiments realisateur(String realisateur) {
        this.setRealisateur(realisateur);
        return this;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
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

    public UsageBatiment getUsage() {
        return this.usage;
    }

    public Batiments usage(UsageBatiment usage) {
        this.setUsage(usage);
        return this;
    }

    public void setUsage(UsageBatiment usage) {
        this.usage = usage;
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

    public MateriauSb getMateriauSb() {
        return this.materiauSb;
    }

    public Batiments materiauSb(MateriauSb materiauSb) {
        this.setMateriauSb(materiauSb);
        return this;
    }

    public void setMateriauSb(MateriauSb materiauSb) {
        this.materiauSb = materiauSb;
    }

    public String getDescription() {
        return this.description;
    }

    public Batiments description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
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
            ", nom='" + getNom() + "'" +
            ", contactNom='" + getContactNom() + "'" +
            ", contactMail='" + getContactMail() + "'" +
            ", contactPhone='" + getContactPhone() + "'" +
            ", constructionDebut='" + getConstructionDebut() + "'" +
            ", constructionFin='" + getConstructionFin() + "'" +
            ", surface=" + getSurface() +
            ", cout=" + getCout() +
            ", bottesTaille='" + getBottesTaille() + "'" +
            ", autoconstruction='" + getAutoconstruction() + "'" +
            ", concepteur='" + getConcepteur() + "'" +
            ", realisateur='" + getRealisateur() + "'" +
            ", participatif='" + getParticipatif() + "'" +
            ", usage='" + getUsage() + "'" +
            ", noteCalcul='" + getNoteCalcul() + "'" +
            ", travauxNeuf='" + getTravauxNeuf() + "'" +
            ", travauxExtension='" + getTravauxExtension() + "'" +
            ", travauxRenov='" + getTravauxRenov() + "'" +
            ", travauxIte='" + getTravauxIte() + "'" +
            ", travauxIti='" + getTravauxIti() + "'" +
            ", niveaux=" + getNiveaux() +
            ", bottesDensite=" + getBottesDensite() +
            ", distanceAppro=" + getDistanceAppro() +
            ", bottesCereale='" + getBottesCereale() + "'" +
            ", structSuppl='" + getStructSuppl() + "'" +
            ", revetInt='" + getRevetInt() + "'" +
            ", revetExt='" + getRevetExt() + "'" +
            ", techniqueSecondaire='" + getTechniqueSecondaire() + "'" +
            ", codePostal='" + getCodePostal() + "'" +
            ", integBaie='" + getIntegBaie() + "'" +
            ", materiauSb='" + getMateriauSb() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
