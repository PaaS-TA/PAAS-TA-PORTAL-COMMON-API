package org.openpaas.paasta.portal.common.api.entity.portal;

import javax.persistence.*;

/**
 * Created by indra on 2018-02-20.
 */
@Entity
@Table(name = "auto_scaling_config")
public class AutoScalingConfig {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no", nullable = false)
    private int no;

    @Column(name = "guid", nullable = false)
    private String guid;

    @Column(name = "org")
    private String org;

    @Column(name = "space")
    private String space;

    @Column(name = "app")
    private String app;

    @Column(name = "instance_min_cnt", nullable = false)
    private int instanceMinCnt;

    @Column(name = "instance_max_cnt", nullable = false)
    private int instanceMaxCnt;

    @Column(name = "cpu_threshold_min_per", nullable = false)
    private double cpuThresholdMinPer;

    @Column(name = "cpu_threshold_max_per", nullable = false)
    private double cpuThresholdMaxPer;

    @Column(name = "memory_min_size")
    private int memoryMinSize;

    @Column(name = "memory_max_size")
    private int memoryMaxSize;

    @Column(name = "check_time_sec", nullable = false)
    private int checkTimeSec;

    @Column(name = "auto_increase_yn")
    private String autoIncreaseYn;

    @Column(name = "auto_decrease_yn")
    private String autoDecreaseYn;


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public int getInstanceMinCnt() {
        return instanceMinCnt;
    }

    public void setInstanceMinCnt(int instanceMinCnt) {
        this.instanceMinCnt = instanceMinCnt;
    }

    public int getInstanceMaxCnt() {
        return instanceMaxCnt;
    }

    public void setInstanceMaxCnt(int instanceMaxCnt) {
        this.instanceMaxCnt = instanceMaxCnt;
    }

    public double getCpuThresholdMinPer() {
        return cpuThresholdMinPer;
    }

    public void setCpuThresholdMinPer(double cpuThresholdMinPer) {
        this.cpuThresholdMinPer = cpuThresholdMinPer;
    }

    public double getCpuThresholdMaxPer() {
        return cpuThresholdMaxPer;
    }

    public void setCpuThresholdMaxPer(double cpuThresholdMaxPer) {
        this.cpuThresholdMaxPer = cpuThresholdMaxPer;
    }

    public int getMemoryMinSize() {
        return memoryMinSize;
    }

    public void setMemoryMinSize(int memoryMinSize) {
        this.memoryMinSize = memoryMinSize;
    }

    public int getMemoryMaxSize() {
        return memoryMaxSize;
    }

    public void setMemoryMaxSize(int memoryMaxSize) {
        this.memoryMaxSize = memoryMaxSize;
    }

    public int getCheckTimeSec() {
        return checkTimeSec;
    }

    public void setCheckTimeSec(int checkTimeSec) {
        this.checkTimeSec = checkTimeSec;
    }

    public String getAutoIncreaseYn() {
        return autoIncreaseYn;
    }

    public void setAutoIncreaseYn(String autoIncreaseYn) {
        this.autoIncreaseYn = autoIncreaseYn;
    }

    public String getAutoDecreaseYn() {
        return autoDecreaseYn;
    }

    public void setAutoDecreaseYn(String autoDecreaseYn) {
        this.autoDecreaseYn = autoDecreaseYn;
    }
}
