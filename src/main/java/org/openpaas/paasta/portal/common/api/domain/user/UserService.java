package org.openpaas.paasta.portal.common.api.domain.user;

import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SEJI on 2018-02-20.
 */
@Service
public class UserService {

//    @Autowired
//    PortalConfig portalConfig;

    @Autowired
    UserDetailRepository userDetailRepository;
//
//    private final Logger LOGGER = getLogger(this.getClass());

    /**
     * portal db에 등록된 UserDetail 수
     *
     * @return int user count
     */
    public int getUserCount() {
        int userCnt = (int) userDetailRepository.count();
        System.out.println(userCnt);
//        return userCnt;
        System.out.println("test");
        return 0;
            //return userDetailMapper.getUserDetailCount();
    }



}
