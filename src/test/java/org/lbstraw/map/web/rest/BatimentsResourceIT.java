package org.lbstraw.map.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lbstraw.map.IntegrationTest;
import org.lbstraw.map.domain.Batiments;
import org.lbstraw.map.domain.enumeration.Cereale;
import org.lbstraw.map.domain.enumeration.IntegBaie;
import org.lbstraw.map.domain.enumeration.MateriauSb;
import org.lbstraw.map.domain.enumeration.RevetExt;
import org.lbstraw.map.domain.enumeration.RevetInt;
import org.lbstraw.map.domain.enumeration.TaillesBottes;
import org.lbstraw.map.domain.enumeration.UsageBatiment;
import org.lbstraw.map.domain.enumeration.YesNoPartial;
import org.lbstraw.map.domain.enumeration.YesNoPartial;
import org.lbstraw.map.repository.BatimentsRepository;
import org.lbstraw.map.service.EntityManager;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link BatimentsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient
@WithMockUser
class BatimentsResourceIT {

    private static final Float DEFAULT_LATITUDE = -90F;
    private static final Float UPDATED_LATITUDE = -89F;

    private static final Float DEFAULT_LONGITUDE = -90F;
    private static final Float UPDATED_LONGITUDE = -89F;

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PHONE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CONSTRUCTION_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CONSTRUCTION_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CONSTRUCTION_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CONSTRUCTION_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_SURFACE = 1;
    private static final Integer UPDATED_SURFACE = 2;

    private static final Integer DEFAULT_COUT = 1;
    private static final Integer UPDATED_COUT = 2;

    private static final TaillesBottes DEFAULT_BOTTES_TAILLE = TaillesBottes.T_80_X_120_CM;
    private static final TaillesBottes UPDATED_BOTTES_TAILLE = TaillesBottes.T_50_X_80_CM;

    private static final YesNoPartial DEFAULT_AUTOCONSTRUCTION = YesNoPartial.OUI;
    private static final YesNoPartial UPDATED_AUTOCONSTRUCTION = YesNoPartial.NON;

    private static final String DEFAULT_CONCEPTEUR = "AAAAAAAAAA";
    private static final String UPDATED_CONCEPTEUR = "BBBBBBBBBB";

    private static final String DEFAULT_REALISATEUR = "AAAAAAAAAA";
    private static final String UPDATED_REALISATEUR = "BBBBBBBBBB";

    private static final YesNoPartial DEFAULT_PARTICIPATIF = YesNoPartial.OUI;
    private static final YesNoPartial UPDATED_PARTICIPATIF = YesNoPartial.NON;

    private static final UsageBatiment DEFAULT_USAGE = UsageBatiment.LOGEMENT_COLLECTIF;
    private static final UsageBatiment UPDATED_USAGE = UsageBatiment.LOGEMENT_INDIVIDUEL;

    private static final Boolean DEFAULT_NOTE_CALCUL = false;
    private static final Boolean UPDATED_NOTE_CALCUL = true;

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

    private static final Integer DEFAULT_NIVEAUX = 1;
    private static final Integer UPDATED_NIVEAUX = 2;

    private static final Integer DEFAULT_BOTTES_DENSITE = 1;
    private static final Integer UPDATED_BOTTES_DENSITE = 2;

    private static final Integer DEFAULT_DISTANCE_APPRO = 1;
    private static final Integer UPDATED_DISTANCE_APPRO = 2;

    private static final Cereale DEFAULT_BOTTES_CEREALE = Cereale.BLE;
    private static final Cereale UPDATED_BOTTES_CEREALE = Cereale.ORGE;

    private static final Boolean DEFAULT_STRUCT_SUPPL = false;
    private static final Boolean UPDATED_STRUCT_SUPPL = true;

    private static final RevetInt DEFAULT_REVET_INT = RevetInt.PLAQUE_DE_PLATRE;
    private static final RevetInt UPDATED_REVET_INT = RevetInt.LAMBRIS;

    private static final RevetExt DEFAULT_REVET_EXT = RevetExt.BARDAGE_VENTILE;
    private static final RevetExt UPDATED_REVET_EXT = RevetExt.ENDUIT_TERRE;

    private static final Boolean DEFAULT_TECHNIQUE_SECONDAIRE = false;
    private static final Boolean UPDATED_TECHNIQUE_SECONDAIRE = true;

    private static final String DEFAULT_CODE_POSTAL = "AAAAAA";
    private static final String UPDATED_CODE_POSTAL = "BBBBBB";

    private static final IntegBaie DEFAULT_INTEG_BAIE = IntegBaie.PRE_CADRE_FLOTTANT;
    private static final IntegBaie UPDATED_INTEG_BAIE = IntegBaie.COULISSANT;

    private static final MateriauSb DEFAULT_MATERIAU_SB = MateriauSb.BETON_ARME;
    private static final MateriauSb UPDATED_MATERIAU_SB = MateriauSb.PARPAING_DE_CIMENT;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/batiments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BatimentsRepository batimentsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

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
            .nom(DEFAULT_NOM)
            .contactNom(DEFAULT_CONTACT_NOM)
            .contactMail(DEFAULT_CONTACT_MAIL)
            .contactPhone(DEFAULT_CONTACT_PHONE)
            .constructionDebut(DEFAULT_CONSTRUCTION_DEBUT)
            .constructionFin(DEFAULT_CONSTRUCTION_FIN)
            .surface(DEFAULT_SURFACE)
            .cout(DEFAULT_COUT)
            .bottesTaille(DEFAULT_BOTTES_TAILLE)
            .autoconstruction(DEFAULT_AUTOCONSTRUCTION)
            .concepteur(DEFAULT_CONCEPTEUR)
            .realisateur(DEFAULT_REALISATEUR)
            .participatif(DEFAULT_PARTICIPATIF)
            .usage(DEFAULT_USAGE)
            .noteCalcul(DEFAULT_NOTE_CALCUL)
            .travauxNeuf(DEFAULT_TRAVAUX_NEUF)
            .travauxExtension(DEFAULT_TRAVAUX_EXTENSION)
            .travauxRenov(DEFAULT_TRAVAUX_RENOV)
            .travauxIte(DEFAULT_TRAVAUX_ITE)
            .travauxIti(DEFAULT_TRAVAUX_ITI)
            .niveaux(DEFAULT_NIVEAUX)
            .bottesDensite(DEFAULT_BOTTES_DENSITE)
            .distanceAppro(DEFAULT_DISTANCE_APPRO)
            .bottesCereale(DEFAULT_BOTTES_CEREALE)
            .structSuppl(DEFAULT_STRUCT_SUPPL)
            .revetInt(DEFAULT_REVET_INT)
            .revetExt(DEFAULT_REVET_EXT)
            .techniqueSecondaire(DEFAULT_TECHNIQUE_SECONDAIRE)
            .codePostal(DEFAULT_CODE_POSTAL)
            .integBaie(DEFAULT_INTEG_BAIE)
            .materiauSb(DEFAULT_MATERIAU_SB)
            .description(DEFAULT_DESCRIPTION);
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
            .nom(UPDATED_NOM)
            .contactNom(UPDATED_CONTACT_NOM)
            .contactMail(UPDATED_CONTACT_MAIL)
            .contactPhone(UPDATED_CONTACT_PHONE)
            .constructionDebut(UPDATED_CONSTRUCTION_DEBUT)
            .constructionFin(UPDATED_CONSTRUCTION_FIN)
            .surface(UPDATED_SURFACE)
            .cout(UPDATED_COUT)
            .bottesTaille(UPDATED_BOTTES_TAILLE)
            .autoconstruction(UPDATED_AUTOCONSTRUCTION)
            .concepteur(UPDATED_CONCEPTEUR)
            .realisateur(UPDATED_REALISATEUR)
            .participatif(UPDATED_PARTICIPATIF)
            .usage(UPDATED_USAGE)
            .noteCalcul(UPDATED_NOTE_CALCUL)
            .travauxNeuf(UPDATED_TRAVAUX_NEUF)
            .travauxExtension(UPDATED_TRAVAUX_EXTENSION)
            .travauxRenov(UPDATED_TRAVAUX_RENOV)
            .travauxIte(UPDATED_TRAVAUX_ITE)
            .travauxIti(UPDATED_TRAVAUX_ITI)
            .niveaux(UPDATED_NIVEAUX)
            .bottesDensite(UPDATED_BOTTES_DENSITE)
            .distanceAppro(UPDATED_DISTANCE_APPRO)
            .bottesCereale(UPDATED_BOTTES_CEREALE)
            .structSuppl(UPDATED_STRUCT_SUPPL)
            .revetInt(UPDATED_REVET_INT)
            .revetExt(UPDATED_REVET_EXT)
            .techniqueSecondaire(UPDATED_TECHNIQUE_SECONDAIRE)
            .codePostal(UPDATED_CODE_POSTAL)
            .integBaie(UPDATED_INTEG_BAIE)
            .materiauSb(UPDATED_MATERIAU_SB)
            .description(UPDATED_DESCRIPTION);
        return batiments;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Batiments.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntities(em);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        batiments = createEntity(em);
    }

    @Test
    void createBatiments() throws Exception {
        int databaseSizeBeforeCreate = batimentsRepository.findAll().collectList().block().size();
        // Create the Batiments
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(batiments))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeCreate + 1);
        Batiments testBatiments = batimentsList.get(batimentsList.size() - 1);
        assertThat(testBatiments.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testBatiments.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testBatiments.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testBatiments.getContactNom()).isEqualTo(DEFAULT_CONTACT_NOM);
        assertThat(testBatiments.getContactMail()).isEqualTo(DEFAULT_CONTACT_MAIL);
        assertThat(testBatiments.getContactPhone()).isEqualTo(DEFAULT_CONTACT_PHONE);
        assertThat(testBatiments.getConstructionDebut()).isEqualTo(DEFAULT_CONSTRUCTION_DEBUT);
        assertThat(testBatiments.getConstructionFin()).isEqualTo(DEFAULT_CONSTRUCTION_FIN);
        assertThat(testBatiments.getSurface()).isEqualTo(DEFAULT_SURFACE);
        assertThat(testBatiments.getCout()).isEqualTo(DEFAULT_COUT);
        assertThat(testBatiments.getBottesTaille()).isEqualTo(DEFAULT_BOTTES_TAILLE);
        assertThat(testBatiments.getAutoconstruction()).isEqualTo(DEFAULT_AUTOCONSTRUCTION);
        assertThat(testBatiments.getConcepteur()).isEqualTo(DEFAULT_CONCEPTEUR);
        assertThat(testBatiments.getRealisateur()).isEqualTo(DEFAULT_REALISATEUR);
        assertThat(testBatiments.getParticipatif()).isEqualTo(DEFAULT_PARTICIPATIF);
        assertThat(testBatiments.getUsage()).isEqualTo(DEFAULT_USAGE);
        assertThat(testBatiments.getNoteCalcul()).isEqualTo(DEFAULT_NOTE_CALCUL);
        assertThat(testBatiments.getTravauxNeuf()).isEqualTo(DEFAULT_TRAVAUX_NEUF);
        assertThat(testBatiments.getTravauxExtension()).isEqualTo(DEFAULT_TRAVAUX_EXTENSION);
        assertThat(testBatiments.getTravauxRenov()).isEqualTo(DEFAULT_TRAVAUX_RENOV);
        assertThat(testBatiments.getTravauxIte()).isEqualTo(DEFAULT_TRAVAUX_ITE);
        assertThat(testBatiments.getTravauxIti()).isEqualTo(DEFAULT_TRAVAUX_ITI);
        assertThat(testBatiments.getNiveaux()).isEqualTo(DEFAULT_NIVEAUX);
        assertThat(testBatiments.getBottesDensite()).isEqualTo(DEFAULT_BOTTES_DENSITE);
        assertThat(testBatiments.getDistanceAppro()).isEqualTo(DEFAULT_DISTANCE_APPRO);
        assertThat(testBatiments.getBottesCereale()).isEqualTo(DEFAULT_BOTTES_CEREALE);
        assertThat(testBatiments.getStructSuppl()).isEqualTo(DEFAULT_STRUCT_SUPPL);
        assertThat(testBatiments.getRevetInt()).isEqualTo(DEFAULT_REVET_INT);
        assertThat(testBatiments.getRevetExt()).isEqualTo(DEFAULT_REVET_EXT);
        assertThat(testBatiments.getTechniqueSecondaire()).isEqualTo(DEFAULT_TECHNIQUE_SECONDAIRE);
        assertThat(testBatiments.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testBatiments.getIntegBaie()).isEqualTo(DEFAULT_INTEG_BAIE);
        assertThat(testBatiments.getMateriauSb()).isEqualTo(DEFAULT_MATERIAU_SB);
        assertThat(testBatiments.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    void createBatimentsWithExistingId() throws Exception {
        // Create the Batiments with an existing ID
        batiments.setId(1L);

        int databaseSizeBeforeCreate = batimentsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(batiments))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = batimentsRepository.findAll().collectList().block().size();
        // set the field null
        batiments.setLatitude(null);

        // Create the Batiments, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(batiments))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = batimentsRepository.findAll().collectList().block().size();
        // set the field null
        batiments.setLongitude(null);

        // Create the Batiments, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(batiments))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllBatimentsAsStream() {
        // Initialize the database
        batimentsRepository.save(batiments).block();

        List<Batiments> batimentsList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Batiments.class)
            .getResponseBody()
            .filter(batiments::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(batimentsList).isNotNull();
        assertThat(batimentsList).hasSize(1);
        Batiments testBatiments = batimentsList.get(0);
        assertThat(testBatiments.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testBatiments.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testBatiments.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testBatiments.getContactNom()).isEqualTo(DEFAULT_CONTACT_NOM);
        assertThat(testBatiments.getContactMail()).isEqualTo(DEFAULT_CONTACT_MAIL);
        assertThat(testBatiments.getContactPhone()).isEqualTo(DEFAULT_CONTACT_PHONE);
        assertThat(testBatiments.getConstructionDebut()).isEqualTo(DEFAULT_CONSTRUCTION_DEBUT);
        assertThat(testBatiments.getConstructionFin()).isEqualTo(DEFAULT_CONSTRUCTION_FIN);
        assertThat(testBatiments.getSurface()).isEqualTo(DEFAULT_SURFACE);
        assertThat(testBatiments.getCout()).isEqualTo(DEFAULT_COUT);
        assertThat(testBatiments.getBottesTaille()).isEqualTo(DEFAULT_BOTTES_TAILLE);
        assertThat(testBatiments.getAutoconstruction()).isEqualTo(DEFAULT_AUTOCONSTRUCTION);
        assertThat(testBatiments.getConcepteur()).isEqualTo(DEFAULT_CONCEPTEUR);
        assertThat(testBatiments.getRealisateur()).isEqualTo(DEFAULT_REALISATEUR);
        assertThat(testBatiments.getParticipatif()).isEqualTo(DEFAULT_PARTICIPATIF);
        assertThat(testBatiments.getUsage()).isEqualTo(DEFAULT_USAGE);
        assertThat(testBatiments.getNoteCalcul()).isEqualTo(DEFAULT_NOTE_CALCUL);
        assertThat(testBatiments.getTravauxNeuf()).isEqualTo(DEFAULT_TRAVAUX_NEUF);
        assertThat(testBatiments.getTravauxExtension()).isEqualTo(DEFAULT_TRAVAUX_EXTENSION);
        assertThat(testBatiments.getTravauxRenov()).isEqualTo(DEFAULT_TRAVAUX_RENOV);
        assertThat(testBatiments.getTravauxIte()).isEqualTo(DEFAULT_TRAVAUX_ITE);
        assertThat(testBatiments.getTravauxIti()).isEqualTo(DEFAULT_TRAVAUX_ITI);
        assertThat(testBatiments.getNiveaux()).isEqualTo(DEFAULT_NIVEAUX);
        assertThat(testBatiments.getBottesDensite()).isEqualTo(DEFAULT_BOTTES_DENSITE);
        assertThat(testBatiments.getDistanceAppro()).isEqualTo(DEFAULT_DISTANCE_APPRO);
        assertThat(testBatiments.getBottesCereale()).isEqualTo(DEFAULT_BOTTES_CEREALE);
        assertThat(testBatiments.getStructSuppl()).isEqualTo(DEFAULT_STRUCT_SUPPL);
        assertThat(testBatiments.getRevetInt()).isEqualTo(DEFAULT_REVET_INT);
        assertThat(testBatiments.getRevetExt()).isEqualTo(DEFAULT_REVET_EXT);
        assertThat(testBatiments.getTechniqueSecondaire()).isEqualTo(DEFAULT_TECHNIQUE_SECONDAIRE);
        assertThat(testBatiments.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testBatiments.getIntegBaie()).isEqualTo(DEFAULT_INTEG_BAIE);
        assertThat(testBatiments.getMateriauSb()).isEqualTo(DEFAULT_MATERIAU_SB);
        assertThat(testBatiments.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    void getAllBatiments() {
        // Initialize the database
        batimentsRepository.save(batiments).block();

        // Get all the batimentsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(batiments.getId().intValue()))
            .jsonPath("$.[*].latitude")
            .value(hasItem(DEFAULT_LATITUDE.doubleValue()))
            .jsonPath("$.[*].longitude")
            .value(hasItem(DEFAULT_LONGITUDE.doubleValue()))
            .jsonPath("$.[*].nom")
            .value(hasItem(DEFAULT_NOM))
            .jsonPath("$.[*].contactNom")
            .value(hasItem(DEFAULT_CONTACT_NOM))
            .jsonPath("$.[*].contactMail")
            .value(hasItem(DEFAULT_CONTACT_MAIL))
            .jsonPath("$.[*].contactPhone")
            .value(hasItem(DEFAULT_CONTACT_PHONE))
            .jsonPath("$.[*].constructionDebut")
            .value(hasItem(DEFAULT_CONSTRUCTION_DEBUT.toString()))
            .jsonPath("$.[*].constructionFin")
            .value(hasItem(DEFAULT_CONSTRUCTION_FIN.toString()))
            .jsonPath("$.[*].surface")
            .value(hasItem(DEFAULT_SURFACE))
            .jsonPath("$.[*].cout")
            .value(hasItem(DEFAULT_COUT))
            .jsonPath("$.[*].bottesTaille")
            .value(hasItem(DEFAULT_BOTTES_TAILLE.toString()))
            .jsonPath("$.[*].autoconstruction")
            .value(hasItem(DEFAULT_AUTOCONSTRUCTION.toString()))
            .jsonPath("$.[*].concepteur")
            .value(hasItem(DEFAULT_CONCEPTEUR))
            .jsonPath("$.[*].realisateur")
            .value(hasItem(DEFAULT_REALISATEUR))
            .jsonPath("$.[*].participatif")
            .value(hasItem(DEFAULT_PARTICIPATIF.toString()))
            .jsonPath("$.[*].usage")
            .value(hasItem(DEFAULT_USAGE.toString()))
            .jsonPath("$.[*].noteCalcul")
            .value(hasItem(DEFAULT_NOTE_CALCUL.booleanValue()))
            .jsonPath("$.[*].travauxNeuf")
            .value(hasItem(DEFAULT_TRAVAUX_NEUF.booleanValue()))
            .jsonPath("$.[*].travauxExtension")
            .value(hasItem(DEFAULT_TRAVAUX_EXTENSION.booleanValue()))
            .jsonPath("$.[*].travauxRenov")
            .value(hasItem(DEFAULT_TRAVAUX_RENOV.booleanValue()))
            .jsonPath("$.[*].travauxIte")
            .value(hasItem(DEFAULT_TRAVAUX_ITE.booleanValue()))
            .jsonPath("$.[*].travauxIti")
            .value(hasItem(DEFAULT_TRAVAUX_ITI.booleanValue()))
            .jsonPath("$.[*].niveaux")
            .value(hasItem(DEFAULT_NIVEAUX))
            .jsonPath("$.[*].bottesDensite")
            .value(hasItem(DEFAULT_BOTTES_DENSITE))
            .jsonPath("$.[*].distanceAppro")
            .value(hasItem(DEFAULT_DISTANCE_APPRO))
            .jsonPath("$.[*].bottesCereale")
            .value(hasItem(DEFAULT_BOTTES_CEREALE.toString()))
            .jsonPath("$.[*].structSuppl")
            .value(hasItem(DEFAULT_STRUCT_SUPPL.booleanValue()))
            .jsonPath("$.[*].revetInt")
            .value(hasItem(DEFAULT_REVET_INT.toString()))
            .jsonPath("$.[*].revetExt")
            .value(hasItem(DEFAULT_REVET_EXT.toString()))
            .jsonPath("$.[*].techniqueSecondaire")
            .value(hasItem(DEFAULT_TECHNIQUE_SECONDAIRE.booleanValue()))
            .jsonPath("$.[*].codePostal")
            .value(hasItem(DEFAULT_CODE_POSTAL))
            .jsonPath("$.[*].integBaie")
            .value(hasItem(DEFAULT_INTEG_BAIE.toString()))
            .jsonPath("$.[*].materiauSb")
            .value(hasItem(DEFAULT_MATERIAU_SB.toString()))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    void getBatiments() {
        // Initialize the database
        batimentsRepository.save(batiments).block();

        // Get the batiments
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, batiments.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(batiments.getId().intValue()))
            .jsonPath("$.latitude")
            .value(is(DEFAULT_LATITUDE.doubleValue()))
            .jsonPath("$.longitude")
            .value(is(DEFAULT_LONGITUDE.doubleValue()))
            .jsonPath("$.nom")
            .value(is(DEFAULT_NOM))
            .jsonPath("$.contactNom")
            .value(is(DEFAULT_CONTACT_NOM))
            .jsonPath("$.contactMail")
            .value(is(DEFAULT_CONTACT_MAIL))
            .jsonPath("$.contactPhone")
            .value(is(DEFAULT_CONTACT_PHONE))
            .jsonPath("$.constructionDebut")
            .value(is(DEFAULT_CONSTRUCTION_DEBUT.toString()))
            .jsonPath("$.constructionFin")
            .value(is(DEFAULT_CONSTRUCTION_FIN.toString()))
            .jsonPath("$.surface")
            .value(is(DEFAULT_SURFACE))
            .jsonPath("$.cout")
            .value(is(DEFAULT_COUT))
            .jsonPath("$.bottesTaille")
            .value(is(DEFAULT_BOTTES_TAILLE.toString()))
            .jsonPath("$.autoconstruction")
            .value(is(DEFAULT_AUTOCONSTRUCTION.toString()))
            .jsonPath("$.concepteur")
            .value(is(DEFAULT_CONCEPTEUR))
            .jsonPath("$.realisateur")
            .value(is(DEFAULT_REALISATEUR))
            .jsonPath("$.participatif")
            .value(is(DEFAULT_PARTICIPATIF.toString()))
            .jsonPath("$.usage")
            .value(is(DEFAULT_USAGE.toString()))
            .jsonPath("$.noteCalcul")
            .value(is(DEFAULT_NOTE_CALCUL.booleanValue()))
            .jsonPath("$.travauxNeuf")
            .value(is(DEFAULT_TRAVAUX_NEUF.booleanValue()))
            .jsonPath("$.travauxExtension")
            .value(is(DEFAULT_TRAVAUX_EXTENSION.booleanValue()))
            .jsonPath("$.travauxRenov")
            .value(is(DEFAULT_TRAVAUX_RENOV.booleanValue()))
            .jsonPath("$.travauxIte")
            .value(is(DEFAULT_TRAVAUX_ITE.booleanValue()))
            .jsonPath("$.travauxIti")
            .value(is(DEFAULT_TRAVAUX_ITI.booleanValue()))
            .jsonPath("$.niveaux")
            .value(is(DEFAULT_NIVEAUX))
            .jsonPath("$.bottesDensite")
            .value(is(DEFAULT_BOTTES_DENSITE))
            .jsonPath("$.distanceAppro")
            .value(is(DEFAULT_DISTANCE_APPRO))
            .jsonPath("$.bottesCereale")
            .value(is(DEFAULT_BOTTES_CEREALE.toString()))
            .jsonPath("$.structSuppl")
            .value(is(DEFAULT_STRUCT_SUPPL.booleanValue()))
            .jsonPath("$.revetInt")
            .value(is(DEFAULT_REVET_INT.toString()))
            .jsonPath("$.revetExt")
            .value(is(DEFAULT_REVET_EXT.toString()))
            .jsonPath("$.techniqueSecondaire")
            .value(is(DEFAULT_TECHNIQUE_SECONDAIRE.booleanValue()))
            .jsonPath("$.codePostal")
            .value(is(DEFAULT_CODE_POSTAL))
            .jsonPath("$.integBaie")
            .value(is(DEFAULT_INTEG_BAIE.toString()))
            .jsonPath("$.materiauSb")
            .value(is(DEFAULT_MATERIAU_SB.toString()))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    void getNonExistingBatiments() {
        // Get the batiments
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewBatiments() throws Exception {
        // Initialize the database
        batimentsRepository.save(batiments).block();

        int databaseSizeBeforeUpdate = batimentsRepository.findAll().collectList().block().size();

        // Update the batiments
        Batiments updatedBatiments = batimentsRepository.findById(batiments.getId()).block();
        updatedBatiments
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .nom(UPDATED_NOM)
            .contactNom(UPDATED_CONTACT_NOM)
            .contactMail(UPDATED_CONTACT_MAIL)
            .contactPhone(UPDATED_CONTACT_PHONE)
            .constructionDebut(UPDATED_CONSTRUCTION_DEBUT)
            .constructionFin(UPDATED_CONSTRUCTION_FIN)
            .surface(UPDATED_SURFACE)
            .cout(UPDATED_COUT)
            .bottesTaille(UPDATED_BOTTES_TAILLE)
            .autoconstruction(UPDATED_AUTOCONSTRUCTION)
            .concepteur(UPDATED_CONCEPTEUR)
            .realisateur(UPDATED_REALISATEUR)
            .participatif(UPDATED_PARTICIPATIF)
            .usage(UPDATED_USAGE)
            .noteCalcul(UPDATED_NOTE_CALCUL)
            .travauxNeuf(UPDATED_TRAVAUX_NEUF)
            .travauxExtension(UPDATED_TRAVAUX_EXTENSION)
            .travauxRenov(UPDATED_TRAVAUX_RENOV)
            .travauxIte(UPDATED_TRAVAUX_ITE)
            .travauxIti(UPDATED_TRAVAUX_ITI)
            .niveaux(UPDATED_NIVEAUX)
            .bottesDensite(UPDATED_BOTTES_DENSITE)
            .distanceAppro(UPDATED_DISTANCE_APPRO)
            .bottesCereale(UPDATED_BOTTES_CEREALE)
            .structSuppl(UPDATED_STRUCT_SUPPL)
            .revetInt(UPDATED_REVET_INT)
            .revetExt(UPDATED_REVET_EXT)
            .techniqueSecondaire(UPDATED_TECHNIQUE_SECONDAIRE)
            .codePostal(UPDATED_CODE_POSTAL)
            .integBaie(UPDATED_INTEG_BAIE)
            .materiauSb(UPDATED_MATERIAU_SB)
            .description(UPDATED_DESCRIPTION);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedBatiments.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedBatiments))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
        Batiments testBatiments = batimentsList.get(batimentsList.size() - 1);
        assertThat(testBatiments.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testBatiments.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testBatiments.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testBatiments.getContactNom()).isEqualTo(UPDATED_CONTACT_NOM);
        assertThat(testBatiments.getContactMail()).isEqualTo(UPDATED_CONTACT_MAIL);
        assertThat(testBatiments.getContactPhone()).isEqualTo(UPDATED_CONTACT_PHONE);
        assertThat(testBatiments.getConstructionDebut()).isEqualTo(UPDATED_CONSTRUCTION_DEBUT);
        assertThat(testBatiments.getConstructionFin()).isEqualTo(UPDATED_CONSTRUCTION_FIN);
        assertThat(testBatiments.getSurface()).isEqualTo(UPDATED_SURFACE);
        assertThat(testBatiments.getCout()).isEqualTo(UPDATED_COUT);
        assertThat(testBatiments.getBottesTaille()).isEqualTo(UPDATED_BOTTES_TAILLE);
        assertThat(testBatiments.getAutoconstruction()).isEqualTo(UPDATED_AUTOCONSTRUCTION);
        assertThat(testBatiments.getConcepteur()).isEqualTo(UPDATED_CONCEPTEUR);
        assertThat(testBatiments.getRealisateur()).isEqualTo(UPDATED_REALISATEUR);
        assertThat(testBatiments.getParticipatif()).isEqualTo(UPDATED_PARTICIPATIF);
        assertThat(testBatiments.getUsage()).isEqualTo(UPDATED_USAGE);
        assertThat(testBatiments.getNoteCalcul()).isEqualTo(UPDATED_NOTE_CALCUL);
        assertThat(testBatiments.getTravauxNeuf()).isEqualTo(UPDATED_TRAVAUX_NEUF);
        assertThat(testBatiments.getTravauxExtension()).isEqualTo(UPDATED_TRAVAUX_EXTENSION);
        assertThat(testBatiments.getTravauxRenov()).isEqualTo(UPDATED_TRAVAUX_RENOV);
        assertThat(testBatiments.getTravauxIte()).isEqualTo(UPDATED_TRAVAUX_ITE);
        assertThat(testBatiments.getTravauxIti()).isEqualTo(UPDATED_TRAVAUX_ITI);
        assertThat(testBatiments.getNiveaux()).isEqualTo(UPDATED_NIVEAUX);
        assertThat(testBatiments.getBottesDensite()).isEqualTo(UPDATED_BOTTES_DENSITE);
        assertThat(testBatiments.getDistanceAppro()).isEqualTo(UPDATED_DISTANCE_APPRO);
        assertThat(testBatiments.getBottesCereale()).isEqualTo(UPDATED_BOTTES_CEREALE);
        assertThat(testBatiments.getStructSuppl()).isEqualTo(UPDATED_STRUCT_SUPPL);
        assertThat(testBatiments.getRevetInt()).isEqualTo(UPDATED_REVET_INT);
        assertThat(testBatiments.getRevetExt()).isEqualTo(UPDATED_REVET_EXT);
        assertThat(testBatiments.getTechniqueSecondaire()).isEqualTo(UPDATED_TECHNIQUE_SECONDAIRE);
        assertThat(testBatiments.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testBatiments.getIntegBaie()).isEqualTo(UPDATED_INTEG_BAIE);
        assertThat(testBatiments.getMateriauSb()).isEqualTo(UPDATED_MATERIAU_SB);
        assertThat(testBatiments.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    void putNonExistingBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().collectList().block().size();
        batiments.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, batiments.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(batiments))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().collectList().block().size();
        batiments.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(batiments))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().collectList().block().size();
        batiments.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(batiments))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateBatimentsWithPatch() throws Exception {
        // Initialize the database
        batimentsRepository.save(batiments).block();

        int databaseSizeBeforeUpdate = batimentsRepository.findAll().collectList().block().size();

        // Update the batiments using partial update
        Batiments partialUpdatedBatiments = new Batiments();
        partialUpdatedBatiments.setId(batiments.getId());

        partialUpdatedBatiments
            .latitude(UPDATED_LATITUDE)
            .contactPhone(UPDATED_CONTACT_PHONE)
            .constructionDebut(UPDATED_CONSTRUCTION_DEBUT)
            .constructionFin(UPDATED_CONSTRUCTION_FIN)
            .surface(UPDATED_SURFACE)
            .cout(UPDATED_COUT)
            .bottesTaille(UPDATED_BOTTES_TAILLE)
            .autoconstruction(UPDATED_AUTOCONSTRUCTION)
            .concepteur(UPDATED_CONCEPTEUR)
            .participatif(UPDATED_PARTICIPATIF)
            .usage(UPDATED_USAGE)
            .noteCalcul(UPDATED_NOTE_CALCUL)
            .travauxNeuf(UPDATED_TRAVAUX_NEUF)
            .travauxExtension(UPDATED_TRAVAUX_EXTENSION)
            .travauxIte(UPDATED_TRAVAUX_ITE)
            .travauxIti(UPDATED_TRAVAUX_ITI)
            .bottesDensite(UPDATED_BOTTES_DENSITE)
            .bottesCereale(UPDATED_BOTTES_CEREALE)
            .structSuppl(UPDATED_STRUCT_SUPPL)
            .techniqueSecondaire(UPDATED_TECHNIQUE_SECONDAIRE)
            .integBaie(UPDATED_INTEG_BAIE)
            .materiauSb(UPDATED_MATERIAU_SB);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBatiments.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBatiments))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
        Batiments testBatiments = batimentsList.get(batimentsList.size() - 1);
        assertThat(testBatiments.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testBatiments.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testBatiments.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testBatiments.getContactNom()).isEqualTo(DEFAULT_CONTACT_NOM);
        assertThat(testBatiments.getContactMail()).isEqualTo(DEFAULT_CONTACT_MAIL);
        assertThat(testBatiments.getContactPhone()).isEqualTo(UPDATED_CONTACT_PHONE);
        assertThat(testBatiments.getConstructionDebut()).isEqualTo(UPDATED_CONSTRUCTION_DEBUT);
        assertThat(testBatiments.getConstructionFin()).isEqualTo(UPDATED_CONSTRUCTION_FIN);
        assertThat(testBatiments.getSurface()).isEqualTo(UPDATED_SURFACE);
        assertThat(testBatiments.getCout()).isEqualTo(UPDATED_COUT);
        assertThat(testBatiments.getBottesTaille()).isEqualTo(UPDATED_BOTTES_TAILLE);
        assertThat(testBatiments.getAutoconstruction()).isEqualTo(UPDATED_AUTOCONSTRUCTION);
        assertThat(testBatiments.getConcepteur()).isEqualTo(UPDATED_CONCEPTEUR);
        assertThat(testBatiments.getRealisateur()).isEqualTo(DEFAULT_REALISATEUR);
        assertThat(testBatiments.getParticipatif()).isEqualTo(UPDATED_PARTICIPATIF);
        assertThat(testBatiments.getUsage()).isEqualTo(UPDATED_USAGE);
        assertThat(testBatiments.getNoteCalcul()).isEqualTo(UPDATED_NOTE_CALCUL);
        assertThat(testBatiments.getTravauxNeuf()).isEqualTo(UPDATED_TRAVAUX_NEUF);
        assertThat(testBatiments.getTravauxExtension()).isEqualTo(UPDATED_TRAVAUX_EXTENSION);
        assertThat(testBatiments.getTravauxRenov()).isEqualTo(DEFAULT_TRAVAUX_RENOV);
        assertThat(testBatiments.getTravauxIte()).isEqualTo(UPDATED_TRAVAUX_ITE);
        assertThat(testBatiments.getTravauxIti()).isEqualTo(UPDATED_TRAVAUX_ITI);
        assertThat(testBatiments.getNiveaux()).isEqualTo(DEFAULT_NIVEAUX);
        assertThat(testBatiments.getBottesDensite()).isEqualTo(UPDATED_BOTTES_DENSITE);
        assertThat(testBatiments.getDistanceAppro()).isEqualTo(DEFAULT_DISTANCE_APPRO);
        assertThat(testBatiments.getBottesCereale()).isEqualTo(UPDATED_BOTTES_CEREALE);
        assertThat(testBatiments.getStructSuppl()).isEqualTo(UPDATED_STRUCT_SUPPL);
        assertThat(testBatiments.getRevetInt()).isEqualTo(DEFAULT_REVET_INT);
        assertThat(testBatiments.getRevetExt()).isEqualTo(DEFAULT_REVET_EXT);
        assertThat(testBatiments.getTechniqueSecondaire()).isEqualTo(UPDATED_TECHNIQUE_SECONDAIRE);
        assertThat(testBatiments.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testBatiments.getIntegBaie()).isEqualTo(UPDATED_INTEG_BAIE);
        assertThat(testBatiments.getMateriauSb()).isEqualTo(UPDATED_MATERIAU_SB);
        assertThat(testBatiments.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    void fullUpdateBatimentsWithPatch() throws Exception {
        // Initialize the database
        batimentsRepository.save(batiments).block();

        int databaseSizeBeforeUpdate = batimentsRepository.findAll().collectList().block().size();

        // Update the batiments using partial update
        Batiments partialUpdatedBatiments = new Batiments();
        partialUpdatedBatiments.setId(batiments.getId());

        partialUpdatedBatiments
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .nom(UPDATED_NOM)
            .contactNom(UPDATED_CONTACT_NOM)
            .contactMail(UPDATED_CONTACT_MAIL)
            .contactPhone(UPDATED_CONTACT_PHONE)
            .constructionDebut(UPDATED_CONSTRUCTION_DEBUT)
            .constructionFin(UPDATED_CONSTRUCTION_FIN)
            .surface(UPDATED_SURFACE)
            .cout(UPDATED_COUT)
            .bottesTaille(UPDATED_BOTTES_TAILLE)
            .autoconstruction(UPDATED_AUTOCONSTRUCTION)
            .concepteur(UPDATED_CONCEPTEUR)
            .realisateur(UPDATED_REALISATEUR)
            .participatif(UPDATED_PARTICIPATIF)
            .usage(UPDATED_USAGE)
            .noteCalcul(UPDATED_NOTE_CALCUL)
            .travauxNeuf(UPDATED_TRAVAUX_NEUF)
            .travauxExtension(UPDATED_TRAVAUX_EXTENSION)
            .travauxRenov(UPDATED_TRAVAUX_RENOV)
            .travauxIte(UPDATED_TRAVAUX_ITE)
            .travauxIti(UPDATED_TRAVAUX_ITI)
            .niveaux(UPDATED_NIVEAUX)
            .bottesDensite(UPDATED_BOTTES_DENSITE)
            .distanceAppro(UPDATED_DISTANCE_APPRO)
            .bottesCereale(UPDATED_BOTTES_CEREALE)
            .structSuppl(UPDATED_STRUCT_SUPPL)
            .revetInt(UPDATED_REVET_INT)
            .revetExt(UPDATED_REVET_EXT)
            .techniqueSecondaire(UPDATED_TECHNIQUE_SECONDAIRE)
            .codePostal(UPDATED_CODE_POSTAL)
            .integBaie(UPDATED_INTEG_BAIE)
            .materiauSb(UPDATED_MATERIAU_SB)
            .description(UPDATED_DESCRIPTION);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBatiments.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBatiments))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
        Batiments testBatiments = batimentsList.get(batimentsList.size() - 1);
        assertThat(testBatiments.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testBatiments.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testBatiments.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testBatiments.getContactNom()).isEqualTo(UPDATED_CONTACT_NOM);
        assertThat(testBatiments.getContactMail()).isEqualTo(UPDATED_CONTACT_MAIL);
        assertThat(testBatiments.getContactPhone()).isEqualTo(UPDATED_CONTACT_PHONE);
        assertThat(testBatiments.getConstructionDebut()).isEqualTo(UPDATED_CONSTRUCTION_DEBUT);
        assertThat(testBatiments.getConstructionFin()).isEqualTo(UPDATED_CONSTRUCTION_FIN);
        assertThat(testBatiments.getSurface()).isEqualTo(UPDATED_SURFACE);
        assertThat(testBatiments.getCout()).isEqualTo(UPDATED_COUT);
        assertThat(testBatiments.getBottesTaille()).isEqualTo(UPDATED_BOTTES_TAILLE);
        assertThat(testBatiments.getAutoconstruction()).isEqualTo(UPDATED_AUTOCONSTRUCTION);
        assertThat(testBatiments.getConcepteur()).isEqualTo(UPDATED_CONCEPTEUR);
        assertThat(testBatiments.getRealisateur()).isEqualTo(UPDATED_REALISATEUR);
        assertThat(testBatiments.getParticipatif()).isEqualTo(UPDATED_PARTICIPATIF);
        assertThat(testBatiments.getUsage()).isEqualTo(UPDATED_USAGE);
        assertThat(testBatiments.getNoteCalcul()).isEqualTo(UPDATED_NOTE_CALCUL);
        assertThat(testBatiments.getTravauxNeuf()).isEqualTo(UPDATED_TRAVAUX_NEUF);
        assertThat(testBatiments.getTravauxExtension()).isEqualTo(UPDATED_TRAVAUX_EXTENSION);
        assertThat(testBatiments.getTravauxRenov()).isEqualTo(UPDATED_TRAVAUX_RENOV);
        assertThat(testBatiments.getTravauxIte()).isEqualTo(UPDATED_TRAVAUX_ITE);
        assertThat(testBatiments.getTravauxIti()).isEqualTo(UPDATED_TRAVAUX_ITI);
        assertThat(testBatiments.getNiveaux()).isEqualTo(UPDATED_NIVEAUX);
        assertThat(testBatiments.getBottesDensite()).isEqualTo(UPDATED_BOTTES_DENSITE);
        assertThat(testBatiments.getDistanceAppro()).isEqualTo(UPDATED_DISTANCE_APPRO);
        assertThat(testBatiments.getBottesCereale()).isEqualTo(UPDATED_BOTTES_CEREALE);
        assertThat(testBatiments.getStructSuppl()).isEqualTo(UPDATED_STRUCT_SUPPL);
        assertThat(testBatiments.getRevetInt()).isEqualTo(UPDATED_REVET_INT);
        assertThat(testBatiments.getRevetExt()).isEqualTo(UPDATED_REVET_EXT);
        assertThat(testBatiments.getTechniqueSecondaire()).isEqualTo(UPDATED_TECHNIQUE_SECONDAIRE);
        assertThat(testBatiments.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testBatiments.getIntegBaie()).isEqualTo(UPDATED_INTEG_BAIE);
        assertThat(testBatiments.getMateriauSb()).isEqualTo(UPDATED_MATERIAU_SB);
        assertThat(testBatiments.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    void patchNonExistingBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().collectList().block().size();
        batiments.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, batiments.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(batiments))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().collectList().block().size();
        batiments.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(batiments))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().collectList().block().size();
        batiments.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(batiments))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteBatiments() {
        // Initialize the database
        batimentsRepository.save(batiments).block();

        int databaseSizeBeforeDelete = batimentsRepository.findAll().collectList().block().size();

        // Delete the batiments
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, batiments.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Batiments> batimentsList = batimentsRepository.findAll().collectList().block();
        assertThat(batimentsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
