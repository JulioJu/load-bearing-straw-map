package org.lbstraw.map.repository;

import java.util.List;
import org.lbstraw.map.domain.Batiments;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Batiments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BatimentsRepository extends JpaRepository<Batiments, Long> {
    // START added by JulioJu
    @Query(
        "select ba.id as id, ba.nomBatiment as nomBatiment, ba.latitude as latitude, ba.longitude as longitude, ba.usageBatiment as usageBatiment, ba.surfacePlancher as surfacePlancher from Batiments ba"
    )
    List<org.lbstraw.map.domain.BatimentsLazyView> findAllLazy();

    @Query(nativeQuery = true, value = "select ba.creator_id as creatorId from Batiments ba where ba.id = :batId")
    List<org.lbstraw.map.domain.BatimentsWithOnlyCreatorId> findWithOnlyCreatorIds(
        @org.springframework.data.repository.query.Param("batId") Long batId
    );

    // END added by JulioJu

    @Query("select batiments from Batiments batiments where batiments.creator.login = ?#{principal.preferredUsername}")
    List<Batiments> findByCreatorIsCurrentUser();
}
