package org.openpaas.paasta.portal.common.api.repository.portal;

import org.openpaas.paasta.portal.common.api.entity.portal.CodeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SEJI on 2018-02-20.
 */
@Repository
public interface CodeDetailRepository extends JpaRepository<CodeDetail, Integer> {

    int countByGroupId(String groupId);

    List<CodeDetail> findAllByNo(int no);


    List<CodeDetail> findByGroupId(String groudId);

    List<CodeDetail> findAllByGroupId(String groudid);


}