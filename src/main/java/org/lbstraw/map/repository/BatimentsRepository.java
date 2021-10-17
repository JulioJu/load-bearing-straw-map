package org.lbstraw.map.repository;

import org.lbstraw.map.domain.Batiments;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the Batiments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BatimentsRepository extends R2dbcRepository<Batiments, Long>, BatimentsRepositoryInternal {
    // just to avoid having unambigous methods
    @Override
    Flux<Batiments> findAll();

    @Override
    Mono<Batiments> findById(Long id);

    @Override
    <S extends Batiments> Mono<S> save(S entity);
}

interface BatimentsRepositoryInternal {
    <S extends Batiments> Mono<S> insert(S entity);
    <S extends Batiments> Mono<S> save(S entity);
    Mono<Integer> update(Batiments entity);

    Flux<Batiments> findAll();
    Mono<Batiments> findById(Long id);
    Flux<Batiments> findAllBy(Pageable pageable);
    Flux<Batiments> findAllBy(Pageable pageable, Criteria criteria);
}
