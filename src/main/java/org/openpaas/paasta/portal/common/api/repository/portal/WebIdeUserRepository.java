package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.WebIdeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by indra on 2018-02-20.
 */
@Repository
public interface WebIdeUserRepository extends JpaRepository<WebIdeUser, Integer> {

    WebIdeUser findAllByOrgName(String orgName);

    List<WebIdeUser> findAllByOrderByUseYnDescUrlAsc();

    int countByOrgName(String orgName);
}
