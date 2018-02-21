package org.openpaas.paasta.portal.common.api.domain.user;

import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SEJI on 2018-02-20.
 */
@Service
public class UserService {

    @Autowired
    UserDetailRepository userDetailRepository;

    /**
     * portal db에 등록된 UserDetail 수
     *
     * @return int user count
     */
    public int getUserCount() {
        int userCnt = (int) userDetailRepository.count();
        System.out.println(userCnt);
        return userCnt;
    }

    /**
     * 사용자 상세 정보
     *
     * @param userId the user id
     * @return UserDetail user
     */
    public UserDetail getUser(String userId) {
        UserDetail userDetail = userDetailRepository.findByUserId(userId);
        System.out.println(userDetail);
        return userDetail;
    }




}
