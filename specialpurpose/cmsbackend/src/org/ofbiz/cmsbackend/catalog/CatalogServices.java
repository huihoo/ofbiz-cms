package org.ofbiz.cmsbackend.catalog;

import java.util.Map;

import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.Delegator;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.ServiceUtil;

public class CatalogServices {

    
    public static Map<String, Object> createCmsCatalog(DispatchContext dctx,
            Map<String, ? extends Object> context) throws GenericEntityException {
        Map result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator();
        String catalogId = delegator.getNextSeqId("CmsCatalog");
        GenericValue gv = delegator.makeValue("CmsCatalog", UtilMisc.toMap("catalogId", catalogId));
        gv.setNonPKFields(context);
        gv.create();
        result.put("catalogId", catalogId);
        return result;

    }

    public static Map<String, Object> updateCmsCatalog(DispatchContext dctx,
            Map<String, ? extends Object> context) throws GenericEntityException {
        Delegator delegator = dctx.getDelegator();
        String catalogId = (String) context.get("catalogId");
        GenericValue gv = delegator.findByPrimaryKey("CmsCatalog",
                UtilMisc.toMap("catalogId", catalogId));
        if (UtilValidate.isNotEmpty(gv)) {
            gv.setNonPKFields(context);
            gv.store();
        }
        return ServiceUtil.returnSuccess();

    }

    public static Map<String, Object> deleteCmsCatalog(DispatchContext dctx,
            Map<String, ? extends Object> context) throws GenericEntityException {
        Delegator delegator = dctx.getDelegator();
        String catalogId = (String) context.get("catalogId");
        GenericValue gv = delegator.findByPrimaryKey("CmsCatalog",
                UtilMisc.toMap("catalogId", catalogId));
        if (UtilValidate.isNotEmpty(gv)) {
            gv.remove();
        }
        return ServiceUtil.returnSuccess();
    }
}
