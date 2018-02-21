package org.openpaas.paasta.portal.common.api.entity.portal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

/**
 * Created by SEJI on 2018-02-20.
 */
@Entity
@Table(name = "user_detail")
@Getter
@Setter
public class UserDetail {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "tell_phone", nullable = false)
    private String tellPhone;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "address_detail", nullable = false)
    private String addressDetail;

    @Column(name = "admin_yn", nullable = false)
    private String adminYn;

    @Column(name = "img_path", nullable = false)
    private String imgPath;


    public UserDetail() {
        //empty
    }
    public UserDetail(Map user) {
        this.userId     = (user.containsKey("userId"))? (String) user.get("userId"):null;
        this.userName   = (user.containsKey("userName"))? (String) user.get("userName"):null;
        this.status     = (user.containsKey("status"))? (String) user.get("status"):null;
        this.addressDetail = (user.containsKey("addressDetail"))? (String) user.get("addressDetail"):null;
        this.address    = (user.containsKey("address"))? (String) user.get("address"):null;
        this.tellPhone  = (user.containsKey("tellPhone"))? (String) user.get("tellPhone"):null;
        this.zipCode    = (user.containsKey("zipCode"))? (String) user.get("zipCode"):null;
        this.adminYn    = (user.containsKey("adminYn"))? (String) user.get("adminYn"):null;
        this.imgPath    = (user.containsKey("imgPath"))? (String) user.get("imgPath"):null;
    }
}

