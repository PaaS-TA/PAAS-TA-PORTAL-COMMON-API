package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.BuildpackCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by indra on 2018-02-06.
 */
@Repository
public interface BuildpackCategoryRepository extends JpaRepository<BuildpackCategory, Integer> {
    String findByBuildPackName(String buildpack_name);
    BuildpackCategory findByNo(int no);

}
