package org.openpaas.paasta.portal.common.api.entity.portal;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SEJI on 2018-03-06.
 */
@Entity
@Table(name = "catalog_history")
public class CatalogHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no", nullable = false)
    private int no;

    @Column(name = "catalog_no", nullable = false)
    private int catalogNo;

    @Column(name = "catalog_type", nullable = false)
    private String catalogType;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @UpdateTimestamp
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Column(name = "lastmodified", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;

    @Transient
    private String searchKeyword;

    @Transient
    private String searchTypeColumn;

    @Transient
    private String searchTypeUseYn;

    @Transient
    private int starterCatalogNo;

    @Transient
    private String name;


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getCatalogNo() {
        return catalogNo;
    }

    public void setCatalogNo(int catalogNo) {
        this.catalogNo = catalogNo;
    }

    public String getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(String catalogType) {
        this.catalogType = catalogType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    public Date getCreated() {
        return created == null ? null : new Date(created.getTime());
    }

    public void setCreated(Date created) {
        this.created = created == null ? null : new Date(created.getTime());
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    public Date getLastmodified() {
        return lastmodified == null ? null : new Date(lastmodified.getTime()) ;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified == null ? null : new Date(lastmodified.getTime());
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchTypeColumn() {
        return searchTypeColumn;
    }

    public void setSearchTypeColumn(String searchTypeColumn) {
        this.searchTypeColumn = searchTypeColumn;
    }

    public String getSearchTypeUseYn() {
        return searchTypeUseYn;
    }

    public void setSearchTypeUseYn(String searchTypeUseYn) {
        this.searchTypeUseYn = searchTypeUseYn;
    }

    public int getStarterCatalogNo() {
        return starterCatalogNo;
    }

    public void setStarterCatalogNo(int starterCatalogNo) {
        this.starterCatalogNo = starterCatalogNo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "no=" + no +
                ", catalogNo=" + catalogNo +
                ", catalogType=" + catalogType +
                ", userId='" + userId + '\'' +
                ", created=" + created +
                ", lastmodified=" + lastmodified +
                ", searchKeyword='" + searchKeyword + '\'' +
                ", searchTypeColumn='" + searchTypeColumn + '\'' +
                ", searchTypeUseYn='" + searchTypeUseYn + '\'' +
                ", starterCatalogNo=" + starterCatalogNo +
                ", name='" + name + '\'' +
                '}';
    }
}
