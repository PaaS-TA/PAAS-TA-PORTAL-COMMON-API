package org.openpaas.paasta.portal.common.api.domain.userManagement;

import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by indra on 2018-02-22.
 */
public class UserManagementSpecifications {

    public static Specification<UserDetail> userIdEq(final String userId) {
        return new Specification<UserDetail>() {

            @Override
            public Predicate toPredicate(Root<UserDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if(userId == null || userId.equals(""))
                    return null;
                return cb.equal(root.get("userId"), userId);
            }
        };
    }

    public static Specification<UserDetail> searchKeywordEq(final String searchKeyword) {
        return new Specification<UserDetail>() {

            @Override
            public Predicate toPredicate(Root<UserDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if(searchKeyword == null || searchKeyword.equals(""))
                    return null;
                return cb.equal(root.get("name"), searchKeyword);
            }
        };
    }


}
