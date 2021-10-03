package org.lbstraw.map.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lbstraw.map.IntegrationTest;
import org.lbstraw.map.domain.LoadBearingStrawMap;
import org.lbstraw.map.repository.LoadBearingStrawMapRepository;
import org.lbstraw.map.service.EntityManager;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link LoadBearingStrawMapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient
@WithMockUser
class LoadBearingStrawMapResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Float DEFAULT_LATITUDE = -90F;
    private static final Float UPDATED_LATITUDE = -89F;

    private static final Float DEFAULT_LONGITUDE = -90F;
    private static final Float UPDATED_LONGITUDE = -89F;

    private static final String ENTITY_API_URL = "/api/load-bearing-straw-maps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LoadBearingStrawMapRepository loadBearingStrawMapRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private LoadBearingStrawMap loadBearingStrawMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoadBearingStrawMap createEntity(EntityManager em) {
        LoadBearingStrawMap loadBearingStrawMap = new LoadBearingStrawMap()
            .name(DEFAULT_NAME)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE);
        return loadBearingStrawMap;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoadBearingStrawMap createUpdatedEntity(EntityManager em) {
        LoadBearingStrawMap loadBearingStrawMap = new LoadBearingStrawMap()
            .name(UPDATED_NAME)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);
        return loadBearingStrawMap;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(LoadBearingStrawMap.class).block();
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
        loadBearingStrawMap = createEntity(em);
    }

    @Test
    void createLoadBearingStrawMap() throws Exception {
        int databaseSizeBeforeCreate = loadBearingStrawMapRepository.findAll().collectList().block().size();
        // Create the LoadBearingStrawMap
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(loadBearingStrawMap))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the LoadBearingStrawMap in the database
        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeCreate + 1);
        LoadBearingStrawMap testLoadBearingStrawMap = loadBearingStrawMapList.get(loadBearingStrawMapList.size() - 1);
        assertThat(testLoadBearingStrawMap.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLoadBearingStrawMap.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testLoadBearingStrawMap.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
    }

    @Test
    void createLoadBearingStrawMapWithExistingId() throws Exception {
        // Create the LoadBearingStrawMap with an existing ID
        loadBearingStrawMap.setId(1L);

        int databaseSizeBeforeCreate = loadBearingStrawMapRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(loadBearingStrawMap))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the LoadBearingStrawMap in the database
        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = loadBearingStrawMapRepository.findAll().collectList().block().size();
        // set the field null
        loadBearingStrawMap.setLatitude(null);

        // Create the LoadBearingStrawMap, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(loadBearingStrawMap))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = loadBearingStrawMapRepository.findAll().collectList().block().size();
        // set the field null
        loadBearingStrawMap.setLongitude(null);

        // Create the LoadBearingStrawMap, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(loadBearingStrawMap))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllLoadBearingStrawMapsAsStream() {
        // Initialize the database
        loadBearingStrawMapRepository.save(loadBearingStrawMap).block();

        List<LoadBearingStrawMap> loadBearingStrawMapList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(LoadBearingStrawMap.class)
            .getResponseBody()
            .filter(loadBearingStrawMap::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(loadBearingStrawMapList).isNotNull();
        assertThat(loadBearingStrawMapList).hasSize(1);
        LoadBearingStrawMap testLoadBearingStrawMap = loadBearingStrawMapList.get(0);
        assertThat(testLoadBearingStrawMap.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLoadBearingStrawMap.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testLoadBearingStrawMap.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
    }

    @Test
    void getAllLoadBearingStrawMaps() {
        // Initialize the database
        loadBearingStrawMapRepository.save(loadBearingStrawMap).block();

        // Get all the loadBearingStrawMapList
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
            .value(hasItem(loadBearingStrawMap.getId().intValue()))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME))
            .jsonPath("$.[*].latitude")
            .value(hasItem(DEFAULT_LATITUDE.doubleValue()))
            .jsonPath("$.[*].longitude")
            .value(hasItem(DEFAULT_LONGITUDE.doubleValue()));
    }

    @Test
    void getLoadBearingStrawMap() {
        // Initialize the database
        loadBearingStrawMapRepository.save(loadBearingStrawMap).block();

        // Get the loadBearingStrawMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, loadBearingStrawMap.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(loadBearingStrawMap.getId().intValue()))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME))
            .jsonPath("$.latitude")
            .value(is(DEFAULT_LATITUDE.doubleValue()))
            .jsonPath("$.longitude")
            .value(is(DEFAULT_LONGITUDE.doubleValue()));
    }

    @Test
    void getNonExistingLoadBearingStrawMap() {
        // Get the loadBearingStrawMap
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewLoadBearingStrawMap() throws Exception {
        // Initialize the database
        loadBearingStrawMapRepository.save(loadBearingStrawMap).block();

        int databaseSizeBeforeUpdate = loadBearingStrawMapRepository.findAll().collectList().block().size();

        // Update the loadBearingStrawMap
        LoadBearingStrawMap updatedLoadBearingStrawMap = loadBearingStrawMapRepository.findById(loadBearingStrawMap.getId()).block();
        updatedLoadBearingStrawMap.name(UPDATED_NAME).latitude(UPDATED_LATITUDE).longitude(UPDATED_LONGITUDE);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedLoadBearingStrawMap.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedLoadBearingStrawMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the LoadBearingStrawMap in the database
        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeUpdate);
        LoadBearingStrawMap testLoadBearingStrawMap = loadBearingStrawMapList.get(loadBearingStrawMapList.size() - 1);
        assertThat(testLoadBearingStrawMap.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLoadBearingStrawMap.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testLoadBearingStrawMap.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
    }

    @Test
    void putNonExistingLoadBearingStrawMap() throws Exception {
        int databaseSizeBeforeUpdate = loadBearingStrawMapRepository.findAll().collectList().block().size();
        loadBearingStrawMap.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, loadBearingStrawMap.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(loadBearingStrawMap))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the LoadBearingStrawMap in the database
        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchLoadBearingStrawMap() throws Exception {
        int databaseSizeBeforeUpdate = loadBearingStrawMapRepository.findAll().collectList().block().size();
        loadBearingStrawMap.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(loadBearingStrawMap))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the LoadBearingStrawMap in the database
        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamLoadBearingStrawMap() throws Exception {
        int databaseSizeBeforeUpdate = loadBearingStrawMapRepository.findAll().collectList().block().size();
        loadBearingStrawMap.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(loadBearingStrawMap))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the LoadBearingStrawMap in the database
        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateLoadBearingStrawMapWithPatch() throws Exception {
        // Initialize the database
        loadBearingStrawMapRepository.save(loadBearingStrawMap).block();

        int databaseSizeBeforeUpdate = loadBearingStrawMapRepository.findAll().collectList().block().size();

        // Update the loadBearingStrawMap using partial update
        LoadBearingStrawMap partialUpdatedLoadBearingStrawMap = new LoadBearingStrawMap();
        partialUpdatedLoadBearingStrawMap.setId(loadBearingStrawMap.getId());

        partialUpdatedLoadBearingStrawMap.latitude(UPDATED_LATITUDE).longitude(UPDATED_LONGITUDE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedLoadBearingStrawMap.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedLoadBearingStrawMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the LoadBearingStrawMap in the database
        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeUpdate);
        LoadBearingStrawMap testLoadBearingStrawMap = loadBearingStrawMapList.get(loadBearingStrawMapList.size() - 1);
        assertThat(testLoadBearingStrawMap.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLoadBearingStrawMap.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testLoadBearingStrawMap.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
    }

    @Test
    void fullUpdateLoadBearingStrawMapWithPatch() throws Exception {
        // Initialize the database
        loadBearingStrawMapRepository.save(loadBearingStrawMap).block();

        int databaseSizeBeforeUpdate = loadBearingStrawMapRepository.findAll().collectList().block().size();

        // Update the loadBearingStrawMap using partial update
        LoadBearingStrawMap partialUpdatedLoadBearingStrawMap = new LoadBearingStrawMap();
        partialUpdatedLoadBearingStrawMap.setId(loadBearingStrawMap.getId());

        partialUpdatedLoadBearingStrawMap.name(UPDATED_NAME).latitude(UPDATED_LATITUDE).longitude(UPDATED_LONGITUDE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedLoadBearingStrawMap.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedLoadBearingStrawMap))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the LoadBearingStrawMap in the database
        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeUpdate);
        LoadBearingStrawMap testLoadBearingStrawMap = loadBearingStrawMapList.get(loadBearingStrawMapList.size() - 1);
        assertThat(testLoadBearingStrawMap.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLoadBearingStrawMap.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testLoadBearingStrawMap.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
    }

    @Test
    void patchNonExistingLoadBearingStrawMap() throws Exception {
        int databaseSizeBeforeUpdate = loadBearingStrawMapRepository.findAll().collectList().block().size();
        loadBearingStrawMap.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, loadBearingStrawMap.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(loadBearingStrawMap))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the LoadBearingStrawMap in the database
        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchLoadBearingStrawMap() throws Exception {
        int databaseSizeBeforeUpdate = loadBearingStrawMapRepository.findAll().collectList().block().size();
        loadBearingStrawMap.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(loadBearingStrawMap))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the LoadBearingStrawMap in the database
        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamLoadBearingStrawMap() throws Exception {
        int databaseSizeBeforeUpdate = loadBearingStrawMapRepository.findAll().collectList().block().size();
        loadBearingStrawMap.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(loadBearingStrawMap))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the LoadBearingStrawMap in the database
        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteLoadBearingStrawMap() {
        // Initialize the database
        loadBearingStrawMapRepository.save(loadBearingStrawMap).block();

        int databaseSizeBeforeDelete = loadBearingStrawMapRepository.findAll().collectList().block().size();

        // Delete the loadBearingStrawMap
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, loadBearingStrawMap.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<LoadBearingStrawMap> loadBearingStrawMapList = loadBearingStrawMapRepository.findAll().collectList().block();
        assertThat(loadBearingStrawMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
