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
    @Column(name = "no", nullable = false)
    private int no;

    @Column(name = "starter_category_no", nullable = false)
    private int StarterCatalogNo;

    @Column(name = "servicepack_category_no", nullable = false)
    private int ServicepackCategoryNo;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getStarterCatalogNo() {
        return StarterCatalogNo;
    }

    public void setStarterCatalogNo(int starterCatalogNo) {
        StarterCatalogNo = starterCatalogNo;
    }

    public int getServicepackCategoryNo() {
        return ServicepackCategoryNo;
    }

    public void setServicepackCategoryNo(int servicepackCategoryNo) {
        ServicepackCategoryNo = servicepackCategoryNo;
    }

}
