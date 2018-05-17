package org.openpaas.paasta.portal.common.api.entity.cc;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by indra on 2018-02-08.
 */

@Entity
@Table(name = "routes")
public class CatalogCc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "guid")
    private String guid;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "host")
    private String host;

    @Column(name = "domain_id")
    private int domainid;

    @Column(name = "space_id")
    private int spaceid;

    @Column(name = "path")
    private String path;

    @Column(name = "port")
    private int port;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getDomainid() {
        return domainid;
    }

    public void setDomainid(int domainid) {
        this.domainid = domainid;
    }

    public int getSpaceid() {
        return spaceid;
    }

    public void setSpaceid(int spaceid) {
        this.spaceid = spaceid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "CatalogCc{" +
                "id='" + id + '\'' +
                ", guid='" + guid + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", host='" + host + '\'' +
                ", domainid=" + domainid +
                ", spaceid=" + spaceid +
                ", path='" + path + '\'' +
                ", port=" + port +
                '}';
    }
}
