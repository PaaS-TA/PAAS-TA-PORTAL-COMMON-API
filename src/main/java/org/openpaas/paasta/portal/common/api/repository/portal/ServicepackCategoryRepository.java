package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.ServicepackCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by indra on 2018-02-06.
 */
@Repository
public interface ServicepackCategoryRepository extends JpaRepository<ServicepackCategory, Integer> {
    ServicepackCategory findByNo(int no);
}
