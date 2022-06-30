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

//    int countByGroupId(String groupId);
    int countByGroupNo(int groupNo);

    List<CodeDetail> findAllByNo(int no);

//    List<CodeDetail> findByGroupIdAndKey(String groupId, String key);
    List<CodeDetail> findByGroupNoAndKey(int groupNo, String key);
//    List<CodeDetail> findByGroupId(String groudId);

    List<CodeDetail> findByGroupNo(int groudNo);
    List<CodeDetail> findAllByKey(String key);

//    List<CodeDetail> findAllByGroupIdAndLanguage(String groupId, String language);

    List<CodeDetail> findAllByGroupNoAndLanguage(int groupNo, String language);



}
