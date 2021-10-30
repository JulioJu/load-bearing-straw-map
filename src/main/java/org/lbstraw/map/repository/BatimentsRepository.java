package org.lbstraw.map.repository;

// START added by JulioJu
import java.util.List;
import org.lbstraw.map.domain.Batiments;
import org.lbstraw.map.domain.BatimentsLazyView;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Batiments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BatimentsRepository extends JpaRepository<Batiments, Long> {
    @Query("select ba.id as id, ba.nom as nom, ba.latitude as latitude, ba.longitude as longitude from Batiments ba")
    List<BatimentsLazyView> findAllLazy();
}
// END added by JulioJu
