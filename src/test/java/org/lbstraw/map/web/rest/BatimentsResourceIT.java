package org.lbstraw.map.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
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
import org.lbstraw.map.domain.enumeration.MateriauSb;
import org.lbstraw.map.domain.enumeration.RevetExt;
import org.lbstraw.map.domain.enumeration.RevetInt;
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
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batiments)))
            .andExpect(status().isCreated());

        // Validate the Batiments in the database
        List<Batiments> batimentsList = batimentsRepository.findAll();
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
    @Transactional
    void createBatimentsWithExistingId() throws Exception {
        // Create the Batiments with an existing ID
        batiments.setId(1L);

        int databaseSizeBeforeCreate = batimentsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBatimentsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batiments)))
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
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batiments)))
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
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batiments)))
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
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].contactNom").value(hasItem(DEFAULT_CONTACT_NOM)))
            .andExpect(jsonPath("$.[*].contactMail").value(hasItem(DEFAULT_CONTACT_MAIL)))
            .andExpect(jsonPath("$.[*].contactPhone").value(hasItem(DEFAULT_CONTACT_PHONE)))
            .andExpect(jsonPath("$.[*].constructionDebut").value(hasItem(DEFAULT_CONSTRUCTION_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].constructionFin").value(hasItem(DEFAULT_CONSTRUCTION_FIN.toString())))
            .andExpect(jsonPath("$.[*].surface").value(hasItem(DEFAULT_SURFACE)))
            .andExpect(jsonPath("$.[*].cout").value(hasItem(DEFAULT_COUT)))
            .andExpect(jsonPath("$.[*].bottesTaille").value(hasItem(DEFAULT_BOTTES_TAILLE.toString())))
            .andExpect(jsonPath("$.[*].autoconstruction").value(hasItem(DEFAULT_AUTOCONSTRUCTION.toString())))
            .andExpect(jsonPath("$.[*].concepteur").value(hasItem(DEFAULT_CONCEPTEUR)))
            .andExpect(jsonPath("$.[*].realisateur").value(hasItem(DEFAULT_REALISATEUR)))
            .andExpect(jsonPath("$.[*].participatif").value(hasItem(DEFAULT_PARTICIPATIF.toString())))
            .andExpect(jsonPath("$.[*].usage").value(hasItem(DEFAULT_USAGE.toString())))
            .andExpect(jsonPath("$.[*].noteCalcul").value(hasItem(DEFAULT_NOTE_CALCUL.booleanValue())))
            .andExpect(jsonPath("$.[*].travauxNeuf").value(hasItem(DEFAULT_TRAVAUX_NEUF.booleanValue())))
            .andExpect(jsonPath("$.[*].travauxExtension").value(hasItem(DEFAULT_TRAVAUX_EXTENSION.booleanValue())))
            .andExpect(jsonPath("$.[*].travauxRenov").value(hasItem(DEFAULT_TRAVAUX_RENOV.booleanValue())))
            .andExpect(jsonPath("$.[*].travauxIte").value(hasItem(DEFAULT_TRAVAUX_ITE.booleanValue())))
            .andExpect(jsonPath("$.[*].travauxIti").value(hasItem(DEFAULT_TRAVAUX_ITI.booleanValue())))
            .andExpect(jsonPath("$.[*].niveaux").value(hasItem(DEFAULT_NIVEAUX)))
            .andExpect(jsonPath("$.[*].bottesDensite").value(hasItem(DEFAULT_BOTTES_DENSITE)))
            .andExpect(jsonPath("$.[*].distanceAppro").value(hasItem(DEFAULT_DISTANCE_APPRO)))
            .andExpect(jsonPath("$.[*].bottesCereale").value(hasItem(DEFAULT_BOTTES_CEREALE.toString())))
            .andExpect(jsonPath("$.[*].structSuppl").value(hasItem(DEFAULT_STRUCT_SUPPL.booleanValue())))
            .andExpect(jsonPath("$.[*].revetInt").value(hasItem(DEFAULT_REVET_INT.toString())))
            .andExpect(jsonPath("$.[*].revetExt").value(hasItem(DEFAULT_REVET_EXT.toString())))
            .andExpect(jsonPath("$.[*].techniqueSecondaire").value(hasItem(DEFAULT_TECHNIQUE_SECONDAIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].codePostal").value(hasItem(DEFAULT_CODE_POSTAL)))
            .andExpect(jsonPath("$.[*].integBaie").value(hasItem(DEFAULT_INTEG_BAIE.toString())))
            .andExpect(jsonPath("$.[*].materiauSb").value(hasItem(DEFAULT_MATERIAU_SB.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
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
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.contactNom").value(DEFAULT_CONTACT_NOM))
            .andExpect(jsonPath("$.contactMail").value(DEFAULT_CONTACT_MAIL))
            .andExpect(jsonPath("$.contactPhone").value(DEFAULT_CONTACT_PHONE))
            .andExpect(jsonPath("$.constructionDebut").value(DEFAULT_CONSTRUCTION_DEBUT.toString()))
            .andExpect(jsonPath("$.constructionFin").value(DEFAULT_CONSTRUCTION_FIN.toString()))
            .andExpect(jsonPath("$.surface").value(DEFAULT_SURFACE))
            .andExpect(jsonPath("$.cout").value(DEFAULT_COUT))
            .andExpect(jsonPath("$.bottesTaille").value(DEFAULT_BOTTES_TAILLE.toString()))
            .andExpect(jsonPath("$.autoconstruction").value(DEFAULT_AUTOCONSTRUCTION.toString()))
            .andExpect(jsonPath("$.concepteur").value(DEFAULT_CONCEPTEUR))
            .andExpect(jsonPath("$.realisateur").value(DEFAULT_REALISATEUR))
            .andExpect(jsonPath("$.participatif").value(DEFAULT_PARTICIPATIF.toString()))
            .andExpect(jsonPath("$.usage").value(DEFAULT_USAGE.toString()))
            .andExpect(jsonPath("$.noteCalcul").value(DEFAULT_NOTE_CALCUL.booleanValue()))
            .andExpect(jsonPath("$.travauxNeuf").value(DEFAULT_TRAVAUX_NEUF.booleanValue()))
            .andExpect(jsonPath("$.travauxExtension").value(DEFAULT_TRAVAUX_EXTENSION.booleanValue()))
            .andExpect(jsonPath("$.travauxRenov").value(DEFAULT_TRAVAUX_RENOV.booleanValue()))
            .andExpect(jsonPath("$.travauxIte").value(DEFAULT_TRAVAUX_ITE.booleanValue()))
            .andExpect(jsonPath("$.travauxIti").value(DEFAULT_TRAVAUX_ITI.booleanValue()))
            .andExpect(jsonPath("$.niveaux").value(DEFAULT_NIVEAUX))
            .andExpect(jsonPath("$.bottesDensite").value(DEFAULT_BOTTES_DENSITE))
            .andExpect(jsonPath("$.distanceAppro").value(DEFAULT_DISTANCE_APPRO))
            .andExpect(jsonPath("$.bottesCereale").value(DEFAULT_BOTTES_CEREALE.toString()))
            .andExpect(jsonPath("$.structSuppl").value(DEFAULT_STRUCT_SUPPL.booleanValue()))
            .andExpect(jsonPath("$.revetInt").value(DEFAULT_REVET_INT.toString()))
            .andExpect(jsonPath("$.revetExt").value(DEFAULT_REVET_EXT.toString()))
            .andExpect(jsonPath("$.techniqueSecondaire").value(DEFAULT_TECHNIQUE_SECONDAIRE.booleanValue()))
            .andExpect(jsonPath("$.codePostal").value(DEFAULT_CODE_POSTAL))
            .andExpect(jsonPath("$.integBaie").value(DEFAULT_INTEG_BAIE.toString()))
            .andExpect(jsonPath("$.materiauSb").value(DEFAULT_MATERIAU_SB.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
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

        restBatimentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBatiments.getId())
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
    @Transactional
    void putNonExistingBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().size();
        batiments.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBatimentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, batiments.getId())
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
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batiments)))
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

        restBatimentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBatiments.getId())
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

        restBatimentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBatiments.getId())
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
    @Transactional
    void patchNonExistingBatiments() throws Exception {
        int databaseSizeBeforeUpdate = batimentsRepository.findAll().size();
        batiments.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBatimentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, batiments.getId())
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
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(batiments))
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
            .perform(delete(ENTITY_API_URL_ID, batiments.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Batiments> batimentsList = batimentsRepository.findAll();
        assertThat(batimentsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
