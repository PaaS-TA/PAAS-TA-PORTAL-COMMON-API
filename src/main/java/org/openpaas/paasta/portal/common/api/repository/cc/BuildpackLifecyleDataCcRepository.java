package org.openpaas.paasta.portal.common.api.repository.cc;

import org.openpaas.paasta.portal.common.api.entity.cc.BuildpackLifecyleDataCc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by indra on 2018-02-06.
 */
@Repository
public interface BuildpackLifecyleDataCcRepository extends JpaRepository<BuildpackLifecyleDataCc, Integer> {
    BuildpackLifecyleDataCc findDistinctTopByAppGuid(String guid);
}
