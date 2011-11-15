package org.ofbiz.shipment;


import java.sql.Timestamp;
import java.util.Map;

import org.ofbiz.base.util.UtilDateTime;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.Delegator;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.ServiceUtil;


/**
 * 
 * @author hezengyao
 * @since 2010-05
 */
public class EPaketConfigServices {

    public static final String  module         = EPaketConfigServices.class.getName();
    public static final String  LABEL_RESOURCE = "CmsUiLabels";
    
    
    public static Map<String, Object> createEPaketConfig(DispatchContext dctx,
            Map<String, ? extends Object> context) throws GenericEntityException {
        Timestamp now = UtilDateTime.nowTimestamp();
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        Delegator delegator = dctx.getDelegator();
        String epaketConfigId = (String) context.get("epaketConfigId");
        GenericValue gv = delegator.makeValue("EPaketConfig", UtilMisc.toMap("epaketConfigId", epaketConfigId));
        gv.set("createdDate", now);
        gv.set("lastModifiedDate", now);
        gv.set("createdByUserLogin", userLogin.getString("userLoginId"));
        gv.set("lastModifiedByUserLogin", userLogin.getString("userLoginId"));
        
        
        gv.setNonPKFields(context);
        gv.create();
        
        createEPaketConfigStatus(delegator,gv,"createEPaketConfig");
        return ServiceUtil.returnSuccess();

    }

    public static Map<String, Object> updateEPaketConfig(DispatchContext dctx,
            Map<String, ? extends Object> context) throws GenericEntityException {
        Timestamp now = UtilDateTime.nowTimestamp();
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        Delegator delegator = dctx.getDelegator();
        String epaketConfigId = (String) context.get("epaketConfigId");
        GenericValue gv = delegator.findByPrimaryKey("EPaketConfig",
                UtilMisc.toMap("epaketConfigId", epaketConfigId));
        if (UtilValidate.isNotEmpty(gv)) {
            gv.setNonPKFields(context);
            gv.set("lastModifiedDate", now);
            gv.set("lastModifiedByUserLogin", userLogin.getString("userLoginId"));
            gv.store();
            createEPaketConfigStatus(delegator,gv,"updateEPaketConfig");
        }
        return ServiceUtil.returnSuccess();

    }

    public static Map<String, Object> deleteEPaketConfig(DispatchContext dctx,
            Map<String, ? extends Object> context) throws GenericEntityException {
        Delegator delegator = dctx.getDelegator();
        String epaketConfigId = (String) context.get("epaketConfigId");
        GenericValue gv = delegator.findByPrimaryKey("EPaketConfig",
                UtilMisc.toMap("epaketConfigId", epaketConfigId));
        if (UtilValidate.isNotEmpty(gv)) {
            gv.remove();
            createEPaketConfigStatus(delegator,gv,"deleteEPaketConfig");
        }
        return ServiceUtil.returnSuccess();
    }
    
    
    public static Map<String, Object> createEPaketConfigStatus(Delegator delegator,
            Map<String, ? extends Object> context,String Action) throws GenericEntityException {

        String epaketConfigStatusId = delegator.getNextSeqId("EPaketConfigStatus");
        GenericValue ePaketConfigStatus = delegator.makeValue("EPaketConfigStatus", UtilMisc.toMap("epaketConfigStatusId", epaketConfigStatusId));
        
        ePaketConfigStatus.setNonPKFields(context);
        ePaketConfigStatus.set("action",Action);
        ePaketConfigStatus.create();
        return ServiceUtil.returnSuccess();

    }

}
