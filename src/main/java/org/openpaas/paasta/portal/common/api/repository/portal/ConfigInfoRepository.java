package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.ConfigInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by indra on 2018-02-20.
 */
@Repository
public interface ConfigInfoRepository extends JpaRepository<ConfigInfo, Integer>, JpaSpecificationExecutor<ConfigInfo> {

    List<ConfigInfo> findAllByName(String name);

}
