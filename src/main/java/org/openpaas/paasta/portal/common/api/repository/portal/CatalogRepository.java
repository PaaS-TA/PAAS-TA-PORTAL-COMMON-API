package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by SEJI on 2018-02-20.
 */
@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {

}