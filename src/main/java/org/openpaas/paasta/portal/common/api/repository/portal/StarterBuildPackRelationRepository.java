package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.StarterBuildpackRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by swmoon on 2018-04-05.
 */
@Repository
public interface StarterBuildPackRelationRepository extends JpaRepository<StarterBuildpackRelation, Integer> {
    List<StarterBuildpackRelation> findByStarterCatalogNo(int no);
}
