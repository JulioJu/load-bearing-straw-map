package org.julioju.lbstrawmap.repository;

import java.util.List;
import java.util.Optional;
import org.julioju.lbstrawmap.domain.Batiment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Batiment entity.
 */
@Repository
public interface BatimentRepository extends JpaRepository<Batiment, Long> {
    // START added by JulioJu
    @Query(
        "select ba.id as id, ba.nomBatiment as nomBatiment, ba.latitude as latitude, ba.longitude as longitude, ba.usageBatiment as usageBatiment, ba.surfacePlancher as surfacePlancher from Batiment ba"
    )
    java.util.List<org.julioju.lbstrawmap.domain.BatimentLazyView> findAllLazy();

    @Query(nativeQuery = true, value = "select ba.created_by_id as createdBy from Batiment ba where ba.id = :batId")
    java.util.List<org.julioju.lbstrawmap.domain.BatimentWithOnlyCreatorId> findWithOnlyCreatorIds(
        @org.springframework.data.repository.query.Param("batId") Long batId
    );

    // END added by JulioJu

    @Query("select batiment from Batiment batiment where batiment.createdBy.login = ?#{principal.username}")
    List<Batiment> findByCreatedByIsCurrentUser();

    default Optional<Batiment> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Batiment> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Batiment> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct batiment from Batiment batiment left join fetch batiment.createdBy",
        countQuery = "select count(distinct batiment) from Batiment batiment"
    )
    Page<Batiment> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct batiment from Batiment batiment left join fetch batiment.createdBy")
    List<Batiment> findAllWithToOneRelationships();

    @Query("select batiment from Batiment batiment left join fetch batiment.createdBy where batiment.id =:id")
    Optional<Batiment> findOneWithToOneRelationships(@Param("id") Long id);
}
