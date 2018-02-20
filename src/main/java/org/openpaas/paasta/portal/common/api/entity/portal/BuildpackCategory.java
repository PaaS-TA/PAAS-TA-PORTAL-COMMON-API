package org.openpaas.paasta.portal.common.api.entity.portal;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by indra on 2018-02-07.
 */
@Entity
@Table(name = "buildpack_category")
public class BuildpackCategory {

    //    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private int no;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "classification", nullable = false)
    private String classification;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "description")
    private String description;

    @Column(name = "buildpack_name", nullable = false)
    private String buildpackName;

    @Column(name = "thumb_img_name")
    private String thumbImgName;

    @Column(name = "thumb_img_path")
    private String thumbImgPath;

    @Column(name = "use_yn", columnDefinition = "default 'Y'", nullable = false)
    private String useYn;

    @Column(name = "app_sample_file_name")
    private String appSampleFileName;

    @Column(name = "app_sample_file_path")
    private String appSampleFilePaht;

    @Column(name = "app_sample_file_size")
    private String appSampleFileSize;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @CreationTimestamp
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Column(name = "lastmodified", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;



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

    public String getBuildpackName() {
        return buildpackName;
    }

    public void setBuildpackName(String buildpackName) {
        this.buildpackName = buildpackName;
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

    public String getAppSampleFileName() {
        return appSampleFileName;
    }

    public void setAppSampleFileName(String appSampleFileName) {
        this.appSampleFileName = appSampleFileName;
    }

    public String getAppSampleFilePaht() {
        return appSampleFilePaht;
    }

    public void setAppSampleFilePaht(String appSampleFilePaht) {
        this.appSampleFilePaht = appSampleFilePaht;
    }

    public String getAppSampleFileSize() {
        return appSampleFileSize;
    }

    public void setAppSampleFileSize(String appSampleFileSize) {
        this.appSampleFileSize = appSampleFileSize;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

}
