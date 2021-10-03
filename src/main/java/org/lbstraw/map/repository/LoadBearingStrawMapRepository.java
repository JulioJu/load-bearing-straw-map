package org.lbstraw.map.repository;

import org.lbstraw.map.domain.LoadBearingStrawMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the LoadBearingStrawMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoadBearingStrawMapRepository extends R2dbcRepository<LoadBearingStrawMap, Long>, LoadBearingStrawMapRepositoryInternal {
    // just to avoid having unambigous methods
    @Override
    Flux<LoadBearingStrawMap> findAll();

    @Override
    Mono<LoadBearingStrawMap> findById(Long id);

    @Override
    <S extends LoadBearingStrawMap> Mono<S> save(S entity);
}

interface LoadBearingStrawMapRepositoryInternal {
    <S extends LoadBearingStrawMap> Mono<S> insert(S entity);
    <S extends LoadBearingStrawMap> Mono<S> save(S entity);
    Mono<Integer> update(LoadBearingStrawMap entity);

    Flux<LoadBearingStrawMap> findAll();
    Mono<LoadBearingStrawMap> findById(Long id);
    Flux<LoadBearingStrawMap> findAllBy(Pageable pageable);
    Flux<LoadBearingStrawMap> findAllBy(Pageable pageable, Criteria criteria);
}
