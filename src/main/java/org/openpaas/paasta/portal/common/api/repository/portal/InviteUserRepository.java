package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.InviteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InviteUserRepository extends JpaRepository<InviteUser, Integer> {
    List<InviteUser> findByUserIdAndOrgGuid(String userId, String orgId);

    List<InviteUser> findByToken(String token);
}
