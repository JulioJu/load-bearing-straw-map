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
import org.lbstraw.map.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link org.lbstraw.map.domain.Batiments}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BatimentsResource {

    private final Logger log = LoggerFactory.getLogger(BatimentsResource.class);

    private static final String ENTITY_NAME = "batiments";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BatimentsRepository batimentsRepository;

    public BatimentsResource(BatimentsRepository batimentsRepository) {
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
    public Mono<ResponseEntity<Batiments>> createBatiments(@Valid @RequestBody Batiments batiments) throws URISyntaxException {
        log.debug("REST request to save Batiments : {}", batiments);
        if (batiments.getId() != null) {
            throw new BadRequestAlertException("A new batiments cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return batimentsRepository
            .save(batiments)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/batiments/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
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
    public Mono<ResponseEntity<Batiments>> updateBatiments(
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

        return batimentsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return batimentsRepository
                    .save(batiments)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                            .body(result)
                    );
            });
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
    public Mono<ResponseEntity<Batiments>> partialUpdateBatiments(
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

        return batimentsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Batiments> result = batimentsRepository
                    .findById(batiments.getId())
                    .map(existingBatiments -> {
                        if (batiments.getLatitude() != null) {
                            existingBatiments.setLatitude(batiments.getLatitude());
                        }
                        if (batiments.getLongitude() != null) {
                            existingBatiments.setLongitude(batiments.getLongitude());
                        }
                        if (batiments.getNom() != null) {
                            existingBatiments.setNom(batiments.getNom());
                        }
                        if (batiments.getContactNom() != null) {
                            existingBatiments.setContactNom(batiments.getContactNom());
                        }
                        if (batiments.getContactMail() != null) {
                            existingBatiments.setContactMail(batiments.getContactMail());
                        }
                        if (batiments.getContactPhone() != null) {
                            existingBatiments.setContactPhone(batiments.getContactPhone());
                        }
                        if (batiments.getConstructionDebut() != null) {
                            existingBatiments.setConstructionDebut(batiments.getConstructionDebut());
                        }
                        if (batiments.getConstructionFin() != null) {
                            existingBatiments.setConstructionFin(batiments.getConstructionFin());
                        }
                        if (batiments.getSurface() != null) {
                            existingBatiments.setSurface(batiments.getSurface());
                        }
                        if (batiments.getCout() != null) {
                            existingBatiments.setCout(batiments.getCout());
                        }
                        if (batiments.getBottesTaille() != null) {
                            existingBatiments.setBottesTaille(batiments.getBottesTaille());
                        }
                        if (batiments.getAutoconstruction() != null) {
                            existingBatiments.setAutoconstruction(batiments.getAutoconstruction());
                        }
                        if (batiments.getConcepteur() != null) {
                            existingBatiments.setConcepteur(batiments.getConcepteur());
                        }
                        if (batiments.getRealisateur() != null) {
                            existingBatiments.setRealisateur(batiments.getRealisateur());
                        }
                        if (batiments.getParticipatif() != null) {
                            existingBatiments.setParticipatif(batiments.getParticipatif());
                        }
                        if (batiments.getUsage() != null) {
                            existingBatiments.setUsage(batiments.getUsage());
                        }
                        if (batiments.getNoteCalcul() != null) {
                            existingBatiments.setNoteCalcul(batiments.getNoteCalcul());
                        }
                        if (batiments.getTravauxNeuf() != null) {
                            existingBatiments.setTravauxNeuf(batiments.getTravauxNeuf());
                        }
                        if (batiments.getTravauxExtension() != null) {
                            existingBatiments.setTravauxExtension(batiments.getTravauxExtension());
                        }
                        if (batiments.getTravauxRenov() != null) {
                            existingBatiments.setTravauxRenov(batiments.getTravauxRenov());
                        }
                        if (batiments.getTravauxIte() != null) {
                            existingBatiments.setTravauxIte(batiments.getTravauxIte());
                        }
                        if (batiments.getTravauxIti() != null) {
                            existingBatiments.setTravauxIti(batiments.getTravauxIti());
                        }
                        if (batiments.getNiveaux() != null) {
                            existingBatiments.setNiveaux(batiments.getNiveaux());
                        }
                        if (batiments.getBottesDensite() != null) {
                            existingBatiments.setBottesDensite(batiments.getBottesDensite());
                        }
                        if (batiments.getDistanceAppro() != null) {
                            existingBatiments.setDistanceAppro(batiments.getDistanceAppro());
                        }
                        if (batiments.getBottesCereale() != null) {
                            existingBatiments.setBottesCereale(batiments.getBottesCereale());
                        }
                        if (batiments.getStructSuppl() != null) {
                            existingBatiments.setStructSuppl(batiments.getStructSuppl());
                        }
                        if (batiments.getRevetInt() != null) {
                            existingBatiments.setRevetInt(batiments.getRevetInt());
                        }
                        if (batiments.getRevetExt() != null) {
                            existingBatiments.setRevetExt(batiments.getRevetExt());
                        }
                        if (batiments.getTechniqueSecondaire() != null) {
                            existingBatiments.setTechniqueSecondaire(batiments.getTechniqueSecondaire());
                        }
                        if (batiments.getCodePostal() != null) {
                            existingBatiments.setCodePostal(batiments.getCodePostal());
                        }
                        if (batiments.getIntegBaie() != null) {
                            existingBatiments.setIntegBaie(batiments.getIntegBaie());
                        }
                        if (batiments.getMateriauSb() != null) {
                            existingBatiments.setMateriauSb(batiments.getMateriauSb());
                        }
                        if (batiments.getDescription() != null) {
                            existingBatiments.setDescription(batiments.getDescription());
                        }

                        return existingBatiments;
                    })
                    .flatMap(batimentsRepository::save);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /batiments} : get all the batiments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of batiments in body.
     */
    @GetMapping("/batiments")
    public Mono<List<Batiments>> getAllBatiments() {
        log.debug("REST request to get all Batiments");
        return batimentsRepository.findAll().collectList();
    }

    /**
     * {@code GET  /batiments} : get all the batiments as a stream.
     * @return the {@link Flux} of batiments.
     */
    @GetMapping(value = "/batiments", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Batiments> getAllBatimentsAsStream() {
        log.debug("REST request to get all Batiments as a stream");
        return batimentsRepository.findAll();
    }

    /**
     * {@code GET  /batiments/:id} : get the "id" batiments.
     *
     * @param id the id of the batiments to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the batiments, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/batiments/{id}")
    public Mono<ResponseEntity<Batiments>> getBatiments(@PathVariable Long id) {
        log.debug("REST request to get Batiments : {}", id);
        Mono<Batiments> batiments = batimentsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(batiments);
    }

    /**
     * {@code DELETE  /batiments/:id} : delete the "id" batiments.
     *
     * @param id the id of the batiments to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/batiments/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteBatiments(@PathVariable Long id) {
        log.debug("REST request to delete Batiments : {}", id);
        return batimentsRepository
            .deleteById(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
