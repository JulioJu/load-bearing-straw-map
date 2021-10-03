package org.lbstraw.map.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.lbstraw.map.domain.LoadBearingStrawMap;
import org.lbstraw.map.repository.LoadBearingStrawMapRepository;
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
 * REST controller for managing {@link org.lbstraw.map.domain.LoadBearingStrawMap}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LoadBearingStrawMapResource {

    private final Logger log = LoggerFactory.getLogger(LoadBearingStrawMapResource.class);

    private static final String ENTITY_NAME = "loadBearingStrawMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoadBearingStrawMapRepository loadBearingStrawMapRepository;

    public LoadBearingStrawMapResource(LoadBearingStrawMapRepository loadBearingStrawMapRepository) {
        this.loadBearingStrawMapRepository = loadBearingStrawMapRepository;
    }

    /**
     * {@code POST  /load-bearing-straw-maps} : Create a new loadBearingStrawMap.
     *
     * @param loadBearingStrawMap the loadBearingStrawMap to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new loadBearingStrawMap, or with status {@code 400 (Bad Request)} if the loadBearingStrawMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/load-bearing-straw-maps")
    public Mono<ResponseEntity<LoadBearingStrawMap>> createLoadBearingStrawMap(@Valid @RequestBody LoadBearingStrawMap loadBearingStrawMap)
        throws URISyntaxException {
        log.debug("REST request to save LoadBearingStrawMap : {}", loadBearingStrawMap);
        if (loadBearingStrawMap.getId() != null) {
            throw new BadRequestAlertException("A new loadBearingStrawMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return loadBearingStrawMapRepository
            .save(loadBearingStrawMap)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/load-bearing-straw-maps/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /load-bearing-straw-maps/:id} : Updates an existing loadBearingStrawMap.
     *
     * @param id the id of the loadBearingStrawMap to save.
     * @param loadBearingStrawMap the loadBearingStrawMap to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loadBearingStrawMap,
     * or with status {@code 400 (Bad Request)} if the loadBearingStrawMap is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loadBearingStrawMap couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/load-bearing-straw-maps/{id}")
    public Mono<ResponseEntity<LoadBearingStrawMap>> updateLoadBearingStrawMap(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LoadBearingStrawMap loadBearingStrawMap
    ) throws URISyntaxException {
        log.debug("REST request to update LoadBearingStrawMap : {}, {}", id, loadBearingStrawMap);
        if (loadBearingStrawMap.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loadBearingStrawMap.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return loadBearingStrawMapRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return loadBearingStrawMapRepository
                    .save(loadBearingStrawMap)
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
     * {@code PATCH  /load-bearing-straw-maps/:id} : Partial updates given fields of an existing loadBearingStrawMap, field will ignore if it is null
     *
     * @param id the id of the loadBearingStrawMap to save.
     * @param loadBearingStrawMap the loadBearingStrawMap to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loadBearingStrawMap,
     * or with status {@code 400 (Bad Request)} if the loadBearingStrawMap is not valid,
     * or with status {@code 404 (Not Found)} if the loadBearingStrawMap is not found,
     * or with status {@code 500 (Internal Server Error)} if the loadBearingStrawMap couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/load-bearing-straw-maps/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<LoadBearingStrawMap>> partialUpdateLoadBearingStrawMap(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody LoadBearingStrawMap loadBearingStrawMap
    ) throws URISyntaxException {
        log.debug("REST request to partial update LoadBearingStrawMap partially : {}, {}", id, loadBearingStrawMap);
        if (loadBearingStrawMap.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loadBearingStrawMap.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return loadBearingStrawMapRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<LoadBearingStrawMap> result = loadBearingStrawMapRepository
                    .findById(loadBearingStrawMap.getId())
                    .map(existingLoadBearingStrawMap -> {
                        if (loadBearingStrawMap.getName() != null) {
                            existingLoadBearingStrawMap.setName(loadBearingStrawMap.getName());
                        }
                        if (loadBearingStrawMap.getLongitude() != null) {
                            existingLoadBearingStrawMap.setLongitude(loadBearingStrawMap.getLongitude());
                        }
                        if (loadBearingStrawMap.getLatitude() != null) {
                            existingLoadBearingStrawMap.setLatitude(loadBearingStrawMap.getLatitude());
                        }

                        return existingLoadBearingStrawMap;
                    })
                    .flatMap(loadBearingStrawMapRepository::save);

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
     * {@code GET  /load-bearing-straw-maps} : get all the loadBearingStrawMaps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loadBearingStrawMaps in body.
     */
    @GetMapping("/load-bearing-straw-maps")
    public Mono<List<LoadBearingStrawMap>> getAllLoadBearingStrawMaps() {
        log.debug("REST request to get all LoadBearingStrawMaps");
        return loadBearingStrawMapRepository.findAll().collectList();
    }

    /**
     * {@code GET  /load-bearing-straw-maps} : get all the loadBearingStrawMaps as a stream.
     * @return the {@link Flux} of loadBearingStrawMaps.
     */
    @GetMapping(value = "/load-bearing-straw-maps", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<LoadBearingStrawMap> getAllLoadBearingStrawMapsAsStream() {
        log.debug("REST request to get all LoadBearingStrawMaps as a stream");
        return loadBearingStrawMapRepository.findAll();
    }

    /**
     * {@code GET  /load-bearing-straw-maps/:id} : get the "id" loadBearingStrawMap.
     *
     * @param id the id of the loadBearingStrawMap to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loadBearingStrawMap, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/load-bearing-straw-maps/{id}")
    public Mono<ResponseEntity<LoadBearingStrawMap>> getLoadBearingStrawMap(@PathVariable Long id) {
        log.debug("REST request to get LoadBearingStrawMap : {}", id);
        Mono<LoadBearingStrawMap> loadBearingStrawMap = loadBearingStrawMapRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(loadBearingStrawMap);
    }

    /**
     * {@code DELETE  /load-bearing-straw-maps/:id} : delete the "id" loadBearingStrawMap.
     *
     * @param id the id of the loadBearingStrawMap to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/load-bearing-straw-maps/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteLoadBearingStrawMap(@PathVariable Long id) {
        log.debug("REST request to delete LoadBearingStrawMap : {}", id);
        return loadBearingStrawMapRepository
            .deleteById(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
