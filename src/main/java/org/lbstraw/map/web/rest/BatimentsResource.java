package org.lbstraw.map.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.lbstraw.map.domain.Batiments;
// START added by JulioJu
import org.lbstraw.map.domain.BatimentsLazyView;
// END added by JulioJu
import org.lbstraw.map.repository.BatimentsRepository;
import org.lbstraw.map.service.BatimentsService;
import org.lbstraw.map.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
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

    public BatimentsResource(BatimentsService batimentsService, BatimentsRepository batimentsRepository) {
        this.batimentsService = batimentsService;
        this.batimentsRepository = batimentsRepository;
    }

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

        Optional<Batiments> result = batimentsService.partialUpdate(batiments);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, batiments.getId().toString())
        );
    }

    /**
     * {@code GET  /batiments} : get all the batiments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of batiments in body.
     */
    @GetMapping("/batiments")
    public List<Batiments> getAllBatiments() {
        log.debug("REST request to get all Batiments");
        return batimentsService.findAll();
    }

    // START added by JulioJu
    /**
     * {@code GET  /batiments-lazy} : get all the batiments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of batiments in body.
     */
    @GetMapping("/batiments-lazy")
    public List<BatimentsLazyView> getAllBatimentsLazy() {
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
        batimentsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
