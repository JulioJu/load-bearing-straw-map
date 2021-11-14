package org.julioju.lbstrawmap.repository;

import org.julioju.lbstrawmap.domain.AuthHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AuthHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthHistoryRepository extends JpaRepository<AuthHistory, Long> {}
