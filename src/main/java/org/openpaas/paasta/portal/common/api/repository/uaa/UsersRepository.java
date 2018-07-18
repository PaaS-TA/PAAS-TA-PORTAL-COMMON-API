package org.openpaas.paasta.portal.common.api.repository.uaa;

        import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

/**
 * Created by SEJI on 2018-02-20.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findById(String guid);

}
