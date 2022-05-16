package org.openpaas.paasta.portal.common.api.entity.portal;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by SEJI on 2018-03-06.
 */
@Entity
@Table(name = "starter_category")
public class StarterCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no", nullable = false)
    private int no;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "classification", nullable = false)
    private String classification;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "description")
    private String description;

    @Column(name = "thumb_img_name")
    private String thumbImgName;

    @Column(name = "thumb_img_path")
    private String thumbImgPath;

    @Column(name = "use_yn", nullable = false)
    private String useYn;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "tags_param")
    private String tagsParam;

    @CreationTimestamp
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Column(name = "lastmodified", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;

    @Formula("(SELECT cd.value FROM code_detail cd WHERE cd.key = classification AND cd.group_id = 'STARTER_CATALOG' AND cd.language = language)")
    private String classificationValue;

    @Formula("(SELECT cd.summary FROM code_detail cd WHERE cd.key = classification AND cd.group_id = 'STARTER_CATALOG' AND cd.language = language)")
    private String classificationSummary;

    @Column(name = "language")
    private String language;

    @Transient
    private int buildPackCategoryNo;

    @Transient
    private List<Integer> servicePackCategoryNoList;

    @Transient
    private String searchKeyword;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbImgName() {
        return thumbImgName;
    }

    public void setThumbImgName(String thumbImgName) {
        this.thumbImgName = thumbImgName;
    }

    public String getThumbImgPath() {
        return thumbImgPath;
    }

    public void setThumbImgPath(String thumbImgPath) {
        this.thumbImgPath = thumbImgPath;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
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
        return lastmodified == null ? null : new Date(lastmodified.getTime());
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified == null ? null : new Date(lastmodified.getTime());
    }

    public String getClassificationValue() {
        return classificationValue;
    }

    public void setClassificationValue(String classificationValue) {
        this.classificationValue = classificationValue;
    }

    public String getClassificationSummary() {
        return classificationSummary;
    }

    public void setClassificationSummary(String classificationSummary) {
        this.classificationSummary = classificationSummary;
    }

    public int getBuildPackCategoryNo() {
        return buildPackCategoryNo;
    }

    public void setBuildPackCategoryNo(int buildPackCategoryNo) {
        this.buildPackCategoryNo = buildPackCategoryNo;
    }

    public List<Integer> getServicePackCategoryNoList() {
        return servicePackCategoryNoList;
    }

    public void setServicePackCategoryNoList(List<Integer> servicePackCategoryNoList) {
        this.servicePackCategoryNoList = servicePackCategoryNoList;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getTagsParam() {
        return tagsParam;
    }

    public void setTagsParam(String tagsParam) {
        this.tagsParam = tagsParam;
    }

    public String getLanguage() {
	return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "StarterCategory{" + "no=" + no + ", name='" + name + '\'' + ", classification='" + classification + '\'' + ", summary='" + summary + '\'' + ", description='" + description + '\'' + ", thumbImgName='" + thumbImgName + '\'' + ", thumbImgPath='" + thumbImgPath + '\'' + ", useYn='" + useYn + '\'' + ", userId='" + userId + '\'' + ", tagsParam='" + tagsParam + '\'' + ", created=" + created + ", lastmodified=" + lastmodified + ", classificationValue='" + classificationValue + '\'' + ", classificationSummary='" + classificationSummary + '\'' + ", language='" + language + '\'' + ", buildPackCategoryNo=" + buildPackCategoryNo + ", servicePackCategoryNoList=" + servicePackCategoryNoList + ", searchKeyword='" + searchKeyword + '\'' + '}';
    }
}
