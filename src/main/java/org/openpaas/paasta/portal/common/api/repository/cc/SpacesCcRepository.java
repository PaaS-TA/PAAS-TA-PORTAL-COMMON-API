package org.openpaas.paasta.portal.common.api.repository.cc;

import org.openpaas.paasta.portal.common.api.entity.cc.SpacesCc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by indra on 2018-02-06.
 */
@Repository
public interface SpacesCcRepository extends JpaRepository<SpacesCc, Integer> {

    @Query(nativeQuery = true)
    List<SpacesCc> allByOrganizationIdList();

    @Query(nativeQuery = true)
    List<SpacesCc> allList();
}
