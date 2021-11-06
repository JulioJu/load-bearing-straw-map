package org.lbstraw.map.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lbstraw.map.IntegrationTest;
import org.lbstraw.map.domain.Batiments;
import org.lbstraw.map.domain.enumeration.Cereale;
import org.lbstraw.map.domain.enumeration.IntegBaie;
import org.lbstraw.map.domain.enumeration.RevetExt;
import org.lbstraw.map.domain.enumeration.RevetInt;
import org.lbstraw.map.domain.enumeration.StructureSupplementaire;
import org.lbstraw.map.domain.enumeration.SupportAncrage;
import org.lbstraw.map.domain.enumeration.TaillesBottes;
import org.lbstraw.map.domain.enumeration.UsageBatiment;
import org.lbstraw.map.domain.enumeration.YesNoPartial;
import org.lbstraw.map.domain.enumeration.YesNoPartial;
import org.lbstraw.map.repository.BatimentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link BatimentsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BatimentsResourceIT {

    private static final Float DEFAULT_LATITUDE = -90F;
    private static final Float UPDATED_LATITUDE = -89F;

    private static final Float DEFAULT_LONGITUDE = -90F;
    private static final Float UPDATED_LONGITUDE = -89F;

    private static final String DEFAULT_NOM_BATIMENT = "AAAAAAAAAA";
    private static final String UPDATED_NOM_BATIMENT = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO_PRINCIPALE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO_PRINCIPALE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_PRINCIPALE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_PRINCIPALE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PHOTO_PRINCIPALE_LEGENDE = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_PRINCIPALE_LEGENDE = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO_PRINCIPALE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_PRINCIPALE_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO_1 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_1_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PHOTO_1_LEGENDE = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_1_LEGENDE = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO_1_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_1_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_2_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PHOTO_2_LEGENDE = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_2_LEGENDE = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO_2_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_2_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO_3 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO_3 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_3_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_3_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PHOTO_3_LEGENDE = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_3_LEGENDE = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO_3_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_3_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO_4 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO_4 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_4_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_4_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PHOTO_4_LEGENDE = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_4_LEGENDE = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO_4_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_4_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO_5 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO_5 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_5_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_5_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PHOTO_5_LEGENDE = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_5_LEGENDE = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO_5_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_5_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TECHNIQUE_SECONDAIRE = false;
    private static final Boolean UPDATED_TECHNIQUE_SECONDAIRE = true;

    private static final UsageBatiment DEFAULT_USAGE_BATIMENT = UsageBatiment.LOGEMENT_COLLECTIF;
    private static final UsageBatiment UPDATED_USAGE_BATIMENT = UsageBatiment.LOGEMENT_INDIVIDUEL;

    private static final Integer DEFAULT_COUT = 1;
    private static final Integer UPDATED_COUT = 2;

    private static final Integer DEFAULT_SURFACE_PLANCHER = 1;
    private static final Integer UPDATED_SURFACE_PLANCHER = 2;

    private static final Integer DEFAULT_NIVEAUX = 1;
    private static final Integer UPDATED_NIVEAUX = 2;

    private static final Boolean DEFAULT_TRAVAUX_NEUF = false;
    private static final Boolean UPDATED_TRAVAUX_NEUF = true;

    private static final Boolean DEFAULT_TRAVAUX_EXTENSION = false;
    private static final Boolean UPDATED_TRAVAUX_EXTENSION = true;

    private static final Boolean DEFAULT_TRAVAUX_RENOV = false;
    private static final Boolean UPDATED_TRAVAUX_RENOV = true;

    private static final Boolean DEFAULT_TRAVAUX_ITE = false;
    private static final Boolean UPDATED_TRAVAUX_ITE = true;

    private static final Boolean DEFAULT_TRAVAUX_ITI = false;
    private static final Boolean UPDATED_TRAVAUX_ITI = true;

    private static final LocalDate DEFAULT_CONSTRUCTION_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CONSTRUCTION_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CONSTRUCTION_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CONSTRUCTION_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final TaillesBottes DEFAULT_BOTTES_TAILLE = TaillesBottes.T_70_X_120_X_230_CM;
    private static final TaillesBottes UPDATED_BOTTES_TAILLE = TaillesBottes.T_50_X_80_X_110_a_200_CM;

    private static final String DEFAULT_BOTTE_TAILLE_AUTRE = "AAAAAAAAAA";
    private static final String UPDATED_BOTTE_TAILLE_AUTRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_BOTTES_DENSITE = 1;
    private static final Integer UPDATED_BOTTES_DENSITE = 2;

    private static final Cereale DEFAULT_BOTTES_CEREALE = Cereale.BLE;
    private static final Cereale UPDATED_BOTTES_CEREALE = Cereale.ORGE;

    private static final Integer DEFAULT_DISTANCE_APPRO = 1;
    private static final Integer UPDATED_DISTANCE_APPRO = 2;

    private static final YesNoPartial DEFAULT_AUTOCONSTRUCTION = YesNoPartial.OUI;
    private static final YesNoPartial UPDATED_AUTOCONSTRUCTION = YesNoPartial.NON;

    private static final YesNoPartial DEFAULT_PARTICIPATIF = YesNoPartial.OUI;
    private static final YesNoPartial UPDATED_PARTICIPATIF = YesNoPartial.NON;

    private static final Boolean DEFAULT_STRUCT_SUPPL = false;
    private static final Boolean UPDATED_STRUCT_SUPPL = true;

    private static final StructureSupplementaire DEFAULT_STRUCT_SUPPL_NATURE = StructureSupplementaire.BOIS;
    private static final StructureSupplementaire UPDATED_STRUCT_SUPPL_NATURE = StructureSupplementaire.BETON_ARME;

    private static final Boolean DEFAULT_NOTE_CALCUL = false;
    private static final Boolean UPDATED_NOTE_CALCUL = true;

    private static final Integer DEFAULT_NBR_RANG_DE_BOTTES = 1;
    private static final Integer UPDATED_NBR_RANG_DE_BOTTES = 2;

    private static final Float DEFAULT_LONG_MAX_SANS_MUR_REFEND = 1F;
    private static final Float UPDATED_LONG_MAX_SANS_MUR_REFEND = 2F;

    private static final IntegBaie DEFAULT_INTEG_BAIE = IntegBaie.PRE_CADRE_FLOTTANT;
    private static final IntegBaie UPDATED_INTEG_BAIE = IntegBaie.COULISSANT;

    private static final SupportAncrage DEFAULT_SUPPORT_ANCRAGE = SupportAncrage.BOIS;
    private static final SupportAncrage UPDATED_SUPPORT_ANCRAGE = SupportAncrage.BETON_ARME;

    private static final String DEFAULT_SUPPORT_ANCRAGE_PRECISIONS = "AAAAAAAAAA";
    private static final String UPDATED_SUPPORT_ANCRAGE_PRECISIONS = "BBBBBBBBBB";

    private static final RevetInt DEFAULT_REVET_INT = RevetInt.PLAQUE_DE_PLATRE;
    private static final RevetInt UPDATED_REVET_INT = RevetInt.LAMBRIS;

    private static final RevetExt DEFAULT_REVET_EXT = RevetExt.BARDAGE_VENTILE;
    private static final RevetExt UPDATED_REVET_EXT = RevetExt.ENDUIT_TERRE;

    private static final String DEFAULT_REVET_EXT_AUTRE = "AAAAAAAAAA";
    private static final String UPDATED_REVET_EXT_AUTRE = "BBBBBBBBBB";

    private static final String DEFAULT_MAITRE_D_OUVRAGE = "AAAAAAAAAA";
    private static final String UPDATED_MAITRE_D_OUVRAGE = "BBBBBBBBBB";

    private static final String DEFAULT_MAITRE_D_OEUVRE = "AAAAAAAAAA";
    private static final String UPDATED_MAITRE_D_OEUVRE = "BBBBBBBBBB";

    private static final String DEFAULT_ARCHITECTE = "AAAAAAAAAA";
    private static final String UPDATED_ARCHITECTE = "BBBBBBBBBB";

    private static final String DEFAULT_BUREAU_D_ETUDE_STRUCTURE = "AAAAAAAAAA";
    private static final String UPDATED_BUREAU_D_ETUDE_STRUCTURE = "BBBBBBBBBB";

    private static final String DEFAULT_BUREAU_CONTROL = "AAAAAAAAAA";
    private static final String UPDATED_BUREAU_CONTROL = "BBBBBBBBBB";

    private static final String DEFAULT_ENTREPRISE_BOTTES = "AAAAAAAAAA";
    private static final String UPDATED_ENTREPRISE_BOTTES = "BBBBBBBBBB";

    private static final String DEFAULT_ENTREPRISE_CHARPENTE = "AAAAAAAAAA";
    private static final String UPDATED_ENTREPRISE_CHARPENTE = "BBBBBBBBBB";

    private static final String DEFAULT_ENTREPRISE_ENDUITS = "AAAAAAAAAA";
    private static final String UPDATED_ENTREPRISE_ENDUITS = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_PROJET = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_PROJET = "BBBBBBBBBB";

    private static final String DEFAULT_DIFFICULTEES = "AAAAAAAAAA";
    private static final String UPDATED_DIFFICULTEES = "BBBBBBBBBB";

    private static final String DEFAULT_ASTUCES = "AAAAAAAAAA";
    private static final String UPDATED_ASTUCES = "BBBBBBBBBB";

    private static final String DEFAULT_DIVERS = "AAAAAAAAAA";
    private static final String UPDATED_DIVERS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_POSTAL = "AAAAAA";
    private static final String UPDATED_CODE_POSTAL = "BBBBBB";

    private static final Boolean DEFAULT_NON_BATIMENT_ET_PHOTOS_PUBLICS = false;
    private static final Boolean UPDATED_NON_BATIMENT_ET_PHOTOS_PUBLICS = true;

    private static final LocalDate DEFAULT_DATE_CREATION_FICHE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREATION_FICHE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_MODIFICATION_FICHE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MODIFICATION_FICHE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/batiments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BatimentsRepository batimentsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBatimentsMockMvc;

    private Batiments batiments;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Batiments createEntity(EntityManager em) {
        Batiments batiments = new Batiments()
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE)
            .nomBatiment(DEFAULT_NOM_BATIMENT)
            .photoPrincipale(DEFAULT_PHOTO_PRINCIPALE)
            .photoPrincipaleContentType(DEFAULT_PHOTO_PRINCIPALE_CONTENT_TYPE)
            .photoPrincipaleLegende(DEFAULT_PHOTO_PRINCIPALE_LEGENDE)
            .photoPrincipaleDescription(DEFAULT_PHOTO_PRINCIPALE_DESCRIPTION)
            .photo1(DEFAULT_PHOTO_1)
            .photo1ContentType(DEFAULT_PHOTO_1_CONTENT_TYPE)
            .photo1Legende(DEFAULT_PHOTO_1_LEGENDE)
            .photo1Description(DEFAULT_PHOTO_1_DESCRIPTION)
            .photo2(DEFAULT_PHOTO_2)
            .photo2ContentType(DEFAULT_PHOTO_2_CONTENT_TYPE)
            .photo2Legende(DEFAULT_PHOTO_2_LEGENDE)
            .photo2Description(DEFAULT_PHOTO_2_DESCRIPTION)
            .photo3(DEFAULT_PHOTO_3)
            .photo3ContentType(DEFAULT_PHOTO_3_CONTENT_TYPE)
            .photo3Legende(DEFAULT_PHOTO_3_LEGENDE)
            .photo3Description(DEFAULT_PHOTO_3_DESCRIPTION)
            .photo4(DEFAULT_PHOTO_4)
            .photo4ContentType(DEFAULT_PHOTO_4_CONTENT_TYPE)
            .photo4Legende(DEFAULT_PHOTO_4_LEGENDE)
            .photo4Description(DEFAULT_PHOTO_4_DESCRIPTION)
            .photo5(DEFAULT_PHOTO_5)
            .photo5ContentType(DEFAULT_PHOTO_5_CONTENT_TYPE)
            .photo5Legende(DEFAULT_PHOTO_5_LEGENDE)
            .photo5Description(DEFAULT_PHOTO_5_DESCRIPTION)
            .techniqueSecondaire(DEFAULT_TECHNIQUE_SECONDAIRE)
            .usageBatiment(DEFAULT_USAGE_BATIMENT)
            .cout(DEFAULT_COUT)
            .surfacePlancher(DEFAULT_SURFACE_PLANCHER)
            .niveaux(DEFAULT_NIVEAUX)
            .travauxNeuf(DEFAULT_TRAVAUX_NEUF)
            .travauxExtension(DEFAULT_TRAVAUX_EXTENSION)
            .travauxRenov(DEFAULT_TRAVAUX_RENOV)
            .travauxIte(DEFAULT_TRAVAUX_ITE)
            .travauxIti(DEFAULT_TRAVAUX_ITI)
            .constructionDebut(DEFAULT_CONSTRUCTION_DEBUT)
            .constructionFin(DEFAULT_CONSTRUCTION_FIN)
            .bottesTaille(DEFAULT_BOTTES_TAILLE)
            .botteTailleAutre(DEFAULT_BOTTE_TAILLE_AUTRE)
            .bottesDensite(DEFAULT_BOTTES_DENSITE)
            .bottesCereale(DEFAULT_BOTTES_CEREALE)
            .distanceAppro(DEFAULT_DISTANCE_APPRO)
            .autoconstruction(DEFAULT_AUTOCONSTRUCTION)
            .participatif(DEFAULT_PARTICIPATIF)
            .structSuppl(DEFAULT_STRUCT_SUPPL)
            .structSupplNature(DEFAULT_STRUCT_SUPPL_NATURE)
            .noteCalcul(DEFAULT_NOTE_CALCUL)
            .nbrRangDeBottes(DEFAULT_NBR_RANG_DE_BOTTES)
            .longMaxSansMurRefend(DEFAULT_LONG_MAX_SANS_MUR_REFEND)
            .integBaie(DEFAULT_INTEG_BAIE)
            .supportAncrage(DEFAULT_SUPPORT_ANCRAGE)
            .supportAncragePrecisions(DEFAULT_SUPPORT_ANCRAGE_PRECISIONS)
            .revetInt(DEFAULT_REVET_INT)
            .revetExt(DEFAULT_REVET_EXT)
            .revetExtAutre(DEFAULT_REVET_EXT_AUTRE)
            .maitreDOuvrage(DEFAULT_MAITRE_D_OUVRAGE)
            .maitreDOeuvre(DEFAULT_MAITRE_D_OEUVRE)
            .architecte(DEFAULT_ARCHITECTE)
            .bureauDEtudeStructure(DEFAULT_BUREAU_D_ETUDE_STRUCTURE)
            .bureauControl(DEFAULT_BUREAU_CONTROL)
            .entrepriseBottes(DEFAULT_ENTREPRISE_BOTTES)
            .entrepriseCharpente(DEFAULT_ENTREPRISE_CHARPENTE)
            .entrepriseEnduits(DEFAULT_ENTREPRISE_ENDUITS)
            .descriptionProjet(DEFAULT_DESCRIPTION_PROJET)
            .difficultees(DEFAULT_DIFFICULTEES)
            .astuces(DEFAULT_ASTUCES)
            .divers(DEFAULT_DIVERS)
            .contactNom(DEFAULT_CONTACT_NOM)
            .contactMail(DEFAULT_CONTACT_MAIL)
            .contactPhone(DEFAULT_CONTACT_PHONE)
            .codePostal(DEFAULT_CODE_POSTAL)
            .nonBatimentEtPhotosPublics(DEFAULT_NON_BATIMENT_ET_PHOTOS_PUBLICS)
            .dateCreationFiche(DEFAULT_DATE_CREATION_FICHE)
            .dateModificationFiche(DEFAULT_DATE_MODIFICATION_FICHE);
        return batiments;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Batiments createUpdatedEntity(EntityManager em) {
        Batiments batiments = new Batiments()
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .nomBatiment(UPDATED_NOM_BATIMENT)
            .photoPrincipale(UPDATED_PHOTO_PRINCIPALE)
            .photoPrincipaleContentType(UPDATED_PHOTO_PRINCIPALE_CONTENT_TYPE)
            .photoPrincipaleLegende(UPDATED_PHOTO_PRINCIPALE_LEGENDE)
            .photoPrincipaleDescription(UPDATED_PHOTO_PRINCIPALE_DESCRIPTION)
            .photo1(UPDATED_PHOTO_1)
            .photo1ContentType(UPDATED_PHOTO_1_CONTENT_TYPE)
            .photo1Legende(UPDATED_PHOTO_1_LEGENDE)
            .photo1Description(UPDATED_PHOTO_1_DESCRIPTION)
            .photo2(UPDATED_PHOTO_2)
            .photo2ContentType(UPDATED_PHOTO_2_CONTENT_TYPE)
            .photo2Legende(UPDATED_PHOTO_2_LEGENDE)
            .photo2Description(UPDATED_PHOTO_2_DESCRIPTION)
            .photo3(UPDATED_PHOTO_3)
            .photo3ContentType(UPDATED_PHOTO_3_CONTENT_TYPE)
            .photo3Legende(UPDATED_PHOTO_3_LEGENDE)
            .photo3Description(UPDATED_PHOTO_3_DESCRIPTION)
            .photo4(UPDATED_PHOTO_4)
            .photo4ContentType(UPDATED_PHOTO_4_CONTENT_TYPE)
            .photo4Legende(UPDATED_PHOTO_4_LEGENDE)
            .photo4Description(UPDATED_PHOTO_4_DESCRIPTION)
            .photo5(UPDATED_PHOTO_5)
            .photo5ContentType(UPDATED_PHOTO_5_CONTENT_TYPE)
            .photo5Legende(UPDATED_PHOTO_5_LEGENDE)
            .photo5Description(UPDATED_PHOTO_5_DESCRIPTION)
            .techniqueSecondaire(UPDATED_TECHNIQUE_SECONDAIRE)
            .usageBatiment(UPDATED_USAGE_BATIMENT)
            .cout(UPDATED_COUT)
            .surfacePlancher(UPDATED_SURFACE_PLANCHER)
            .niveaux(UPDATED_NIVEAUX)
            .travauxNeuf(UPDATED_TRAVAUX_NEUF)
            .travauxExtension(UPDATED_TRAVAUX_EXTENSION)
            .travauxRenov(UPDATED_TRAVAUX_RENOV)
            .travauxIte(UPDATED_TRAVAUX_ITE)
            .travauxIti(UPDATED_TRAVAUX_ITI)
            .constructionDebut(UPDATED_CONSTRUCTION_DEBUT)
            .constructionFin(UPDATED_CONSTRUCTION_FIN)
            .bottesTaille(UPDATED_BOTTES_TAILLE)
            .botteTailleAutre(UPDATED_BOTTE_TAILLE_AUTRE)
            .bottesDensite(UPDATED_BOTTES_DENSITE)
            .bottesCereale(UPDATED_BOTTES_CEREALE)
            .distanceAppro(UPDATED_DISTANCE_APPRO)
            .autoconstruction(UPDATED_AUTOCONSTRUCTION)
            .participatif(UPDATED_PARTICIPATIF)
            .structSuppl(UPDATED_STRUCT_SUPPL)
            .structSupplNature(UPDATED_STRUCT_SUPPL_NATURE)
            .noteCalcul(UPDATED_NOTE_CALCUL)
            .nbrRangDeBottes(UPDATED_NBR_RANG_DE_BOTTES)
            .longMaxSansMurRefend(UPDATED_LONG_MAX_SANS_MUR_REFEND)
            .integBaie(UPDATED_INTEG_BAIE)
            .supportAncrage(UPDATED_SUPPORT_ANCRAGE)
            .supportAncragePrecisions(UPDATED_SUPPORT_ANCRAGE_PRECISIONS)
            .revetInt(UPDATED_REVET_INT)
            .revetExt(UPDATED_REVET_EXT)
            .revetExtAutre(UPDATED_REVET_EXT_AUTRE)
            .maitreDOuvrage(UPDATED_MAITRE_D_OUVRAGE)
            .maitreDOeuvre(UPDATED_MAITRE_D_OEUVRE)
            .architecte(UPDATED_ARCHITECTE)
            .bureauDEtudeStructure(UPDATED_BUREAU_D_ETUDE_STRUCTURE)
            .bureauControl(UPDATED_BUREAU_CONTROL)
            .entrepriseBottes(UPDATED_ENTREPRISE_BOTTES)
            .entrepriseCharpente(UPDATED_ENTREPRISE_CHARPENTE)
            .entrepriseEnduits(UPDATED_ENTREPRISE_ENDUITS)
            .descriptionProjet(UPDATED_DESCRIPTION_PROJET)
            .difficultees(UPDATED_DIFFICULTEES)
            .astuces(UPDATED_ASTUCES)
            .divers(UPDATED_DIVERS)
            .contactNom(UPDATED_CONTACT_NOM)
            .contactMail(UPDATED_CONTACT_MAIL)
            .contactPhone(UPDATED_CONTACT_PHONE)
            .codePostal(UPDATED_CODE_POSTAL)
            .nonBatimentEtPhotosPublics(UPDATED_NON_BATIMENT_ET_PHOTOS_PUBLICS)
            .dateCreationFiche(UPDATED_DATE_CREATION_FICHE)
            .dateModificationFiche(UPDATED_DATE_MODIFICATION_FICHE);
        return batiments;
    }

    @BeforeEach
    public void initTest() {
        batiments = createEntity(em);
    }

    @Test
    @Transactional
    void createBatiments() throws Exception {
        int databaseSizeBeforeCreate = batimentsRepository.findAll().size();
        // Create the Batiments
        restBatimentsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(batiments))
            )
            .andExpect(status().isCreated());

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeCreate + 1);
        Batiments testBatiments = batimentsList.get(batimentsList.size() - 1);
        assertThat(testBatiments.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testBatiments.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testBatiments.getNomBatiment()).isEqualTo(DEFAULT_NOM_BATIMENT);
        assertThat(testBatiments.getPhotoPrincipale()).isEqualTo(DEFAULT_PHOTO_PRINCIPALE);
        assertThat(testBatiments.getPhotoPrincipaleContentType()).isEqualTo(DEFAULT_PHOTO_PRINCIPALE_CONTENT_TYPE);
        assertThat(testBatiments.getPhotoPrincipaleLegende()).isEqualTo(DEFAULT_PHOTO_PRINCIPALE_LEGENDE);
        assertThat(testBatiments.getPhotoPrincipaleDescription()).isEqualTo(DEFAULT_PHOTO_PRINCIPALE_DESCRIPTION);
        assertThat(testBatiments.getPhoto1()).isEqualTo(DEFAULT_PHOTO_1);
        assertThat(testBatiments.getPhoto1ContentType()).isEqualTo(DEFAULT_PHOTO_1_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto1Legende()).isEqualTo(DEFAULT_PHOTO_1_LEGENDE);
        assertThat(testBatiments.getPhoto1Description()).isEqualTo(DEFAULT_PHOTO_1_DESCRIPTION);
        assertThat(testBatiments.getPhoto2()).isEqualTo(DEFAULT_PHOTO_2);
        assertThat(testBatiments.getPhoto2ContentType()).isEqualTo(DEFAULT_PHOTO_2_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto2Legende()).isEqualTo(DEFAULT_PHOTO_2_LEGENDE);
        assertThat(testBatiments.getPhoto2Description()).isEqualTo(DEFAULT_PHOTO_2_DESCRIPTION);
        assertThat(testBatiments.getPhoto3()).isEqualTo(DEFAULT_PHOTO_3);
        assertThat(testBatiments.getPhoto3ContentType()).isEqualTo(DEFAULT_PHOTO_3_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto3Legende()).isEqualTo(DEFAULT_PHOTO_3_LEGENDE);
        assertThat(testBatiments.getPhoto3Description()).isEqualTo(DEFAULT_PHOTO_3_DESCRIPTION);
        assertThat(testBatiments.getPhoto4()).isEqualTo(DEFAULT_PHOTO_4);
        assertThat(testBatiments.getPhoto4ContentType()).isEqualTo(DEFAULT_PHOTO_4_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto4Legende()).isEqualTo(DEFAULT_PHOTO_4_LEGENDE);
        assertThat(testBatiments.getPhoto4Description()).isEqualTo(DEFAULT_PHOTO_4_DESCRIPTION);
        assertThat(testBatiments.getPhoto5()).isEqualTo(DEFAULT_PHOTO_5);
        assertThat(testBatiments.getPhoto5ContentType()).isEqualTo(DEFAULT_PHOTO_5_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto5Legende()).isEqualTo(DEFAULT_PHOTO_5_LEGENDE);
        assertThat(testBatiments.getPhoto5Description()).isEqualTo(DEFAULT_PHOTO_5_DESCRIPTION);
        assertThat(testBatiments.getTechniqueSecondaire()).isEqualTo(DEFAULT_TECHNIQUE_SECONDAIRE);
        assertThat(testBatiments.getUsageBatiment()).isEqualTo(DEFAULT_USAGE_BATIMENT);
        assertThat(testBatiments.getCout()).isEqualTo(DEFAULT_COUT);
        assertThat(testBatiments.getSurfacePlancher()).isEqualTo(DEFAULT_SURFACE_PLANCHER);
        assertThat(testBatiments.getNiveaux()).isEqualTo(DEFAULT_NIVEAUX);
        assertThat(testBatiments.getTravauxNeuf()).isEqualTo(DEFAULT_TRAVAUX_NEUF);
        assertThat(testBatiments.getTravauxExtension()).isEqualTo(DEFAULT_TRAVAUX_EXTENSION);
        assertThat(testBatiments.getTravauxRenov()).isEqualTo(DEFAULT_TRAVAUX_RENOV);
        assertThat(testBatiments.getTravauxIte()).isEqualTo(DEFAULT_TRAVAUX_ITE);
        assertThat(testBatiments.getTravauxIti()).isEqualTo(DEFAULT_TRAVAUX_ITI);
        assertThat(testBatiments.getConstructionDebut()).isEqualTo(DEFAULT_CONSTRUCTION_DEBUT);
        assertThat(testBatiments.getConstructionFin()).isEqualTo(DEFAULT_CONSTRUCTION_FIN);
        assertThat(testBatiments.getBottesTaille()).isEqualTo(DEFAULT_BOTTES_TAILLE);
        assertThat(testBatiments.getBotteTailleAutre()).isEqualTo(DEFAULT_BOTTE_TAILLE_AUTRE);
        assertThat(testBatiments.getBottesDensite()).isEqualTo(DEFAULT_BOTTES_DENSITE);
        assertThat(testBatiments.getBottesCereale()).isEqualTo(DEFAULT_BOTTES_CEREALE);
        assertThat(testBatiments.getDistanceAppro()).isEqualTo(DEFAULT_DISTANCE_APPRO);
        assertThat(testBatiments.getAutoconstruction()).isEqualTo(DEFAULT_AUTOCONSTRUCTION);
        assertThat(testBatiments.getParticipatif()).isEqualTo(DEFAULT_PARTICIPATIF);
        assertThat(testBatiments.getStructSuppl()).isEqualTo(DEFAULT_STRUCT_SUPPL);
        assertThat(testBatiments.getStructSupplNature()).isEqualTo(DEFAULT_STRUCT_SUPPL_NATURE);
        assertThat(testBatiments.getNoteCalcul()).isEqualTo(DEFAULT_NOTE_CALCUL);
        assertThat(testBatiments.getNbrRangDeBottes()).isEqualTo(DEFAULT_NBR_RANG_DE_BOTTES);
        assertThat(testBatiments.getLongMaxSansMurRefend()).isEqualTo(DEFAULT_LONG_MAX_SANS_MUR_REFEND);
        assertThat(testBatiments.getIntegBaie()).isEqualTo(DEFAULT_INTEG_BAIE);
        assertThat(testBatiments.getSupportAncrage()).isEqualTo(DEFAULT_SUPPORT_ANCRAGE);
        assertThat(testBatiments.getSupportAncragePrecisions()).isEqualTo(DEFAULT_SUPPORT_ANCRAGE_PRECISIONS);
        assertThat(testBatiments.getRevetInt()).isEqualTo(DEFAULT_REVET_INT);
        assertThat(testBatiments.getRevetExt()).isEqualTo(DEFAULT_REVET_EXT);
        assertThat(testBatiments.getRevetExtAutre()).isEqualTo(DEFAULT_REVET_EXT_AUTRE);
        assertThat(testBatiments.getMaitreDOuvrage()).isEqualTo(DEFAULT_MAITRE_D_OUVRAGE);
        assertThat(testBatiments.getMaitreDOeuvre()).isEqualTo(DEFAULT_MAITRE_D_OEUVRE);
        assertThat(testBatiments.getArchitecte()).isEqualTo(DEFAULT_ARCHITECTE);
        assertThat(testBatiments.getBureauDEtudeStructure()).isEqualTo(DEFAULT_BUREAU_D_ETUDE_STRUCTURE);
        assertThat(testBatiments.getBureauControl()).isEqualTo(DEFAULT_BUREAU_CONTROL);
        assertThat(testBatiments.getEntrepriseBottes()).isEqualTo(DEFAULT_ENTREPRISE_BOTTES);
        assertThat(testBatiments.getEntrepriseCharpente()).isEqualTo(DEFAULT_ENTREPRISE_CHARPENTE);
        assertThat(testBatiments.getEntrepriseEnduits()).isEqualTo(DEFAULT_ENTREPRISE_ENDUITS);
        assertThat(testBatiments.getDescriptionProjet()).isEqualTo(DEFAULT_DESCRIPTION_PROJET);
        assertThat(testBatiments.getDifficultees()).isEqualTo(DEFAULT_DIFFICULTEES);
        assertThat(testBatiments.getAstuces()).isEqualTo(DEFAULT_ASTUCES);
        assertThat(testBatiments.getDivers()).isEqualTo(DEFAULT_DIVERS);
        assertThat(testBatiments.getContactNom()).isEqualTo(DEFAULT_CONTACT_NOM);
        assertThat(testBatiments.getContactMail()).isEqualTo(DEFAULT_CONTACT_MAIL);
        assertThat(testBatiments.getContactPhone()).isEqualTo(DEFAULT_CONTACT_PHONE);
        assertThat(testBatiments.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testBatiments.getNonBatimentEtPhotosPublics()).isEqualTo(DEFAULT_NON_BATIMENT_ET_PHOTOS_PUBLICS);
        assertThat(testBatiments.getDateCreationFiche()).isEqualTo(DEFAULT_DATE_CREATION_FICHE);
        assertThat(testBatiments.getDateModificationFiche()).isEqualTo(DEFAULT_DATE_MODIFICATION_FICHE);
    }

    @Test
    @Transactional
    void createBatimentsWithExistingId() throws Exception {
        // Create the Batiments with an existing ID
        batiments.setId(1L);

        int databaseSizeBeforeCreate = batimentsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBatimentsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(batiments))
            )
            .andExpect(status().isBadRequest());

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = batimentsRepository.findAll().size();
        // set the field null
        batiments.setLatitude(null);

        // Create the Batiments, which fails.

        restBatimentsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(batiments))
            )
            .andExpect(status().isBadRequest());

        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = batimentsRepository.findAll().size();
        // set the field null
        batiments.setLongitude(null);

        // Create the Batiments, which fails.

        restBatimentsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(batiments))
            )
            .andExpect(status().isBadRequest());

        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateCreationFicheIsRequired() throws Exception {
        int databaseSizeBeforeTest = batimentsRepository.findAll().size();
        // set the field null
        batiments.setDateCreationFiche(null);

        // Create the Batiments, which fails.

        restBatimentsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(batiments))
            )
            .andExpect(status().isBadRequest());

        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateModificationFicheIsRequired() throws Exception {
        int databaseSizeBeforeTest = batimentsRepository.findAll().size();
        // set the field null
        batiments.setDateModificationFiche(null);

        // Create the Batiments, which fails.

        restBatimentsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(batiments))
            )
            .andExpect(status().isBadRequest());

        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBatiments() throws Exception {
        // Initialize the database
        batimentsRepository.saveAndFlush(batiments);

        // Get all the batimentsList
        restBatimentsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(batiments.getId().intValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].nomBatiment").value(hasItem(DEFAULT_NOM_BATIMENT)))
            .andExpect(jsonPath("$.[*].photoPrincipaleContentType").value(hasItem(DEFAULT_PHOTO_PRINCIPALE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photoPrincipale").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO_PRINCIPALE))))
            .andExpect(jsonPath("$.[*].photoPrincipaleLegende").value(hasItem(DEFAULT_PHOTO_PRINCIPALE_LEGENDE)))
            .andExpect(jsonPath("$.[*].photoPrincipaleDescription").value(hasItem(DEFAULT_PHOTO_PRINCIPALE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].photo1ContentType").value(hasItem(DEFAULT_PHOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo1").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO_1))))
            .andExpect(jsonPath("$.[*].photo1Legende").value(hasItem(DEFAULT_PHOTO_1_LEGENDE)))
            .andExpect(jsonPath("$.[*].photo1Description").value(hasItem(DEFAULT_PHOTO_1_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].photo2ContentType").value(hasItem(DEFAULT_PHOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo2").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO_2))))
            .andExpect(jsonPath("$.[*].photo2Legende").value(hasItem(DEFAULT_PHOTO_2_LEGENDE)))
            .andExpect(jsonPath("$.[*].photo2Description").value(hasItem(DEFAULT_PHOTO_2_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].photo3ContentType").value(hasItem(DEFAULT_PHOTO_3_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo3").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO_3))))
            .andExpect(jsonPath("$.[*].photo3Legende").value(hasItem(DEFAULT_PHOTO_3_LEGENDE)))
            .andExpect(jsonPath("$.[*].photo3Description").value(hasItem(DEFAULT_PHOTO_3_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].photo4ContentType").value(hasItem(DEFAULT_PHOTO_4_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo4").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO_4))))
            .andExpect(jsonPath("$.[*].photo4Legende").value(hasItem(DEFAULT_PHOTO_4_LEGENDE)))
            .andExpect(jsonPath("$.[*].photo4Description").value(hasItem(DEFAULT_PHOTO_4_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].photo5ContentType").value(hasItem(DEFAULT_PHOTO_5_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo5").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO_5))))
            .andExpect(jsonPath("$.[*].photo5Legende").value(hasItem(DEFAULT_PHOTO_5_LEGENDE)))
            .andExpect(jsonPath("$.[*].photo5Description").value(hasItem(DEFAULT_PHOTO_5_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].techniqueSecondaire").value(hasItem(DEFAULT_TECHNIQUE_SECONDAIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].usageBatiment").value(hasItem(DEFAULT_USAGE_BATIMENT.toString())))
            .andExpect(jsonPath("$.[*].cout").value(hasItem(DEFAULT_COUT)))
            .andExpect(jsonPath("$.[*].surfacePlancher").value(hasItem(DEFAULT_SURFACE_PLANCHER)))
            .andExpect(jsonPath("$.[*].niveaux").value(hasItem(DEFAULT_NIVEAUX)))
            .andExpect(jsonPath("$.[*].travauxNeuf").value(hasItem(DEFAULT_TRAVAUX_NEUF.booleanValue())))
            .andExpect(jsonPath("$.[*].travauxExtension").value(hasItem(DEFAULT_TRAVAUX_EXTENSION.booleanValue())))
            .andExpect(jsonPath("$.[*].travauxRenov").value(hasItem(DEFAULT_TRAVAUX_RENOV.booleanValue())))
            .andExpect(jsonPath("$.[*].travauxIte").value(hasItem(DEFAULT_TRAVAUX_ITE.booleanValue())))
            .andExpect(jsonPath("$.[*].travauxIti").value(hasItem(DEFAULT_TRAVAUX_ITI.booleanValue())))
            .andExpect(jsonPath("$.[*].constructionDebut").value(hasItem(DEFAULT_CONSTRUCTION_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].constructionFin").value(hasItem(DEFAULT_CONSTRUCTION_FIN.toString())))
            .andExpect(jsonPath("$.[*].bottesTaille").value(hasItem(DEFAULT_BOTTES_TAILLE.toString())))
            .andExpect(jsonPath("$.[*].botteTailleAutre").value(hasItem(DEFAULT_BOTTE_TAILLE_AUTRE)))
            .andExpect(jsonPath("$.[*].bottesDensite").value(hasItem(DEFAULT_BOTTES_DENSITE)))
            .andExpect(jsonPath("$.[*].bottesCereale").value(hasItem(DEFAULT_BOTTES_CEREALE.toString())))
            .andExpect(jsonPath("$.[*].distanceAppro").value(hasItem(DEFAULT_DISTANCE_APPRO)))
            .andExpect(jsonPath("$.[*].autoconstruction").value(hasItem(DEFAULT_AUTOCONSTRUCTION.toString())))
            .andExpect(jsonPath("$.[*].participatif").value(hasItem(DEFAULT_PARTICIPATIF.toString())))
            .andExpect(jsonPath("$.[*].structSuppl").value(hasItem(DEFAULT_STRUCT_SUPPL.booleanValue())))
            .andExpect(jsonPath("$.[*].structSupplNature").value(hasItem(DEFAULT_STRUCT_SUPPL_NATURE.toString())))
            .andExpect(jsonPath("$.[*].noteCalcul").value(hasItem(DEFAULT_NOTE_CALCUL.booleanValue())))
            .andExpect(jsonPath("$.[*].nbrRangDeBottes").value(hasItem(DEFAULT_NBR_RANG_DE_BOTTES)))
            .andExpect(jsonPath("$.[*].longMaxSansMurRefend").value(hasItem(DEFAULT_LONG_MAX_SANS_MUR_REFEND.doubleValue())))
            .andExpect(jsonPath("$.[*].integBaie").value(hasItem(DEFAULT_INTEG_BAIE.toString())))
            .andExpect(jsonPath("$.[*].supportAncrage").value(hasItem(DEFAULT_SUPPORT_ANCRAGE.toString())))
            .andExpect(jsonPath("$.[*].supportAncragePrecisions").value(hasItem(DEFAULT_SUPPORT_ANCRAGE_PRECISIONS)))
            .andExpect(jsonPath("$.[*].revetInt").value(hasItem(DEFAULT_REVET_INT.toString())))
            .andExpect(jsonPath("$.[*].revetExt").value(hasItem(DEFAULT_REVET_EXT.toString())))
            .andExpect(jsonPath("$.[*].revetExtAutre").value(hasItem(DEFAULT_REVET_EXT_AUTRE)))
            .andExpect(jsonPath("$.[*].maitreDOuvrage").value(hasItem(DEFAULT_MAITRE_D_OUVRAGE)))
            .andExpect(jsonPath("$.[*].maitreDOeuvre").value(hasItem(DEFAULT_MAITRE_D_OEUVRE)))
            .andExpect(jsonPath("$.[*].architecte").value(hasItem(DEFAULT_ARCHITECTE)))
            .andExpect(jsonPath("$.[*].bureauDEtudeStructure").value(hasItem(DEFAULT_BUREAU_D_ETUDE_STRUCTURE)))
            .andExpect(jsonPath("$.[*].bureauControl").value(hasItem(DEFAULT_BUREAU_CONTROL)))
            .andExpect(jsonPath("$.[*].entrepriseBottes").value(hasItem(DEFAULT_ENTREPRISE_BOTTES)))
            .andExpect(jsonPath("$.[*].entrepriseCharpente").value(hasItem(DEFAULT_ENTREPRISE_CHARPENTE)))
            .andExpect(jsonPath("$.[*].entrepriseEnduits").value(hasItem(DEFAULT_ENTREPRISE_ENDUITS)))
            .andExpect(jsonPath("$.[*].descriptionProjet").value(hasItem(DEFAULT_DESCRIPTION_PROJET.toString())))
            .andExpect(jsonPath("$.[*].difficultees").value(hasItem(DEFAULT_DIFFICULTEES.toString())))
            .andExpect(jsonPath("$.[*].astuces").value(hasItem(DEFAULT_ASTUCES.toString())))
            .andExpect(jsonPath("$.[*].divers").value(hasItem(DEFAULT_DIVERS.toString())))
            .andExpect(jsonPath("$.[*].contactNom").value(hasItem(DEFAULT_CONTACT_NOM)))
            .andExpect(jsonPath("$.[*].contactMail").value(hasItem(DEFAULT_CONTACT_MAIL)))
            .andExpect(jsonPath("$.[*].contactPhone").value(hasItem(DEFAULT_CONTACT_PHONE)))
            .andExpect(jsonPath("$.[*].codePostal").value(hasItem(DEFAULT_CODE_POSTAL)))
            .andExpect(jsonPath("$.[*].nonBatimentEtPhotosPublics").value(hasItem(DEFAULT_NON_BATIMENT_ET_PHOTOS_PUBLICS.booleanValue())))
            .andExpect(jsonPath("$.[*].dateCreationFiche").value(hasItem(DEFAULT_DATE_CREATION_FICHE.toString())))
            .andExpect(jsonPath("$.[*].dateModificationFiche").value(hasItem(DEFAULT_DATE_MODIFICATION_FICHE.toString())));
    }

    @Test
    @Transactional
    void getBatiments() throws Exception {
        // Initialize the database
        batimentsRepository.saveAndFlush(batiments);

        // Get the batiments
        restBatimentsMockMvc
            .perform(get(ENTITY_API_URL_ID, batiments.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(batiments.getId().intValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.nomBatiment").value(DEFAULT_NOM_BATIMENT))
            .andExpect(jsonPath("$.photoPrincipaleContentType").value(DEFAULT_PHOTO_PRINCIPALE_CONTENT_TYPE))
            .andExpect(jsonPath("$.photoPrincipale").value(Base64Utils.encodeToString(DEFAULT_PHOTO_PRINCIPALE)))
            .andExpect(jsonPath("$.photoPrincipaleLegende").value(DEFAULT_PHOTO_PRINCIPALE_LEGENDE))
            .andExpect(jsonPath("$.photoPrincipaleDescription").value(DEFAULT_PHOTO_PRINCIPALE_DESCRIPTION))
            .andExpect(jsonPath("$.photo1ContentType").value(DEFAULT_PHOTO_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo1").value(Base64Utils.encodeToString(DEFAULT_PHOTO_1)))
            .andExpect(jsonPath("$.photo1Legende").value(DEFAULT_PHOTO_1_LEGENDE))
            .andExpect(jsonPath("$.photo1Description").value(DEFAULT_PHOTO_1_DESCRIPTION))
            .andExpect(jsonPath("$.photo2ContentType").value(DEFAULT_PHOTO_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo2").value(Base64Utils.encodeToString(DEFAULT_PHOTO_2)))
            .andExpect(jsonPath("$.photo2Legende").value(DEFAULT_PHOTO_2_LEGENDE))
            .andExpect(jsonPath("$.photo2Description").value(DEFAULT_PHOTO_2_DESCRIPTION))
            .andExpect(jsonPath("$.photo3ContentType").value(DEFAULT_PHOTO_3_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo3").value(Base64Utils.encodeToString(DEFAULT_PHOTO_3)))
            .andExpect(jsonPath("$.photo3Legende").value(DEFAULT_PHOTO_3_LEGENDE))
            .andExpect(jsonPath("$.photo3Description").value(DEFAULT_PHOTO_3_DESCRIPTION))
            .andExpect(jsonPath("$.photo4ContentType").value(DEFAULT_PHOTO_4_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo4").value(Base64Utils.encodeToString(DEFAULT_PHOTO_4)))
            .andExpect(jsonPath("$.photo4Legende").value(DEFAULT_PHOTO_4_LEGENDE))
            .andExpect(jsonPath("$.photo4Description").value(DEFAULT_PHOTO_4_DESCRIPTION))
            .andExpect(jsonPath("$.photo5ContentType").value(DEFAULT_PHOTO_5_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo5").value(Base64Utils.encodeToString(DEFAULT_PHOTO_5)))
            .andExpect(jsonPath("$.photo5Legende").value(DEFAULT_PHOTO_5_LEGENDE))
            .andExpect(jsonPath("$.photo5Description").value(DEFAULT_PHOTO_5_DESCRIPTION))
            .andExpect(jsonPath("$.techniqueSecondaire").value(DEFAULT_TECHNIQUE_SECONDAIRE.booleanValue()))
            .andExpect(jsonPath("$.usageBatiment").value(DEFAULT_USAGE_BATIMENT.toString()))
            .andExpect(jsonPath("$.cout").value(DEFAULT_COUT))
            .andExpect(jsonPath("$.surfacePlancher").value(DEFAULT_SURFACE_PLANCHER))
            .andExpect(jsonPath("$.niveaux").value(DEFAULT_NIVEAUX))
            .andExpect(jsonPath("$.travauxNeuf").value(DEFAULT_TRAVAUX_NEUF.booleanValue()))
            .andExpect(jsonPath("$.travauxExtension").value(DEFAULT_TRAVAUX_EXTENSION.booleanValue()))
            .andExpect(jsonPath("$.travauxRenov").value(DEFAULT_TRAVAUX_RENOV.booleanValue()))
            .andExpect(jsonPath("$.travauxIte").value(DEFAULT_TRAVAUX_ITE.booleanValue()))
            .andExpect(jsonPath("$.travauxIti").value(DEFAULT_TRAVAUX_ITI.booleanValue()))
            .andExpect(jsonPath("$.constructionDebut").value(DEFAULT_CONSTRUCTION_DEBUT.toString()))
            .andExpect(jsonPath("$.constructionFin").value(DEFAULT_CONSTRUCTION_FIN.toString()))
            .andExpect(jsonPath("$.bottesTaille").value(DEFAULT_BOTTES_TAILLE.toString()))
            .andExpect(jsonPath("$.botteTailleAutre").value(DEFAULT_BOTTE_TAILLE_AUTRE))
            .andExpect(jsonPath("$.bottesDensite").value(DEFAULT_BOTTES_DENSITE))
            .andExpect(jsonPath("$.bottesCereale").value(DEFAULT_BOTTES_CEREALE.toString()))
            .andExpect(jsonPath("$.distanceAppro").value(DEFAULT_DISTANCE_APPRO))
            .andExpect(jsonPath("$.autoconstruction").value(DEFAULT_AUTOCONSTRUCTION.toString()))
            .andExpect(jsonPath("$.participatif").value(DEFAULT_PARTICIPATIF.toString()))
            .andExpect(jsonPath("$.structSuppl").value(DEFAULT_STRUCT_SUPPL.booleanValue()))
            .andExpect(jsonPath("$.structSupplNature").value(DEFAULT_STRUCT_SUPPL_NATURE.toString()))
            .andExpect(jsonPath("$.noteCalcul").value(DEFAULT_NOTE_CALCUL.booleanValue()))
            .andExpect(jsonPath("$.nbrRangDeBottes").value(DEFAULT_NBR_RANG_DE_BOTTES))
            .andExpect(jsonPath("$.longMaxSansMurRefend").value(DEFAULT_LONG_MAX_SANS_MUR_REFEND.doubleValue()))
            .andExpect(jsonPath("$.integBaie").value(DEFAULT_INTEG_BAIE.toString()))
            .andExpect(jsonPath("$.supportAncrage").value(DEFAULT_SUPPORT_ANCRAGE.toString()))
            .andExpect(jsonPath("$.supportAncragePrecisions").value(DEFAULT_SUPPORT_ANCRAGE_PRECISIONS))
            .andExpect(jsonPath("$.revetInt").value(DEFAULT_REVET_INT.toString()))
            .andExpect(jsonPath("$.revetExt").value(DEFAULT_REVET_EXT.toString()))
            .andExpect(jsonPath("$.revetExtAutre").value(DEFAULT_REVET_EXT_AUTRE))
            .andExpect(jsonPath("$.maitreDOuvrage").value(DEFAULT_MAITRE_D_OUVRAGE))
            .andExpect(jsonPath("$.maitreDOeuvre").value(DEFAULT_MAITRE_D_OEUVRE))
            .andExpect(jsonPath("$.architecte").value(DEFAULT_ARCHITECTE))
            .andExpect(jsonPath("$.bureauDEtudeStructure").value(DEFAULT_BUREAU_D_ETUDE_STRUCTURE))
            .andExpect(jsonPath("$.bureauControl").value(DEFAULT_BUREAU_CONTROL))
            .andExpect(jsonPath("$.entrepriseBottes").value(DEFAULT_ENTREPRISE_BOTTES))
            .andExpect(jsonPath("$.entrepriseCharpente").value(DEFAULT_ENTREPRISE_CHARPENTE))
            .andExpect(jsonPath("$.entrepriseEnduits").value(DEFAULT_ENTREPRISE_ENDUITS))
            .andExpect(jsonPath("$.descriptionProjet").value(DEFAULT_DESCRIPTION_PROJET.toString()))
            .andExpect(jsonPath("$.difficultees").value(DEFAULT_DIFFICULTEES.toString()))
            .andExpect(jsonPath("$.astuces").value(DEFAULT_ASTUCES.toString()))
            .andExpect(jsonPath("$.divers").value(DEFAULT_DIVERS.toString()))
            .andExpect(jsonPath("$.contactNom").value(DEFAULT_CONTACT_NOM))
            .andExpect(jsonPath("$.contactMail").value(DEFAULT_CONTACT_MAIL))
            .andExpect(jsonPath("$.contactPhone").value(DEFAULT_CONTACT_PHONE))
            .andExpect(jsonPath("$.codePostal").value(DEFAULT_CODE_POSTAL))
            .andExpect(jsonPath("$.nonBatimentEtPhotosPublics").value(DEFAULT_NON_BATIMENT_ET_PHOTOS_PUBLICS.booleanValue()))
            .andExpect(jsonPath("$.dateCreationFiche").value(DEFAULT_DATE_CREATION_FICHE.toString()))
            .andExpect(jsonPath("$.dateModificationFiche").value(DEFAULT_DATE_MODIFICATION_FICHE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingBatiments() throws Exception {
        // Get the batiments
        restBatimentsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBatiments() throws Exception {
        // Initialize the database
        batimentsRepository.saveAndFlush(batiments);

        int databaseSizeBeforeUpdate = batimentsRepository.findAll().size();

        // Update the batiments
        Batiments updatedBatiments = batimentsRepository.findById(batiments.getId()).get();
        // Disconnect from session so that the updates on updatedBatiments are not directly saved in db
        em.detach(updatedBatiments);
        updatedBatiments
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .nomBatiment(UPDATED_NOM_BATIMENT)
            .photoPrincipale(UPDATED_PHOTO_PRINCIPALE)
            .photoPrincipaleContentType(UPDATED_PHOTO_PRINCIPALE_CONTENT_TYPE)
            .photoPrincipaleLegende(UPDATED_PHOTO_PRINCIPALE_LEGENDE)
            .photoPrincipaleDescription(UPDATED_PHOTO_PRINCIPALE_DESCRIPTION)
            .photo1(UPDATED_PHOTO_1)
            .photo1ContentType(UPDATED_PHOTO_1_CONTENT_TYPE)
            .photo1Legende(UPDATED_PHOTO_1_LEGENDE)
            .photo1Description(UPDATED_PHOTO_1_DESCRIPTION)
            .photo2(UPDATED_PHOTO_2)
            .photo2ContentType(UPDATED_PHOTO_2_CONTENT_TYPE)
            .photo2Legende(UPDATED_PHOTO_2_LEGENDE)
            .photo2Description(UPDATED_PHOTO_2_DESCRIPTION)
            .photo3(UPDATED_PHOTO_3)
            .photo3ContentType(UPDATED_PHOTO_3_CONTENT_TYPE)
            .photo3Legende(UPDATED_PHOTO_3_LEGENDE)
            .photo3Description(UPDATED_PHOTO_3_DESCRIPTION)
            .photo4(UPDATED_PHOTO_4)
            .photo4ContentType(UPDATED_PHOTO_4_CONTENT_TYPE)
            .photo4Legende(UPDATED_PHOTO_4_LEGENDE)
            .photo4Description(UPDATED_PHOTO_4_DESCRIPTION)
            .photo5(UPDATED_PHOTO_5)
            .photo5ContentType(UPDATED_PHOTO_5_CONTENT_TYPE)
            .photo5Legende(UPDATED_PHOTO_5_LEGENDE)
            .photo5Description(UPDATED_PHOTO_5_DESCRIPTION)
            .techniqueSecondaire(UPDATED_TECHNIQUE_SECONDAIRE)
            .usageBatiment(UPDATED_USAGE_BATIMENT)
            .cout(UPDATED_COUT)
            .surfacePlancher(UPDATED_SURFACE_PLANCHER)
            .niveaux(UPDATED_NIVEAUX)
            .travauxNeuf(UPDATED_TRAVAUX_NEUF)
            .travauxExtension(UPDATED_TRAVAUX_EXTENSION)
            .travauxRenov(UPDATED_TRAVAUX_RENOV)
            .travauxIte(UPDATED_TRAVAUX_ITE)
            .travauxIti(UPDATED_TRAVAUX_ITI)
            .constructionDebut(UPDATED_CONSTRUCTION_DEBUT)
            .constructionFin(UPDATED_CONSTRUCTION_FIN)
            .bottesTaille(UPDATED_BOTTES_TAILLE)
            .botteTailleAutre(UPDATED_BOTTE_TAILLE_AUTRE)
            .bottesDensite(UPDATED_BOTTES_DENSITE)
            .bottesCereale(UPDATED_BOTTES_CEREALE)
            .distanceAppro(UPDATED_DISTANCE_APPRO)
            .autoconstruction(UPDATED_AUTOCONSTRUCTION)
            .participatif(UPDATED_PARTICIPATIF)
            .structSuppl(UPDATED_STRUCT_SUPPL)
            .structSupplNature(UPDATED_STRUCT_SUPPL_NATURE)
            .noteCalcul(UPDATED_NOTE_CALCUL)
            .nbrRangDeBottes(UPDATED_NBR_RANG_DE_BOTTES)
            .longMaxSansMurRefend(UPDATED_LONG_MAX_SANS_MUR_REFEND)
            .integBaie(UPDATED_INTEG_BAIE)
            .supportAncrage(UPDATED_SUPPORT_ANCRAGE)
            .supportAncragePrecisions(UPDATED_SUPPORT_ANCRAGE_PRECISIONS)
            .revetInt(UPDATED_REVET_INT)
            .revetExt(UPDATED_REVET_EXT)
            .revetExtAutre(UPDATED_REVET_EXT_AUTRE)
            .maitreDOuvrage(UPDATED_MAITRE_D_OUVRAGE)
            .maitreDOeuvre(UPDATED_MAITRE_D_OEUVRE)
            .architecte(UPDATED_ARCHITECTE)
            .bureauDEtudeStructure(UPDATED_BUREAU_D_ETUDE_STRUCTURE)
            .bureauControl(UPDATED_BUREAU_CONTROL)
            .entrepriseBottes(UPDATED_ENTREPRISE_BOTTES)
            .entrepriseCharpente(UPDATED_ENTREPRISE_CHARPENTE)
            .entrepriseEnduits(UPDATED_ENTREPRISE_ENDUITS)
            .descriptionProjet(UPDATED_DESCRIPTION_PROJET)
            .difficultees(UPDATED_DIFFICULTEES)
            .astuces(UPDATED_ASTUCES)
            .divers(UPDATED_DIVERS)
            .contactNom(UPDATED_CONTACT_NOM)
            .contactMail(UPDATED_CONTACT_MAIL)
            .contactPhone(UPDATED_CONTACT_PHONE)
            .codePostal(UPDATED_CODE_POSTAL)
            .nonBatimentEtPhotosPublics(UPDATED_NON_BATIMENT_ET_PHOTOS_PUBLICS)
            .dateCreationFiche(UPDATED_DATE_CREATION_FICHE)
            .dateModificationFiche(UPDATED_DATE_MODIFICATION_FICHE);

        restBatimentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBatiments.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBatiments))
            )
            .andExpect(status().isOk());

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
        Batiments testBatiments = batimentsList.get(batimentsList.size() - 1);
        assertThat(testBatiments.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testBatiments.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testBatiments.getNomBatiment()).isEqualTo(UPDATED_NOM_BATIMENT);
        assertThat(testBatiments.getPhotoPrincipale()).isEqualTo(UPDATED_PHOTO_PRINCIPALE);
        assertThat(testBatiments.getPhotoPrincipaleContentType()).isEqualTo(UPDATED_PHOTO_PRINCIPALE_CONTENT_TYPE);
        assertThat(testBatiments.getPhotoPrincipaleLegende()).isEqualTo(UPDATED_PHOTO_PRINCIPALE_LEGENDE);
        assertThat(testBatiments.getPhotoPrincipaleDescription()).isEqualTo(UPDATED_PHOTO_PRINCIPALE_DESCRIPTION);
        assertThat(testBatiments.getPhoto1()).isEqualTo(UPDATED_PHOTO_1);
        assertThat(testBatiments.getPhoto1ContentType()).isEqualTo(UPDATED_PHOTO_1_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto1Legende()).isEqualTo(UPDATED_PHOTO_1_LEGENDE);
        assertThat(testBatiments.getPhoto1Description()).isEqualTo(UPDATED_PHOTO_1_DESCRIPTION);
        assertThat(testBatiments.getPhoto2()).isEqualTo(UPDATED_PHOTO_2);
        assertThat(testBatiments.getPhoto2ContentType()).isEqualTo(UPDATED_PHOTO_2_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto2Legende()).isEqualTo(UPDATED_PHOTO_2_LEGENDE);
        assertThat(testBatiments.getPhoto2Description()).isEqualTo(UPDATED_PHOTO_2_DESCRIPTION);
        assertThat(testBatiments.getPhoto3()).isEqualTo(UPDATED_PHOTO_3);
        assertThat(testBatiments.getPhoto3ContentType()).isEqualTo(UPDATED_PHOTO_3_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto3Legende()).isEqualTo(UPDATED_PHOTO_3_LEGENDE);
        assertThat(testBatiments.getPhoto3Description()).isEqualTo(UPDATED_PHOTO_3_DESCRIPTION);
        assertThat(testBatiments.getPhoto4()).isEqualTo(UPDATED_PHOTO_4);
        assertThat(testBatiments.getPhoto4ContentType()).isEqualTo(UPDATED_PHOTO_4_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto4Legende()).isEqualTo(UPDATED_PHOTO_4_LEGENDE);
        assertThat(testBatiments.getPhoto4Description()).isEqualTo(UPDATED_PHOTO_4_DESCRIPTION);
        assertThat(testBatiments.getPhoto5()).isEqualTo(UPDATED_PHOTO_5);
        assertThat(testBatiments.getPhoto5ContentType()).isEqualTo(UPDATED_PHOTO_5_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto5Legende()).isEqualTo(UPDATED_PHOTO_5_LEGENDE);
        assertThat(testBatiments.getPhoto5Description()).isEqualTo(UPDATED_PHOTO_5_DESCRIPTION);
        assertThat(testBatiments.getTechniqueSecondaire()).isEqualTo(UPDATED_TECHNIQUE_SECONDAIRE);
        assertThat(testBatiments.getUsageBatiment()).isEqualTo(UPDATED_USAGE_BATIMENT);
        assertThat(testBatiments.getCout()).isEqualTo(UPDATED_COUT);
        assertThat(testBatiments.getSurfacePlancher()).isEqualTo(UPDATED_SURFACE_PLANCHER);
        assertThat(testBatiments.getNiveaux()).isEqualTo(UPDATED_NIVEAUX);
        assertThat(testBatiments.getTravauxNeuf()).isEqualTo(UPDATED_TRAVAUX_NEUF);
        assertThat(testBatiments.getTravauxExtension()).isEqualTo(UPDATED_TRAVAUX_EXTENSION);
        assertThat(testBatiments.getTravauxRenov()).isEqualTo(UPDATED_TRAVAUX_RENOV);
        assertThat(testBatiments.getTravauxIte()).isEqualTo(UPDATED_TRAVAUX_ITE);
        assertThat(testBatiments.getTravauxIti()).isEqualTo(UPDATED_TRAVAUX_ITI);
        assertThat(testBatiments.getConstructionDebut()).isEqualTo(UPDATED_CONSTRUCTION_DEBUT);
        assertThat(testBatiments.getConstructionFin()).isEqualTo(UPDATED_CONSTRUCTION_FIN);
        assertThat(testBatiments.getBottesTaille()).isEqualTo(UPDATED_BOTTES_TAILLE);
        assertThat(testBatiments.getBotteTailleAutre()).isEqualTo(UPDATED_BOTTE_TAILLE_AUTRE);
        assertThat(testBatiments.getBottesDensite()).isEqualTo(UPDATED_BOTTES_DENSITE);
        assertThat(testBatiments.getBottesCereale()).isEqualTo(UPDATED_BOTTES_CEREALE);
        assertThat(testBatiments.getDistanceAppro()).isEqualTo(UPDATED_DISTANCE_APPRO);
        assertThat(testBatiments.getAutoconstruction()).isEqualTo(UPDATED_AUTOCONSTRUCTION);
        assertThat(testBatiments.getParticipatif()).isEqualTo(UPDATED_PARTICIPATIF);
        assertThat(testBatiments.getStructSuppl()).isEqualTo(UPDATED_STRUCT_SUPPL);
        assertThat(testBatiments.getStructSupplNature()).isEqualTo(UPDATED_STRUCT_SUPPL_NATURE);
        assertThat(testBatiments.getNoteCalcul()).isEqualTo(UPDATED_NOTE_CALCUL);
        assertThat(testBatiments.getNbrRangDeBottes()).isEqualTo(UPDATED_NBR_RANG_DE_BOTTES);
        assertThat(testBatiments.getLongMaxSansMurRefend()).isEqualTo(UPDATED_LONG_MAX_SANS_MUR_REFEND);
        assertThat(testBatiments.getIntegBaie()).isEqualTo(UPDATED_INTEG_BAIE);
        assertThat(testBatiments.getSupportAncrage()).isEqualTo(UPDATED_SUPPORT_ANCRAGE);
        assertThat(testBatiments.getSupportAncragePrecisions()).isEqualTo(UPDATED_SUPPORT_ANCRAGE_PRECISIONS);
        assertThat(testBatiments.getRevetInt()).isEqualTo(UPDATED_REVET_INT);
        assertThat(testBatiments.getRevetExt()).isEqualTo(UPDATED_REVET_EXT);
        assertThat(testBatiments.getRevetExtAutre()).isEqualTo(UPDATED_REVET_EXT_AUTRE);
        assertThat(testBatiments.getMaitreDOuvrage()).isEqualTo(UPDATED_MAITRE_D_OUVRAGE);
        assertThat(testBatiments.getMaitreDOeuvre()).isEqualTo(UPDATED_MAITRE_D_OEUVRE);
        assertThat(testBatiments.getArchitecte()).isEqualTo(UPDATED_ARCHITECTE);
        assertThat(testBatiments.getBureauDEtudeStructure()).isEqualTo(UPDATED_BUREAU_D_ETUDE_STRUCTURE);
        assertThat(testBatiments.getBureauControl()).isEqualTo(UPDATED_BUREAU_CONTROL);
        assertThat(testBatiments.getEntrepriseBottes()).isEqualTo(UPDATED_ENTREPRISE_BOTTES);
        assertThat(testBatiments.getEntrepriseCharpente()).isEqualTo(UPDATED_ENTREPRISE_CHARPENTE);
        assertThat(testBatiments.getEntrepriseEnduits()).isEqualTo(UPDATED_ENTREPRISE_ENDUITS);
        assertThat(testBatiments.getDescriptionProjet()).isEqualTo(UPDATED_DESCRIPTION_PROJET);
        assertThat(testBatiments.getDifficultees()).isEqualTo(UPDATED_DIFFICULTEES);
        assertThat(testBatiments.getAstuces()).isEqualTo(UPDATED_ASTUCES);
        assertThat(testBatiments.getDivers()).isEqualTo(UPDATED_DIVERS);
        assertThat(testBatiments.getContactNom()).isEqualTo(UPDATED_CONTACT_NOM);
        assertThat(testBatiments.getContactMail()).isEqualTo(UPDATED_CONTACT_MAIL);
        assertThat(testBatiments.getContactPhone()).isEqualTo(UPDATED_CONTACT_PHONE);
        assertThat(testBatiments.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testBatiments.getNonBatimentEtPhotosPublics()).isEqualTo(UPDATED_NON_BATIMENT_ET_PHOTOS_PUBLICS);
        assertThat(testBatiments.getDateCreationFiche()).isEqualTo(UPDATED_DATE_CREATION_FICHE);
        assertThat(testBatiments.getDateModificationFiche()).isEqualTo(UPDATED_DATE_MODIFICATION_FICHE);
    }

    @Test
    @Transactional
    void putNonExistingBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().size();
        batiments.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBatimentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, batiments.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(batiments))
            )
            .andExpect(status().isBadRequest());

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().size();
        batiments.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBatimentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(batiments))
            )
            .andExpect(status().isBadRequest());

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().size();
        batiments.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBatimentsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(batiments))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBatimentsWithPatch() throws Exception {
        // Initialize the database
        batimentsRepository.saveAndFlush(batiments);

        int databaseSizeBeforeUpdate = batimentsRepository.findAll().size();

        // Update the batiments using partial update
        Batiments partialUpdatedBatiments = new Batiments();
        partialUpdatedBatiments.setId(batiments.getId());

        partialUpdatedBatiments
            .latitude(UPDATED_LATITUDE)
            .photoPrincipaleDescription(UPDATED_PHOTO_PRINCIPALE_DESCRIPTION)
            .photo1(UPDATED_PHOTO_1)
            .photo1ContentType(UPDATED_PHOTO_1_CONTENT_TYPE)
            .photo1Legende(UPDATED_PHOTO_1_LEGENDE)
            .photo1Description(UPDATED_PHOTO_1_DESCRIPTION)
            .photo2(UPDATED_PHOTO_2)
            .photo2ContentType(UPDATED_PHOTO_2_CONTENT_TYPE)
            .photo2Legende(UPDATED_PHOTO_2_LEGENDE)
            .photo2Description(UPDATED_PHOTO_2_DESCRIPTION)
            .photo3(UPDATED_PHOTO_3)
            .photo3ContentType(UPDATED_PHOTO_3_CONTENT_TYPE)
            .photo3Description(UPDATED_PHOTO_3_DESCRIPTION)
            .photo4(UPDATED_PHOTO_4)
            .photo4ContentType(UPDATED_PHOTO_4_CONTENT_TYPE)
            .photo4Legende(UPDATED_PHOTO_4_LEGENDE)
            .photo4Description(UPDATED_PHOTO_4_DESCRIPTION)
            .photo5(UPDATED_PHOTO_5)
            .photo5ContentType(UPDATED_PHOTO_5_CONTENT_TYPE)
            .photo5Description(UPDATED_PHOTO_5_DESCRIPTION)
            .techniqueSecondaire(UPDATED_TECHNIQUE_SECONDAIRE)
            .cout(UPDATED_COUT)
            .niveaux(UPDATED_NIVEAUX)
            .travauxNeuf(UPDATED_TRAVAUX_NEUF)
            .travauxIte(UPDATED_TRAVAUX_ITE)
            .constructionDebut(UPDATED_CONSTRUCTION_DEBUT)
            .constructionFin(UPDATED_CONSTRUCTION_FIN)
            .botteTailleAutre(UPDATED_BOTTE_TAILLE_AUTRE)
            .bottesDensite(UPDATED_BOTTES_DENSITE)
            .bottesCereale(UPDATED_BOTTES_CEREALE)
            .autoconstruction(UPDATED_AUTOCONSTRUCTION)
            .noteCalcul(UPDATED_NOTE_CALCUL)
            .nbrRangDeBottes(UPDATED_NBR_RANG_DE_BOTTES)
            .supportAncrage(UPDATED_SUPPORT_ANCRAGE)
            .supportAncragePrecisions(UPDATED_SUPPORT_ANCRAGE_PRECISIONS)
            .revetExt(UPDATED_REVET_EXT)
            .entrepriseBottes(UPDATED_ENTREPRISE_BOTTES)
            .entrepriseCharpente(UPDATED_ENTREPRISE_CHARPENTE)
            .entrepriseEnduits(UPDATED_ENTREPRISE_ENDUITS)
            .difficultees(UPDATED_DIFFICULTEES)
            .contactMail(UPDATED_CONTACT_MAIL)
            .codePostal(UPDATED_CODE_POSTAL)
            .nonBatimentEtPhotosPublics(UPDATED_NON_BATIMENT_ET_PHOTOS_PUBLICS);

        restBatimentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBatiments.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBatiments))
            )
            .andExpect(status().isOk());

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
        Batiments testBatiments = batimentsList.get(batimentsList.size() - 1);
        assertThat(testBatiments.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testBatiments.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testBatiments.getNomBatiment()).isEqualTo(DEFAULT_NOM_BATIMENT);
        assertThat(testBatiments.getPhotoPrincipale()).isEqualTo(DEFAULT_PHOTO_PRINCIPALE);
        assertThat(testBatiments.getPhotoPrincipaleContentType()).isEqualTo(DEFAULT_PHOTO_PRINCIPALE_CONTENT_TYPE);
        assertThat(testBatiments.getPhotoPrincipaleLegende()).isEqualTo(DEFAULT_PHOTO_PRINCIPALE_LEGENDE);
        assertThat(testBatiments.getPhotoPrincipaleDescription()).isEqualTo(UPDATED_PHOTO_PRINCIPALE_DESCRIPTION);
        assertThat(testBatiments.getPhoto1()).isEqualTo(UPDATED_PHOTO_1);
        assertThat(testBatiments.getPhoto1ContentType()).isEqualTo(UPDATED_PHOTO_1_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto1Legende()).isEqualTo(UPDATED_PHOTO_1_LEGENDE);
        assertThat(testBatiments.getPhoto1Description()).isEqualTo(UPDATED_PHOTO_1_DESCRIPTION);
        assertThat(testBatiments.getPhoto2()).isEqualTo(UPDATED_PHOTO_2);
        assertThat(testBatiments.getPhoto2ContentType()).isEqualTo(UPDATED_PHOTO_2_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto2Legende()).isEqualTo(UPDATED_PHOTO_2_LEGENDE);
        assertThat(testBatiments.getPhoto2Description()).isEqualTo(UPDATED_PHOTO_2_DESCRIPTION);
        assertThat(testBatiments.getPhoto3()).isEqualTo(UPDATED_PHOTO_3);
        assertThat(testBatiments.getPhoto3ContentType()).isEqualTo(UPDATED_PHOTO_3_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto3Legende()).isEqualTo(DEFAULT_PHOTO_3_LEGENDE);
        assertThat(testBatiments.getPhoto3Description()).isEqualTo(UPDATED_PHOTO_3_DESCRIPTION);
        assertThat(testBatiments.getPhoto4()).isEqualTo(UPDATED_PHOTO_4);
        assertThat(testBatiments.getPhoto4ContentType()).isEqualTo(UPDATED_PHOTO_4_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto4Legende()).isEqualTo(UPDATED_PHOTO_4_LEGENDE);
        assertThat(testBatiments.getPhoto4Description()).isEqualTo(UPDATED_PHOTO_4_DESCRIPTION);
        assertThat(testBatiments.getPhoto5()).isEqualTo(UPDATED_PHOTO_5);
        assertThat(testBatiments.getPhoto5ContentType()).isEqualTo(UPDATED_PHOTO_5_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto5Legende()).isEqualTo(DEFAULT_PHOTO_5_LEGENDE);
        assertThat(testBatiments.getPhoto5Description()).isEqualTo(UPDATED_PHOTO_5_DESCRIPTION);
        assertThat(testBatiments.getTechniqueSecondaire()).isEqualTo(UPDATED_TECHNIQUE_SECONDAIRE);
        assertThat(testBatiments.getUsageBatiment()).isEqualTo(DEFAULT_USAGE_BATIMENT);
        assertThat(testBatiments.getCout()).isEqualTo(UPDATED_COUT);
        assertThat(testBatiments.getSurfacePlancher()).isEqualTo(DEFAULT_SURFACE_PLANCHER);
        assertThat(testBatiments.getNiveaux()).isEqualTo(UPDATED_NIVEAUX);
        assertThat(testBatiments.getTravauxNeuf()).isEqualTo(UPDATED_TRAVAUX_NEUF);
        assertThat(testBatiments.getTravauxExtension()).isEqualTo(DEFAULT_TRAVAUX_EXTENSION);
        assertThat(testBatiments.getTravauxRenov()).isEqualTo(DEFAULT_TRAVAUX_RENOV);
        assertThat(testBatiments.getTravauxIte()).isEqualTo(UPDATED_TRAVAUX_ITE);
        assertThat(testBatiments.getTravauxIti()).isEqualTo(DEFAULT_TRAVAUX_ITI);
        assertThat(testBatiments.getConstructionDebut()).isEqualTo(UPDATED_CONSTRUCTION_DEBUT);
        assertThat(testBatiments.getConstructionFin()).isEqualTo(UPDATED_CONSTRUCTION_FIN);
        assertThat(testBatiments.getBottesTaille()).isEqualTo(DEFAULT_BOTTES_TAILLE);
        assertThat(testBatiments.getBotteTailleAutre()).isEqualTo(UPDATED_BOTTE_TAILLE_AUTRE);
        assertThat(testBatiments.getBottesDensite()).isEqualTo(UPDATED_BOTTES_DENSITE);
        assertThat(testBatiments.getBottesCereale()).isEqualTo(UPDATED_BOTTES_CEREALE);
        assertThat(testBatiments.getDistanceAppro()).isEqualTo(DEFAULT_DISTANCE_APPRO);
        assertThat(testBatiments.getAutoconstruction()).isEqualTo(UPDATED_AUTOCONSTRUCTION);
        assertThat(testBatiments.getParticipatif()).isEqualTo(DEFAULT_PARTICIPATIF);
        assertThat(testBatiments.getStructSuppl()).isEqualTo(DEFAULT_STRUCT_SUPPL);
        assertThat(testBatiments.getStructSupplNature()).isEqualTo(DEFAULT_STRUCT_SUPPL_NATURE);
        assertThat(testBatiments.getNoteCalcul()).isEqualTo(UPDATED_NOTE_CALCUL);
        assertThat(testBatiments.getNbrRangDeBottes()).isEqualTo(UPDATED_NBR_RANG_DE_BOTTES);
        assertThat(testBatiments.getLongMaxSansMurRefend()).isEqualTo(DEFAULT_LONG_MAX_SANS_MUR_REFEND);
        assertThat(testBatiments.getIntegBaie()).isEqualTo(DEFAULT_INTEG_BAIE);
        assertThat(testBatiments.getSupportAncrage()).isEqualTo(UPDATED_SUPPORT_ANCRAGE);
        assertThat(testBatiments.getSupportAncragePrecisions()).isEqualTo(UPDATED_SUPPORT_ANCRAGE_PRECISIONS);
        assertThat(testBatiments.getRevetInt()).isEqualTo(DEFAULT_REVET_INT);
        assertThat(testBatiments.getRevetExt()).isEqualTo(UPDATED_REVET_EXT);
        assertThat(testBatiments.getRevetExtAutre()).isEqualTo(DEFAULT_REVET_EXT_AUTRE);
        assertThat(testBatiments.getMaitreDOuvrage()).isEqualTo(DEFAULT_MAITRE_D_OUVRAGE);
        assertThat(testBatiments.getMaitreDOeuvre()).isEqualTo(DEFAULT_MAITRE_D_OEUVRE);
        assertThat(testBatiments.getArchitecte()).isEqualTo(DEFAULT_ARCHITECTE);
        assertThat(testBatiments.getBureauDEtudeStructure()).isEqualTo(DEFAULT_BUREAU_D_ETUDE_STRUCTURE);
        assertThat(testBatiments.getBureauControl()).isEqualTo(DEFAULT_BUREAU_CONTROL);
        assertThat(testBatiments.getEntrepriseBottes()).isEqualTo(UPDATED_ENTREPRISE_BOTTES);
        assertThat(testBatiments.getEntrepriseCharpente()).isEqualTo(UPDATED_ENTREPRISE_CHARPENTE);
        assertThat(testBatiments.getEntrepriseEnduits()).isEqualTo(UPDATED_ENTREPRISE_ENDUITS);
        assertThat(testBatiments.getDescriptionProjet()).isEqualTo(DEFAULT_DESCRIPTION_PROJET);
        assertThat(testBatiments.getDifficultees()).isEqualTo(UPDATED_DIFFICULTEES);
        assertThat(testBatiments.getAstuces()).isEqualTo(DEFAULT_ASTUCES);
        assertThat(testBatiments.getDivers()).isEqualTo(DEFAULT_DIVERS);
        assertThat(testBatiments.getContactNom()).isEqualTo(DEFAULT_CONTACT_NOM);
        assertThat(testBatiments.getContactMail()).isEqualTo(UPDATED_CONTACT_MAIL);
        assertThat(testBatiments.getContactPhone()).isEqualTo(DEFAULT_CONTACT_PHONE);
        assertThat(testBatiments.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testBatiments.getNonBatimentEtPhotosPublics()).isEqualTo(UPDATED_NON_BATIMENT_ET_PHOTOS_PUBLICS);
        assertThat(testBatiments.getDateCreationFiche()).isEqualTo(DEFAULT_DATE_CREATION_FICHE);
        assertThat(testBatiments.getDateModificationFiche()).isEqualTo(DEFAULT_DATE_MODIFICATION_FICHE);
    }

    @Test
    @Transactional
    void fullUpdateBatimentsWithPatch() throws Exception {
        // Initialize the database
        batimentsRepository.saveAndFlush(batiments);

        int databaseSizeBeforeUpdate = batimentsRepository.findAll().size();

        // Update the batiments using partial update
        Batiments partialUpdatedBatiments = new Batiments();
        partialUpdatedBatiments.setId(batiments.getId());

        partialUpdatedBatiments
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .nomBatiment(UPDATED_NOM_BATIMENT)
            .photoPrincipale(UPDATED_PHOTO_PRINCIPALE)
            .photoPrincipaleContentType(UPDATED_PHOTO_PRINCIPALE_CONTENT_TYPE)
            .photoPrincipaleLegende(UPDATED_PHOTO_PRINCIPALE_LEGENDE)
            .photoPrincipaleDescription(UPDATED_PHOTO_PRINCIPALE_DESCRIPTION)
            .photo1(UPDATED_PHOTO_1)
            .photo1ContentType(UPDATED_PHOTO_1_CONTENT_TYPE)
            .photo1Legende(UPDATED_PHOTO_1_LEGENDE)
            .photo1Description(UPDATED_PHOTO_1_DESCRIPTION)
            .photo2(UPDATED_PHOTO_2)
            .photo2ContentType(UPDATED_PHOTO_2_CONTENT_TYPE)
            .photo2Legende(UPDATED_PHOTO_2_LEGENDE)
            .photo2Description(UPDATED_PHOTO_2_DESCRIPTION)
            .photo3(UPDATED_PHOTO_3)
            .photo3ContentType(UPDATED_PHOTO_3_CONTENT_TYPE)
            .photo3Legende(UPDATED_PHOTO_3_LEGENDE)
            .photo3Description(UPDATED_PHOTO_3_DESCRIPTION)
            .photo4(UPDATED_PHOTO_4)
            .photo4ContentType(UPDATED_PHOTO_4_CONTENT_TYPE)
            .photo4Legende(UPDATED_PHOTO_4_LEGENDE)
            .photo4Description(UPDATED_PHOTO_4_DESCRIPTION)
            .photo5(UPDATED_PHOTO_5)
            .photo5ContentType(UPDATED_PHOTO_5_CONTENT_TYPE)
            .photo5Legende(UPDATED_PHOTO_5_LEGENDE)
            .photo5Description(UPDATED_PHOTO_5_DESCRIPTION)
            .techniqueSecondaire(UPDATED_TECHNIQUE_SECONDAIRE)
            .usageBatiment(UPDATED_USAGE_BATIMENT)
            .cout(UPDATED_COUT)
            .surfacePlancher(UPDATED_SURFACE_PLANCHER)
            .niveaux(UPDATED_NIVEAUX)
            .travauxNeuf(UPDATED_TRAVAUX_NEUF)
            .travauxExtension(UPDATED_TRAVAUX_EXTENSION)
            .travauxRenov(UPDATED_TRAVAUX_RENOV)
            .travauxIte(UPDATED_TRAVAUX_ITE)
            .travauxIti(UPDATED_TRAVAUX_ITI)
            .constructionDebut(UPDATED_CONSTRUCTION_DEBUT)
            .constructionFin(UPDATED_CONSTRUCTION_FIN)
            .bottesTaille(UPDATED_BOTTES_TAILLE)
            .botteTailleAutre(UPDATED_BOTTE_TAILLE_AUTRE)
            .bottesDensite(UPDATED_BOTTES_DENSITE)
            .bottesCereale(UPDATED_BOTTES_CEREALE)
            .distanceAppro(UPDATED_DISTANCE_APPRO)
            .autoconstruction(UPDATED_AUTOCONSTRUCTION)
            .participatif(UPDATED_PARTICIPATIF)
            .structSuppl(UPDATED_STRUCT_SUPPL)
            .structSupplNature(UPDATED_STRUCT_SUPPL_NATURE)
            .noteCalcul(UPDATED_NOTE_CALCUL)
            .nbrRangDeBottes(UPDATED_NBR_RANG_DE_BOTTES)
            .longMaxSansMurRefend(UPDATED_LONG_MAX_SANS_MUR_REFEND)
            .integBaie(UPDATED_INTEG_BAIE)
            .supportAncrage(UPDATED_SUPPORT_ANCRAGE)
            .supportAncragePrecisions(UPDATED_SUPPORT_ANCRAGE_PRECISIONS)
            .revetInt(UPDATED_REVET_INT)
            .revetExt(UPDATED_REVET_EXT)
            .revetExtAutre(UPDATED_REVET_EXT_AUTRE)
            .maitreDOuvrage(UPDATED_MAITRE_D_OUVRAGE)
            .maitreDOeuvre(UPDATED_MAITRE_D_OEUVRE)
            .architecte(UPDATED_ARCHITECTE)
            .bureauDEtudeStructure(UPDATED_BUREAU_D_ETUDE_STRUCTURE)
            .bureauControl(UPDATED_BUREAU_CONTROL)
            .entrepriseBottes(UPDATED_ENTREPRISE_BOTTES)
            .entrepriseCharpente(UPDATED_ENTREPRISE_CHARPENTE)
            .entrepriseEnduits(UPDATED_ENTREPRISE_ENDUITS)
            .descriptionProjet(UPDATED_DESCRIPTION_PROJET)
            .difficultees(UPDATED_DIFFICULTEES)
            .astuces(UPDATED_ASTUCES)
            .divers(UPDATED_DIVERS)
            .contactNom(UPDATED_CONTACT_NOM)
            .contactMail(UPDATED_CONTACT_MAIL)
            .contactPhone(UPDATED_CONTACT_PHONE)
            .codePostal(UPDATED_CODE_POSTAL)
            .nonBatimentEtPhotosPublics(UPDATED_NON_BATIMENT_ET_PHOTOS_PUBLICS)
            .dateCreationFiche(UPDATED_DATE_CREATION_FICHE)
            .dateModificationFiche(UPDATED_DATE_MODIFICATION_FICHE);

        restBatimentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBatiments.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBatiments))
            )
            .andExpect(status().isOk());

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
        Batiments testBatiments = batimentsList.get(batimentsList.size() - 1);
        assertThat(testBatiments.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testBatiments.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testBatiments.getNomBatiment()).isEqualTo(UPDATED_NOM_BATIMENT);
        assertThat(testBatiments.getPhotoPrincipale()).isEqualTo(UPDATED_PHOTO_PRINCIPALE);
        assertThat(testBatiments.getPhotoPrincipaleContentType()).isEqualTo(UPDATED_PHOTO_PRINCIPALE_CONTENT_TYPE);
        assertThat(testBatiments.getPhotoPrincipaleLegende()).isEqualTo(UPDATED_PHOTO_PRINCIPALE_LEGENDE);
        assertThat(testBatiments.getPhotoPrincipaleDescription()).isEqualTo(UPDATED_PHOTO_PRINCIPALE_DESCRIPTION);
        assertThat(testBatiments.getPhoto1()).isEqualTo(UPDATED_PHOTO_1);
        assertThat(testBatiments.getPhoto1ContentType()).isEqualTo(UPDATED_PHOTO_1_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto1Legende()).isEqualTo(UPDATED_PHOTO_1_LEGENDE);
        assertThat(testBatiments.getPhoto1Description()).isEqualTo(UPDATED_PHOTO_1_DESCRIPTION);
        assertThat(testBatiments.getPhoto2()).isEqualTo(UPDATED_PHOTO_2);
        assertThat(testBatiments.getPhoto2ContentType()).isEqualTo(UPDATED_PHOTO_2_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto2Legende()).isEqualTo(UPDATED_PHOTO_2_LEGENDE);
        assertThat(testBatiments.getPhoto2Description()).isEqualTo(UPDATED_PHOTO_2_DESCRIPTION);
        assertThat(testBatiments.getPhoto3()).isEqualTo(UPDATED_PHOTO_3);
        assertThat(testBatiments.getPhoto3ContentType()).isEqualTo(UPDATED_PHOTO_3_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto3Legende()).isEqualTo(UPDATED_PHOTO_3_LEGENDE);
        assertThat(testBatiments.getPhoto3Description()).isEqualTo(UPDATED_PHOTO_3_DESCRIPTION);
        assertThat(testBatiments.getPhoto4()).isEqualTo(UPDATED_PHOTO_4);
        assertThat(testBatiments.getPhoto4ContentType()).isEqualTo(UPDATED_PHOTO_4_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto4Legende()).isEqualTo(UPDATED_PHOTO_4_LEGENDE);
        assertThat(testBatiments.getPhoto4Description()).isEqualTo(UPDATED_PHOTO_4_DESCRIPTION);
        assertThat(testBatiments.getPhoto5()).isEqualTo(UPDATED_PHOTO_5);
        assertThat(testBatiments.getPhoto5ContentType()).isEqualTo(UPDATED_PHOTO_5_CONTENT_TYPE);
        assertThat(testBatiments.getPhoto5Legende()).isEqualTo(UPDATED_PHOTO_5_LEGENDE);
        assertThat(testBatiments.getPhoto5Description()).isEqualTo(UPDATED_PHOTO_5_DESCRIPTION);
        assertThat(testBatiments.getTechniqueSecondaire()).isEqualTo(UPDATED_TECHNIQUE_SECONDAIRE);
        assertThat(testBatiments.getUsageBatiment()).isEqualTo(UPDATED_USAGE_BATIMENT);
        assertThat(testBatiments.getCout()).isEqualTo(UPDATED_COUT);
        assertThat(testBatiments.getSurfacePlancher()).isEqualTo(UPDATED_SURFACE_PLANCHER);
        assertThat(testBatiments.getNiveaux()).isEqualTo(UPDATED_NIVEAUX);
        assertThat(testBatiments.getTravauxNeuf()).isEqualTo(UPDATED_TRAVAUX_NEUF);
        assertThat(testBatiments.getTravauxExtension()).isEqualTo(UPDATED_TRAVAUX_EXTENSION);
        assertThat(testBatiments.getTravauxRenov()).isEqualTo(UPDATED_TRAVAUX_RENOV);
        assertThat(testBatiments.getTravauxIte()).isEqualTo(UPDATED_TRAVAUX_ITE);
        assertThat(testBatiments.getTravauxIti()).isEqualTo(UPDATED_TRAVAUX_ITI);
        assertThat(testBatiments.getConstructionDebut()).isEqualTo(UPDATED_CONSTRUCTION_DEBUT);
        assertThat(testBatiments.getConstructionFin()).isEqualTo(UPDATED_CONSTRUCTION_FIN);
        assertThat(testBatiments.getBottesTaille()).isEqualTo(UPDATED_BOTTES_TAILLE);
        assertThat(testBatiments.getBotteTailleAutre()).isEqualTo(UPDATED_BOTTE_TAILLE_AUTRE);
        assertThat(testBatiments.getBottesDensite()).isEqualTo(UPDATED_BOTTES_DENSITE);
        assertThat(testBatiments.getBottesCereale()).isEqualTo(UPDATED_BOTTES_CEREALE);
        assertThat(testBatiments.getDistanceAppro()).isEqualTo(UPDATED_DISTANCE_APPRO);
        assertThat(testBatiments.getAutoconstruction()).isEqualTo(UPDATED_AUTOCONSTRUCTION);
        assertThat(testBatiments.getParticipatif()).isEqualTo(UPDATED_PARTICIPATIF);
        assertThat(testBatiments.getStructSuppl()).isEqualTo(UPDATED_STRUCT_SUPPL);
        assertThat(testBatiments.getStructSupplNature()).isEqualTo(UPDATED_STRUCT_SUPPL_NATURE);
        assertThat(testBatiments.getNoteCalcul()).isEqualTo(UPDATED_NOTE_CALCUL);
        assertThat(testBatiments.getNbrRangDeBottes()).isEqualTo(UPDATED_NBR_RANG_DE_BOTTES);
        assertThat(testBatiments.getLongMaxSansMurRefend()).isEqualTo(UPDATED_LONG_MAX_SANS_MUR_REFEND);
        assertThat(testBatiments.getIntegBaie()).isEqualTo(UPDATED_INTEG_BAIE);
        assertThat(testBatiments.getSupportAncrage()).isEqualTo(UPDATED_SUPPORT_ANCRAGE);
        assertThat(testBatiments.getSupportAncragePrecisions()).isEqualTo(UPDATED_SUPPORT_ANCRAGE_PRECISIONS);
        assertThat(testBatiments.getRevetInt()).isEqualTo(UPDATED_REVET_INT);
        assertThat(testBatiments.getRevetExt()).isEqualTo(UPDATED_REVET_EXT);
        assertThat(testBatiments.getRevetExtAutre()).isEqualTo(UPDATED_REVET_EXT_AUTRE);
        assertThat(testBatiments.getMaitreDOuvrage()).isEqualTo(UPDATED_MAITRE_D_OUVRAGE);
        assertThat(testBatiments.getMaitreDOeuvre()).isEqualTo(UPDATED_MAITRE_D_OEUVRE);
        assertThat(testBatiments.getArchitecte()).isEqualTo(UPDATED_ARCHITECTE);
        assertThat(testBatiments.getBureauDEtudeStructure()).isEqualTo(UPDATED_BUREAU_D_ETUDE_STRUCTURE);
        assertThat(testBatiments.getBureauControl()).isEqualTo(UPDATED_BUREAU_CONTROL);
        assertThat(testBatiments.getEntrepriseBottes()).isEqualTo(UPDATED_ENTREPRISE_BOTTES);
        assertThat(testBatiments.getEntrepriseCharpente()).isEqualTo(UPDATED_ENTREPRISE_CHARPENTE);
        assertThat(testBatiments.getEntrepriseEnduits()).isEqualTo(UPDATED_ENTREPRISE_ENDUITS);
        assertThat(testBatiments.getDescriptionProjet()).isEqualTo(UPDATED_DESCRIPTION_PROJET);
        assertThat(testBatiments.getDifficultees()).isEqualTo(UPDATED_DIFFICULTEES);
        assertThat(testBatiments.getAstuces()).isEqualTo(UPDATED_ASTUCES);
        assertThat(testBatiments.getDivers()).isEqualTo(UPDATED_DIVERS);
        assertThat(testBatiments.getContactNom()).isEqualTo(UPDATED_CONTACT_NOM);
        assertThat(testBatiments.getContactMail()).isEqualTo(UPDATED_CONTACT_MAIL);
        assertThat(testBatiments.getContactPhone()).isEqualTo(UPDATED_CONTACT_PHONE);
        assertThat(testBatiments.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testBatiments.getNonBatimentEtPhotosPublics()).isEqualTo(UPDATED_NON_BATIMENT_ET_PHOTOS_PUBLICS);
        assertThat(testBatiments.getDateCreationFiche()).isEqualTo(UPDATED_DATE_CREATION_FICHE);
        assertThat(testBatiments.getDateModificationFiche()).isEqualTo(UPDATED_DATE_MODIFICATION_FICHE);
    }

    @Test
    @Transactional
    void patchNonExistingBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().size();
        batiments.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBatimentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, batiments.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(batiments))
            )
            .andExpect(status().isBadRequest());

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().size();
        batiments.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBatimentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(batiments))
            )
            .andExpect(status().isBadRequest());

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().size();
        batiments.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBatimentsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(batiments))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBatiments() throws Exception {
        // Initialize the database
        batimentsRepository.saveAndFlush(batiments);

        int databaseSizeBeforeDelete = batimentsRepository.findAll().size();

        // Delete the batiments
        restBatimentsMockMvc
            .perform(delete(ENTITY_API_URL_ID, batiments.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
