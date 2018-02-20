package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.AutoScalingConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by indra on 2018-02-20.
 */
@Repository
public interface AutoScalingConfigRepository extends JpaRepository<AutoScalingConfig, Integer> {

    List<AutoScalingConfig> findAllByGuid(String guid);

    List<AutoScalingConfig> findAllByOrderByNoDesc();
}
