package org.openpaas.paasta.portal.common.api.domain.supportNotice;


import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * SupportNotice.java
 * 모델 클래스
 *
 * @author hrjin
 * @since 2017.1.31
 */
@Entity
@Table(name = "support_notice")
public class SupportNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no", nullable = false)
    private int no;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "important", nullable = false)
    private String important;

    @Column(name = "classification", nullable = false)
    private String classification;

    @Column(name = "use_yn", nullable = false)
    private String useYn;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_size")
    private int fileSize;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "lastmodified")
    private LocalDateTime lastModified;


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "SupportNotice{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", important='" + important + '\'' +
                ", classification='" + classification + '\'' +
                ", useYn='" + useYn + '\'' +
                ", content='" + content + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", created='" + created + '\'' +
                ", lastModified='" + lastModified + '\'' +
                '}';
    }
}
