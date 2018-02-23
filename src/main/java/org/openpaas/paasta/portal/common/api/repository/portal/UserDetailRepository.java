package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by SEJI on 2018-02-20.
 */
@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {

    UserDetail findByUserId(String userId);

    int countByUserId(String userId);
}
