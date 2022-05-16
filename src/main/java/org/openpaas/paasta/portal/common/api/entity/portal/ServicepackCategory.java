package org.openpaas.paasta.portal.common.api.entity.portal;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SEJI on 2018-03-06.
 */
@Entity
@Table(name = "servicepack_category")
public class ServicepackCategory {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "service_name", nullable = false)
    private String servicePackName;

    @Column(name = "thumb_img_name")
    private String thumbImgName;

    @Column(name = "thumb_img_path")
    private String thumbImgPath;

    @Column(name = "doc_file_url")
    private String docFileUrl;


    @Column(name = "use_yn", nullable = false)
    private String useYn;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @CreationTimestamp
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Column(name = "lastmodified", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;

    @Column(name = "parameter")
    private String parameter;

    @Column(name = "app_bind_parameter")
    private String appBindParameter;

    @Column(name = "dashboard_use_yn", nullable = false)
    private String dashboardUseYn;

    @Column(name = "on_demand_yn", nullable = false)
    private String onDemandYn;

    @Column(name = "app_bind_yn")
    private String appBindYn;

    @Formula("(SELECT cd.value FROM code_detail cd WHERE cd.key = classification AND cd.group_id = 'SERVICE_PACK_CATALOG' AND cd.language = language)")
    private String classificationValue;

    @Formula("(SELECT cd.summary FROM code_detail cd WHERE cd.key = classification AND cd.group_id = 'SERVICE_PACK_CATALOG' AND cd.language = language)")
    private String classificationSummary;


    @Column(name = "tags_param")
    private String tagsParam;

    @Column(name = "language")
    private String language;
//    @Transient
//    private String app_bind_parameter;

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

    public String getServicePackName() {
        return servicePackName;
    }

    public void setServicePackName(String servicePackName) {
        this.servicePackName = servicePackName;
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

    public String getDocFileUrl() {
        return docFileUrl;
    }

    public void setDocFileUrl(String docFileUrl) {
        this.docFileUrl = docFileUrl;
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

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getAppBindParameter() {
        return appBindParameter;
    }

    public void setAppBindParameter(String appBindParameter) {
        this.appBindParameter = appBindParameter;
    }

    public String getDashboardUseYn() {
        return dashboardUseYn;
    }

    public void setDashboardUseYn(String dashboardUseYn) {
        this.dashboardUseYn = dashboardUseYn;
    }

    public String getOnDemandYn() { return onDemandYn; }

    public void setOnDemandYn(String onDemandYn) {  this.onDemandYn = onDemandYn; }

    public String getAppBindYn() {
        return appBindYn;
    }

    public void setAppBindYn(String appBindYn) {
        this.appBindYn = appBindYn;
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


    //    public String getApp_bind_parameter() {
//        return app_bind_parameter;
//    }
//
//    public void setApp_bind_parameter(String app_bind_parameter) {
//        this.app_bind_parameter = app_bind_parameter;
//    }

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
        return "ServicepackCategory{" + "no=" + no + ", name='" + name + '\'' + ", classification='" + classification + '\'' + ", summary='" + summary + '\'' + ", description='" + description + '\'' + ", servicePackName='" + servicePackName + '\'' + ", thumbImgName='" + thumbImgName + '\'' + ", thumbImgPath='" + thumbImgPath + '\'' + ", docFileUrl='" + docFileUrl + '\'' + ", useYn='" + useYn + '\'' + ", userId='" + userId + '\'' + ", created=" + created + ", lastmodified=" + lastmodified + ", parameter='" + parameter + '\'' + ", appBindParameter='" + appBindParameter + '\'' + ", dashboardUseYn='" + dashboardUseYn + '\'' + ", appBindYn='" + appBindYn + '\'' + ", classificationValue='" + classificationValue + '\'' + ", classificationSummary='" + classificationSummary + '\'' + ", tagsParam='" + tagsParam + '\'' + ", language='" + language + '\'' + ", searchKeyword='" + searchKeyword + '\'' + '}';
    }
}
