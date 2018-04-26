package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.StarterCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by indra on 2018-02-06.
 */
@Repository
public interface StarterCategoryRepository extends JpaRepository<StarterCategory, Integer> {
    StarterCategory findByNo(int no);
    StarterCategory findFirstByNoAndNameContainingOrNoAndDescriptionContainingOrNoAndSummaryContaining(int no, String name, int no1,String description, int no2, String Summary);
}
