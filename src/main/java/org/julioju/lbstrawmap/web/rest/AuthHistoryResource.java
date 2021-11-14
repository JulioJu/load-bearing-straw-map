package org.julioju.lbstrawmap.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.julioju.lbstrawmap.domain.AuthHistory;
import org.julioju.lbstrawmap.repository.AuthHistoryRepository;
import org.julioju.lbstrawmap.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.julioju.lbstrawmap.domain.AuthHistory}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AuthHistoryResource {

    private final Logger log = LoggerFactory.getLogger(AuthHistoryResource.class);

    private static final String ENTITY_NAME = "authHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuthHistoryRepository authHistoryRepository;

    public AuthHistoryResource(AuthHistoryRepository authHistoryRepository) {
        this.authHistoryRepository = authHistoryRepository;
    }

    /**
     * {@code POST  /auth-histories} : Create a new authHistory.
     *
     * @param authHistory the authHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new authHistory, or with status {@code 400 (Bad Request)} if the authHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/auth-histories")
    public ResponseEntity<AuthHistory> createAuthHistory(@Valid @RequestBody AuthHistory authHistory) throws URISyntaxException {
        log.debug("REST request to save AuthHistory : {}", authHistory);
        if (authHistory.getId() != null) {
            throw new BadRequestAlertException("A new authHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuthHistory result = authHistoryRepository.save(authHistory);
        return ResponseEntity
            .created(new URI("/api/auth-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /auth-histories/:id} : Updates an existing authHistory.
     *
     * @param id the id of the authHistory to save.
     * @param authHistory the authHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated authHistory,
     * or with status {@code 400 (Bad Request)} if the authHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the authHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/auth-histories/{id}")
    public ResponseEntity<AuthHistory> updateAuthHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AuthHistory authHistory
    ) throws URISyntaxException {
        log.debug("REST request to update AuthHistory : {}, {}", id, authHistory);
        if (authHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, authHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!authHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AuthHistory result = authHistoryRepository.save(authHistory);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, authHistory.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /auth-histories/:id} : Partial updates given fields of an existing authHistory, field will ignore if it is null
     *
     * @param id the id of the authHistory to save.
     * @param authHistory the authHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated authHistory,
     * or with status {@code 400 (Bad Request)} if the authHistory is not valid,
     * or with status {@code 404 (Not Found)} if the authHistory is not found,
     * or with status {@code 500 (Internal Server Error)} if the authHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/auth-histories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AuthHistory> partialUpdateAuthHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AuthHistory authHistory
    ) throws URISyntaxException {
        log.debug("REST request to partial update AuthHistory partially : {}, {}", id, authHistory);
        if (authHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, authHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!authHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AuthHistory> result = authHistoryRepository
            .findById(authHistory.getId())
            .map(existingAuthHistory -> {
                if (authHistory.getUserId() != null) {
                    existingAuthHistory.setUserId(authHistory.getUserId());
                }
                if (authHistory.getDate() != null) {
                    existingAuthHistory.setDate(authHistory.getDate());
                }

                return existingAuthHistory;
            })
            .map(authHistoryRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, authHistory.getId().toString())
        );
    }

    /**
     * {@code GET  /auth-histories} : get all the authHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of authHistories in body.
     */
    @GetMapping("/auth-histories")
    public List<AuthHistory> getAllAuthHistories() {
        log.debug("REST request to get all AuthHistories");
        return authHistoryRepository.findAll();
    }

    /**
     * {@code GET  /auth-histories/:id} : get the "id" authHistory.
     *
     * @param id the id of the authHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the authHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/auth-histories/{id}")
    public ResponseEntity<AuthHistory> getAuthHistory(@PathVariable Long id) {
        log.debug("REST request to get AuthHistory : {}", id);
        Optional<AuthHistory> authHistory = authHistoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(authHistory);
    }

    /**
     * {@code DELETE  /auth-histories/:id} : delete the "id" authHistory.
     *
     * @param id the id of the authHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/auth-histories/{id}")
    public ResponseEntity<Void> deleteAuthHistory(@PathVariable Long id) {
        log.debug("REST request to delete AuthHistory : {}", id);
        authHistoryRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
