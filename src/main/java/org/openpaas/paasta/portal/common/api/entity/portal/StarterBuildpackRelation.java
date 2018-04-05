package org.openpaas.paasta.portal.common.api.entity.portal;

import javax.persistence.*;

/**
 * Created by SEJI on 2018-03-27.
 */
@Entity
@Table(name = "starter_buildpack_relation")
public class StarterBuildpackRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no", nullable = false)
    private int no;

    @Column(name = "starter_category_no", nullable = false)
    private int starterCatalogNo;

    @Column(name = "buildpack_category_no", nullable = false)
    private int buildpackCategoryNo;

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
        this.starterCatalogNo = starterCatalogNo;
    }

    public int getBuildpackCategoryNo() {
        return buildpackCategoryNo;
    }

    public void setBuildpackCategoryNo(int buildpackCategoryNo) {
        this.buildpackCategoryNo = buildpackCategoryNo;
    }

    @Override
    public String toString() {
        return "StarterBuildpackRelation{" +
                "no=" + no +
                ", starterCatalogNo=" + starterCatalogNo +
                ", buildpackCategoryNo=" + buildpackCategoryNo +
                '}';
    }
}
