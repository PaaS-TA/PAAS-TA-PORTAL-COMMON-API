package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.CatalogHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SEJI on 2018-02-20.
 */
@Repository
public interface CatalogHistoryRepository extends JpaRepository<CatalogHistory, Integer> {

    List<CatalogHistory> findAllByUserId(String userId);
    List<CatalogHistory> findAllByUserIdOrderByLastmodifiedDesc(String userId);

}
