package org.openpaas.paasta.portal.common.api.entity.portal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by SEJI on 2018-03-27.
 */
@Entity
@Table(name = "starter_buildpack_relation")
public class StarterBuildpackRelation {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no", nullable = false)
    private int no;

    @Column(name = "starter_category_no", nullable = false)
    private int StarterCatalogNo;

    @Column(name = "buildpack_category_no", nullable = false)
    private int BuildpackCategoryNo;

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

    public int getBuildpackCategoryNo() {
        return BuildpackCategoryNo;
    }

    public void setBuildpackCategoryNo(int buildpackCategoryNo) {
        BuildpackCategoryNo = buildpackCategoryNo;
    }
}
