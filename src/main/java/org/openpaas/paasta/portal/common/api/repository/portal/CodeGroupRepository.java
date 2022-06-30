package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.CodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SEJI on 2018-02-20.
 */
@Repository
public interface CodeGroupRepository extends JpaRepository<CodeGroup, Integer> {
    List<CodeGroup> findById(String id);
    List<CodeGroup> findAllByIdAndLanguage(String id, String language);
    CodeGroup findByIdAndLanguage(String id, String language);
    List<CodeGroup> findByNo(int no);
    void deleteById(String id);
}
