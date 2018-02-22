package org.openpaas.paasta.portal.common.api.domain.appAutoScaleModal;

import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.entity.portal.AutoScalingConfig;
import org.openpaas.paasta.portal.common.api.repository.portal.AutoScalingConfigRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.HashMap;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by indra on 2018-02-20.
 */
@Service
public class AppAutoScaleModalService {

    private static final Logger logger = getLogger(AppAutoScaleModalController.class);

    @Autowired
    AutoScalingConfigRepository autoScalingConfigRepository;

    @Autowired
    PortalConfig portalConfig;


    /**
     * getAppAutoScaleInfo(HashMap appAutoScale)
     * 앱의 자동인스턴스 증가, 감소 관련 정보를 가져온다.
     *
     * @param appAutoScale 자동인스턴스 증가, 감소 관련 모델 파라메터
     * @return {mode, list}
     */
    public HashMap<String, Object> getAppAutoScaleInfo(HashMap appAutoScale)  {
        HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put("list", autoScalingConfigRepository.findAllByGuid((String) appAutoScale.get("guid")));
        resultMap.put("mode", (String) appAutoScale.getOrDefault("mode",""));
        return resultMap;
    }

    /**
     * getAppAutoScaleInfo(HashMap appAutoScale)
     * 앱의 자동인스턴스 증가, 감소 관련 정보를 가져온다.
     *
     * @param appAutoScale 자동인스턴스 증가, 감소 관련 모델 파라메터
     * @return {mode, list}
     */
    public HashMap<String, Object> getAppAutoScaleList(HashMap appAutoScale)  {
        HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put("list", autoScalingConfigRepository.findAllByOrderByNoDesc());
        resultMap.put("mode", (String) appAutoScale.getOrDefault("mode",""));
        return resultMap;
    }

    /**
     * insertAppAutoScale (HashMap<String,Object> appAutoScale)
     * 앱의 자동인스턴스 증가, 감소 관련 정보를 저장한다.
     * 앱의 자동 증가감소를 admin 사용자가 모니터링하므로 ORG에space에 대한 매니저 권한을 부여한다.
     * @param autoScalingConfig 자동인스턴스 증가, 감소 관련 모델 파라메터
     * @return {결과 값}
     */
    public String insertAppAutoScale(AutoScalingConfig autoScalingConfig)  {
        String resultStr = Constants.RESULT_STATUS_SUCCESS;

        try {
            if(!autoScalingConfigRepository.exists(autoScalingConfig.getNo())) {
                AutoScalingConfig result = autoScalingConfigRepository.save(autoScalingConfig);
            } else {
                resultStr = Constants.RESULT_STATUS_FAIL_DUPLICATED;
            }
        } catch (DataAccessException ex) {
            logger.error("Exception :: DataAccessException :: {}", ex.getCause().getMessage());
            resultStr = Constants.RESULT_STATUS_FAIL;
        }

        return resultStr;
    }

    /**
     * updateAppAutoScale (HashMap<String,Object> appAutoScale)
     * 앱의 자동인스턴스 증가, 감소 관련 정보를 수정한다.
     *
     * @param autoScalingConfig 자동인스턴스 증가, 감소 관련 모델 파라메터
     * @return {결과 값}
     */
    public String updateAppAutoScale(AutoScalingConfig autoScalingConfig)  {
        String resultStr = Constants.RESULT_STATUS_SUCCESS;

        EntityManager portalEm = portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager();

        try {
            if(null == autoScalingConfig.getGuid() || "".equals(autoScalingConfig.getGuid()))
                throw new NullPointerException();

            CriteriaBuilder cb = portalEm.getCriteriaBuilder();
            CriteriaUpdate<AutoScalingConfig> update = cb.createCriteriaUpdate(AutoScalingConfig.class);
            Root e = update.from(AutoScalingConfig.class);

            if(autoScalingConfig.getInstanceMinCnt() != 0)
                update.set("instanceMinCnt", autoScalingConfig.getInstanceMinCnt());

            if(autoScalingConfig.getInstanceMaxCnt() != 0)
                update.set("instanceMaxCnt", autoScalingConfig.getInstanceMaxCnt());

            if(autoScalingConfig.getCpuThresholdMinPer() != 0 && autoScalingConfig.getCpuThresholdMinPer() != 0.0)
                update.set("cpuThresholdMinPer", autoScalingConfig.getCpuThresholdMinPer());

            if(autoScalingConfig.getCpuThresholdMaxPer() != 0.0)
                update.set("cpuThresholdMaxPer", autoScalingConfig.getCpuThresholdMaxPer());

            if(autoScalingConfig.getCheckTimeSec() != 0)
                update.set("checkTimeSec", autoScalingConfig.getCheckTimeSec());

            if(autoScalingConfig.getMemoryMinSize() != 0)
                update.set("memoryMinSize", autoScalingConfig.getMemoryMinSize());

            if(autoScalingConfig.getMemoryMaxSize() != 0)
                update.set("memoryMaxSize", autoScalingConfig.getMemoryMaxSize());

            if(null != autoScalingConfig.getAutoIncreaseYn() && !"".equals(autoScalingConfig.getAutoIncreaseYn()))
                update.set("autoIncreaseYn", autoScalingConfig.getAutoIncreaseYn());

            if(null != autoScalingConfig.getAutoDecreaseYn() && !"".equals(autoScalingConfig.getAutoDecreaseYn()))
                update.set("autoDecreaseYn", autoScalingConfig.getAutoDecreaseYn());

            update.where(cb.greaterThanOrEqualTo(e.get("guid"), (String) autoScalingConfig.getGuid()));

            portalEm.getTransaction().begin();
            int updateCnt = portalEm.createQuery(update).executeUpdate();
            portalEm.getTransaction().commit();

            if(updateCnt == 0)
                throw new NullPointerException();

//            AutoScalingConfig result = autoScalingConfigRepository.save(autoScalingConfig);
        } catch (DataAccessException ex) {
//        } catch (TransactionRequiredException ex) {
            logger.error("Exception :: DataAccessException :: {}", ex.getCause().getMessage());
            resultStr = Constants.RESULT_STATUS_FAIL;
        } catch (NullPointerException nex) {
            logger.error("Exception :: NullPointerException :: {}", nex.getCause().getMessage());
            resultStr = Constants.RESULT_STATUS_FAIL;
        } finally {
            portalEm.close();
        }

        return resultStr;
    }

    /**
     * deleteAppAutoScale (HashMap<String,Object> appAutoScale)
     * 앱의 자동인스턴스 증가, 감소 관련 정보를 삭제한다..
     *
     * @param autoScalingConfig guid 자동인스턴스 증가, 감소 관련 모델 아이디
     * @return {결과 값}
     */
    public String deleteAppAutoScale(AutoScalingConfig autoScalingConfig)  {
        String resultStr = Constants.RESULT_STATUS_SUCCESS;

        EntityManager portalEm = portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager();

        try {
            if(null == autoScalingConfig.getGuid() || "".equals(autoScalingConfig.getGuid()))
                throw new NullPointerException();

            CriteriaBuilder cb = portalEm.getCriteriaBuilder();
            CriteriaDelete<AutoScalingConfig> delete = cb.createCriteriaDelete(AutoScalingConfig.class);
            Root e = delete.from(AutoScalingConfig.class);

            delete.where(cb.greaterThanOrEqualTo(e.get("guid"), (String) autoScalingConfig.getGuid()));

            portalEm.getTransaction().begin();
            int deleteCnt = portalEm.createQuery(delete).executeUpdate();
            portalEm.getTransaction().commit();

            if(deleteCnt == 0)
                throw new NullPointerException();

        } catch (DataAccessException ex) {
            logger.error("Exception :: DataAccessException :: {}", ex.getCause().getMessage());
            resultStr = Constants.RESULT_STATUS_FAIL;
        } catch (NullPointerException nex) {
            logger.error("Exception :: NullPointerException :: {}", nex.getCause().getMessage());
            resultStr = Constants.RESULT_STATUS_FAIL;
        } finally {
            portalEm.close();
        }

        return resultStr;
    }
}
