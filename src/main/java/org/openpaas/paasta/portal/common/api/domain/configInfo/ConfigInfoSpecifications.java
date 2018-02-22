package org.openpaas.paasta.portal.common.api.domain.configInfo;

import org.openpaas.paasta.portal.common.api.entity.portal.ConfigInfo;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by indra on 2018-02-22.
 */
public class ConfigInfoSpecifications {

    public static Specification<ConfigInfo> nameEq(final String name) {
        return new Specification<ConfigInfo>() {

            @Override
            public Predicate toPredicate(Root<ConfigInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if(name == null || name.equals(""))
                    return null;
                return cb.equal(root.get("name"), name);
            }
        };
    }
}
