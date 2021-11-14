package org.julioju.lbstrawmap.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.julioju.lbstrawmap.IntegrationTest;
import org.julioju.lbstrawmap.domain.AuthHistory;
import org.julioju.lbstrawmap.repository.AuthHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AuthHistoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AuthHistoryResourceIT {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/auth-histories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AuthHistoryRepository authHistoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAuthHistoryMockMvc;

    private AuthHistory authHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuthHistory createEntity(EntityManager em) {
        AuthHistory authHistory = new AuthHistory().userId(DEFAULT_USER_ID).date(DEFAULT_DATE);
        return authHistory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuthHistory createUpdatedEntity(EntityManager em) {
        AuthHistory authHistory = new AuthHistory().userId(UPDATED_USER_ID).date(UPDATED_DATE);
        return authHistory;
    }

    @BeforeEach
    public void initTest() {
        authHistory = createEntity(em);
    }

    @Test
    @Transactional
    void createAuthHistory() throws Exception {
        int databaseSizeBeforeCreate = authHistoryRepository.findAll().size();
        // Create the AuthHistory
        restAuthHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(authHistory)))
            .andExpect(status().isCreated());

        // Validate the AuthHistory in the database
        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        AuthHistory testAuthHistory = authHistoryList.get(authHistoryList.size() - 1);
        assertThat(testAuthHistory.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testAuthHistory.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    void createAuthHistoryWithExistingId() throws Exception {
        // Create the AuthHistory with an existing ID
        authHistory.setId(1L);

        int databaseSizeBeforeCreate = authHistoryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuthHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(authHistory)))
            .andExpect(status().isBadRequest());

        // Validate the AuthHistory in the database
        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = authHistoryRepository.findAll().size();
        // set the field null
        authHistory.setUserId(null);

        // Create the AuthHistory, which fails.

        restAuthHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(authHistory)))
            .andExpect(status().isBadRequest());

        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = authHistoryRepository.findAll().size();
        // set the field null
        authHistory.setDate(null);

        // Create the AuthHistory, which fails.

        restAuthHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(authHistory)))
            .andExpect(status().isBadRequest());

        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAuthHistories() throws Exception {
        // Initialize the database
        authHistoryRepository.saveAndFlush(authHistory);

        // Get all the authHistoryList
        restAuthHistoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(authHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    void getAuthHistory() throws Exception {
        // Initialize the database
        authHistoryRepository.saveAndFlush(authHistory);

        // Get the authHistory
        restAuthHistoryMockMvc
            .perform(get(ENTITY_API_URL_ID, authHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(authHistory.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAuthHistory() throws Exception {
        // Get the authHistory
        restAuthHistoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAuthHistory() throws Exception {
        // Initialize the database
        authHistoryRepository.saveAndFlush(authHistory);

        int databaseSizeBeforeUpdate = authHistoryRepository.findAll().size();

        // Update the authHistory
        AuthHistory updatedAuthHistory = authHistoryRepository.findById(authHistory.getId()).get();
        // Disconnect from session so that the updates on updatedAuthHistory are not directly saved in db
        em.detach(updatedAuthHistory);
        updatedAuthHistory.userId(UPDATED_USER_ID).date(UPDATED_DATE);

        restAuthHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAuthHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAuthHistory))
            )
            .andExpect(status().isOk());

        // Validate the AuthHistory in the database
        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeUpdate);
        AuthHistory testAuthHistory = authHistoryList.get(authHistoryList.size() - 1);
        assertThat(testAuthHistory.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testAuthHistory.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingAuthHistory() throws Exception {
        int databaseSizeBeforeUpdate = authHistoryRepository.findAll().size();
        authHistory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuthHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, authHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(authHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuthHistory in the database
        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAuthHistory() throws Exception {
        int databaseSizeBeforeUpdate = authHistoryRepository.findAll().size();
        authHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuthHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(authHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuthHistory in the database
        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAuthHistory() throws Exception {
        int databaseSizeBeforeUpdate = authHistoryRepository.findAll().size();
        authHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuthHistoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(authHistory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AuthHistory in the database
        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAuthHistoryWithPatch() throws Exception {
        // Initialize the database
        authHistoryRepository.saveAndFlush(authHistory);

        int databaseSizeBeforeUpdate = authHistoryRepository.findAll().size();

        // Update the authHistory using partial update
        AuthHistory partialUpdatedAuthHistory = new AuthHistory();
        partialUpdatedAuthHistory.setId(authHistory.getId());

        partialUpdatedAuthHistory.userId(UPDATED_USER_ID);

        restAuthHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAuthHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAuthHistory))
            )
            .andExpect(status().isOk());

        // Validate the AuthHistory in the database
        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeUpdate);
        AuthHistory testAuthHistory = authHistoryList.get(authHistoryList.size() - 1);
        assertThat(testAuthHistory.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testAuthHistory.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    void fullUpdateAuthHistoryWithPatch() throws Exception {
        // Initialize the database
        authHistoryRepository.saveAndFlush(authHistory);

        int databaseSizeBeforeUpdate = authHistoryRepository.findAll().size();

        // Update the authHistory using partial update
        AuthHistory partialUpdatedAuthHistory = new AuthHistory();
        partialUpdatedAuthHistory.setId(authHistory.getId());

        partialUpdatedAuthHistory.userId(UPDATED_USER_ID).date(UPDATED_DATE);

        restAuthHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAuthHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAuthHistory))
            )
            .andExpect(status().isOk());

        // Validate the AuthHistory in the database
        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeUpdate);
        AuthHistory testAuthHistory = authHistoryList.get(authHistoryList.size() - 1);
        assertThat(testAuthHistory.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testAuthHistory.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingAuthHistory() throws Exception {
        int databaseSizeBeforeUpdate = authHistoryRepository.findAll().size();
        authHistory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuthHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, authHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(authHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuthHistory in the database
        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAuthHistory() throws Exception {
        int databaseSizeBeforeUpdate = authHistoryRepository.findAll().size();
        authHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuthHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(authHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuthHistory in the database
        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAuthHistory() throws Exception {
        int databaseSizeBeforeUpdate = authHistoryRepository.findAll().size();
        authHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuthHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(authHistory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AuthHistory in the database
        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAuthHistory() throws Exception {
        // Initialize the database
        authHistoryRepository.saveAndFlush(authHistory);

        int databaseSizeBeforeDelete = authHistoryRepository.findAll().size();

        // Delete the authHistory
        restAuthHistoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, authHistory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AuthHistory> authHistoryList = authHistoryRepository.findAll();
        assertThat(authHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
