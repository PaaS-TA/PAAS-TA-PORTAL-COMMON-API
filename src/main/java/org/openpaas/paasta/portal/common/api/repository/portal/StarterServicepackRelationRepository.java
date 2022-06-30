package org.openpaas.paasta.portal.common.api.repository.portal;

import org.hibernate.criterion.Distinct;
import org.openpaas.paasta.portal.common.api.entity.portal.StarterServicepackRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by indra on 2018-03-27.
 */
@Repository
public interface StarterServicepackRelationRepository extends JpaRepository<StarterServicepackRelation, Integer> {
    List<StarterServicepackRelation> findByStarterCatalogNo(int startCatalogNo);
    List<StarterServicepackRelation> findDistinctByStarterCatalogNo(int startCatalogNo);

    @Query(value = "select distinct ssr.servicepackCategoryNo from StarterServicepackRelation ssr where ssr.starterCatalogNo = :scNo")
    List<Integer> getServicePackCategoryNoList(@Param(value = "scNo") int starterCatalogNo);
}
