package org.julioju.lbstrawmap.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.julioju.lbstrawmap.domain.Batiment;
import org.julioju.lbstrawmap.repository.BatimentRepository;
import org.julioju.lbstrawmap.service.BatimentService;
import org.julioju.lbstrawmap.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.julioju.lbstrawmap.domain.Batiment}.
 */
@RestController
@RequestMapping("/api")
public class BatimentResource {

    private final Logger log = LoggerFactory.getLogger(BatimentResource.class);

    private static final String ENTITY_NAME = "batiment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BatimentService batimentService;

    private final BatimentRepository batimentRepository;

    // START added by JulioJu
    private final org.julioju.lbstrawmap.service.UserService userService;

    public BatimentResource(
        BatimentService batimentsService,
        BatimentRepository batimentsRepository,
        org.julioju.lbstrawmap.service.UserService userService
    ) {
        this.batimentService = batimentsService;
        this.batimentRepository = batimentsRepository;
        this.userService = userService;
    }

    private org.julioju.lbstrawmap.domain.User currentUser() {
        var currentUser = userService.getUserWithAuthorities();
        if (currentUser.isEmpty()) {
            throw new BadRequestAlertException("Logged user does not exist", ENTITY_NAME, "loggedUserDoesNotExist");
        }
        return currentUser.get();
    }

    private void updateOrDeleteCheckUser(Long batimentId) {
        Long batimentCreatorId = batimentRepository.findWithOnlyCreatorIds(batimentId).get(0).getCreatedBy();
        if (!this.currentUser().getId().equals(batimentCreatorId)) {
            throw new BadRequestAlertException(
                "You are not the creator, you could not change this",
                ENTITY_NAME,
                "notAllowedAsYouAreNotTheCreator"
            );
        }
    }

    // END added by JulioJu

    /**
     * {@code POST  /batiments} : Create a new batiment.
     *
     * @param batiment the batiment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new batiment, or with status {@code 400 (Bad Request)} if the batiment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/batiments")
    public ResponseEntity<Batiment> createBatiment(@Valid @RequestBody Batiment batiment) throws URISyntaxException {
        log.debug("REST request to save Batiment : {}", batiment);
        if (batiment.getId() != null) {
            throw new BadRequestAlertException("A new batiment cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // START added by JulioJu
        batiment.setCreatedBy(this.currentUser());
        // END added by JulioJu

        Batiment result = batimentService.save(batiment);
        return ResponseEntity
            .created(new URI("/api/batiments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /batiments/:id} : Updates an existing batiment.
     *
     * @param id the id of the batiment to save.
     * @param batiment the batiment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated batiment,
     * or with status {@code 400 (Bad Request)} if the batiment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the batiment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/batiments/{id}")
    public ResponseEntity<Batiment> updateBatiment(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Batiment batiment
    ) throws URISyntaxException {
        log.debug("REST request to update Batiment : {}, {}", id, batiment);
        if (batiment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, batiment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!batimentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        // START added by JulioJu
        this.updateOrDeleteCheckUser(batiment.getId());
        batiment.setCreatedBy(null);
        // END added by JulioJu

        Batiment result = batimentService.update(batiment);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, batiment.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /batiments/:id} : Partial updates given fields of an existing batiment, field will ignore if it is null
     *
     * @param id the id of the batiment to save.
     * @param batiment the batiment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated batiment,
     * or with status {@code 400 (Bad Request)} if the batiment is not valid,
     * or with status {@code 404 (Not Found)} if the batiment is not found,
     * or with status {@code 500 (Internal Server Error)} if the batiment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/batiments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Batiment> partialUpdateBatiment(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Batiment batiment
    ) throws URISyntaxException {
        log.debug("REST request to partial update Batiment partially : {}, {}", id, batiment);
        if (batiment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, batiment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!batimentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        // START added by JulioJu
        this.updateOrDeleteCheckUser(batiment.getId());
        batiment.setCreatedBy(null);
        // END added by JulioJu

        Optional<Batiment> result = batimentService.partialUpdate(batiment);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, batiment.getId().toString())
        );
    }

    /**
     * {@code GET  /batiments} : get all the batiments.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of batiments in body.
     */
    @GetMapping("/batiments")
    public ResponseEntity<List<Batiment>> getAllBatiments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Batiments");
        Page<Batiment> page;
        if (eagerload) {
            page = batimentService.findAllWithEagerRelationships(pageable);
        } else {
            page = batimentService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    // START added by JulioJu
    /**
     * {@code GET  /batiments-lazy} : get all the batiments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of batiments in body.
     */
    @GetMapping("/batiments-lazy")
    public List<org.julioju.lbstrawmap.domain.BatimentLazyView> getAllBatimentsLazy() {
        log.debug("REST request to get all Batiments (Lazy mode)");
        return batimentRepository.findAllLazy();
    }

    // END added by JulioJu

    /**
     * {@code GET  /batiments/:id} : get the "id" batiment.
     *
     * @param id the id of the batiment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the batiment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/batiments/{id}")
    public ResponseEntity<Batiment> getBatiment(@PathVariable Long id) {
        log.debug("REST request to get Batiment : {}", id);
        Optional<Batiment> batiment = batimentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(batiment);
    }

    /**
     * {@code DELETE  /batiments/:id} : delete the "id" batiment.
     *
     * @param id the id of the batiment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/batiments/{id}")
    public ResponseEntity<Void> deleteBatiment(@PathVariable Long id) {
        log.debug("REST request to delete Batiment : {}", id);

        // START added by JulioJu
        this.updateOrDeleteCheckUser(id);
        // END added by JulioJu

        batimentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
