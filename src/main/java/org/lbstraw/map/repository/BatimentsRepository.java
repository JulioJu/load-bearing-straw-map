package org.lbstraw.map.repository;

import org.lbstraw.map.domain.Batiments;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Batiments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BatimentsRepository extends JpaRepository<Batiments, Long> {}
