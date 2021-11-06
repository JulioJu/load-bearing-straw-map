package org.lbstraw.map.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.lbstraw.map.domain.Batiments;
import org.lbstraw.map.repository.BatimentsRepository;
import org.lbstraw.map.service.BatimentsService;
import org.lbstraw.map.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link org.lbstraw.map.domain.Batiments}.
 */
@RestController
@RequestMapping("/api")
public class BatimentsResource {

    private final Logger log = LoggerFactory.getLogger(BatimentsResource.class);

    private static final String ENTITY_NAME = "batiments";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BatimentsService batimentsService;

    private final BatimentsRepository batimentsRepository;

    // START added by JulioJu
    private final org.lbstraw.map.service.UserService userService;

    public BatimentsResource(
        BatimentsService batimentsService,
        BatimentsRepository batimentsRepository,
        org.lbstraw.map.service.UserService userService
    ) {
        this.batimentsService = batimentsService;
        this.batimentsRepository = batimentsRepository;
        this.userService = userService;
    }

    private org.lbstraw.map.domain.User currentUser() {
        var currentUser = userService.getUserWithAuthorities();
        if (currentUser.isEmpty()) {
            throw new BadRequestAlertException("Logged user does not exist", ENTITY_NAME, "loggedUserDoesNotExist");
        }
        return currentUser.get();
    }

    private void updateOrDeleteCheckUser(Long batimentId) {
        String batimentCreatorId = batimentsRepository.findWithOnlyCreatorIds(batimentId).get(0).getCreatorId();
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
     * {@code POST  /batiments} : Create a new batiments.
     *
     * @param batiments the batiments to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new batiments, or with status {@code 400 (Bad Request)} if the batiments has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/batiments")
    public ResponseEntity<Batiments> createBatiments(@Valid @RequestBody Batiments batiments) throws URISyntaxException {
        log.debug("REST request to save Batiments : {}", batiments);
        if (batiments.getId() != null) {
            throw new BadRequestAlertException("A new batiments cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // START added by JulioJu
        batiments.setCreator(this.currentUser());
        // END added by JulioJu

        Batiments result = batimentsService.save(batiments);
        return ResponseEntity
            .created(new URI("/api/batiments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /batiments/:id} : Updates an existing batiments.
     *
     * @param id the id of the batiments to save.
     * @param batiments the batiments to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated batiments,
     * or with status {@code 400 (Bad Request)} if the batiments is not valid,
     * or with status {@code 500 (Internal Server Error)} if the batiments couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/batiments/{id}")
    public ResponseEntity<Batiments> updateBatiments(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Batiments batiments
    ) throws URISyntaxException {
        log.debug("REST request to update Batiments : {}, {}", id, batiments);
        if (batiments.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, batiments.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!batimentsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        // START added by JulioJu
        this.updateOrDeleteCheckUser(batiments.getId());
        // END added by JulioJu

        Batiments result = batimentsService.save(batiments);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, batiments.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /batiments/:id} : Partial updates given fields of an existing batiments, field will ignore if it is null
     *
     * @param id the id of the batiments to save.
     * @param batiments the batiments to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated batiments,
     * or with status {@code 400 (Bad Request)} if the batiments is not valid,
     * or with status {@code 404 (Not Found)} if the batiments is not found,
     * or with status {@code 500 (Internal Server Error)} if the batiments couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/batiments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Batiments> partialUpdateBatiments(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Batiments batiments
    ) throws URISyntaxException {
        log.debug("REST request to partial update Batiments partially : {}, {}", id, batiments);
        if (batiments.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, batiments.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!batimentsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        // START added by JulioJu
        this.updateOrDeleteCheckUser(batiments.getId());
        // END added by JulioJu

        Optional<Batiments> result = batimentsService.partialUpdate(batiments);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, batiments.getId().toString())
        );
    }

    /**
     * {@code GET  /batiments} : get all the batiments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of batiments in body.
     */
    @GetMapping("/batiments")
    public ResponseEntity<List<Batiments>> getAllBatiments(Pageable pageable) {
        log.debug("REST request to get a page of Batiments");
        Page<Batiments> page = batimentsService.findAll(pageable);
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
    public List<org.lbstraw.map.domain.BatimentsLazyView> getAllBatimentsLazy() {
        log.debug("REST request to get all Batiments (Lazy mode)");
        return batimentsRepository.findAllLazy();
    }

    // END added by JulioJu

    /**
     * {@code GET  /batiments/:id} : get the "id" batiments.
     *
     * @param id the id of the batiments to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the batiments, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/batiments/{id}")
    public ResponseEntity<Batiments> getBatiments(@PathVariable Long id) {
        log.debug("REST request to get Batiments : {}", id);
        Optional<Batiments> batiments = batimentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(batiments);
    }

    /**
     * {@code DELETE  /batiments/:id} : delete the "id" batiments.
     *
     * @param id the id of the batiments to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/batiments/{id}")
    public ResponseEntity<Void> deleteBatiments(@PathVariable Long id) {
        log.debug("REST request to delete Batiments : {}", id);

        // START added by JulioJu
        this.updateOrDeleteCheckUser(id);
        // END added by JulioJu

        batimentsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
