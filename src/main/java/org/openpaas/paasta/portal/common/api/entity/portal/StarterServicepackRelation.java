package org.openpaas.paasta.portal.common.api.entity.portal;

import javax.persistence.*;

/**
 * Created by SEJI on 2018-03-27.
 */
@Entity
@Table(name = "starter_servicepack_relation")
public class StarterServicepackRelation {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private int no;

    @Column(name = "starter_category_no")
    private int starterCatalogNo;

    @Column(name = "servicepack_category_no")
    private int servicepackCategoryNo;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getStarterCatalogNo() {
        return starterCatalogNo;
    }

    public void setStarterCatalogNo(int starterCatalogNo) {
        starterCatalogNo = starterCatalogNo;
    }

    public int getServicepackCategoryNo() {
        return servicepackCategoryNo;
    }

    public void setServicepackCategoryNo(int servicepackCategoryNo) {
        servicepackCategoryNo = servicepackCategoryNo;
    }

}
