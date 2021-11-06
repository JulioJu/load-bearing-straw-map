package org.julioju.lbstrawmap.repository;

import java.util.List;
import org.julioju.lbstrawmap.domain.Batiment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Batiment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BatimentRepository extends JpaRepository<Batiment, Long> {
    // START added by JulioJu
    @Query(
        "select ba.id as id, ba.nomBatiment as nomBatiment, ba.latitude as latitude, ba.longitude as longitude, ba.usageBatiment as usageBatiment, ba.surfacePlancher as surfacePlancher from Batiment ba"
    )
    List<org.julioju.lbstrawmap.domain.BatimentLazyView> findAllLazy();

    @Query(nativeQuery = true, value = "select ba.creator_id as creatorId from Batiment ba where ba.id = :batId")
    List<org.julioju.lbstrawmap.domain.BatimentWithOnlyCreatorId> findWithOnlyCreatorIds(
        @org.springframework.data.repository.query.Param("batId") Long batId
    );

    @Query("select batiment from Batiment batiment where batiment.creator.login = ?#{principal.preferredUsername}")
    List<Batiment> findByCreatorIsCurrentUser();
}