package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.BuildpackCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by indra on 2018-02-06.
 */
@Repository
public interface BuildpackCategoryRepository extends JpaRepository<BuildpackCategory, Integer> {

//    @Query(value = "select max(bc.thumbImgPath) from BuildpackCategory bc where bc.buildpackName = ?1")
    String findByBuildpackName(String buildpack_name);
}
