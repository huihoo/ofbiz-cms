package org.ofbiz.cmsbackend.accessory;

import java.util.Map;

import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.Delegator;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.ServiceUtil;

public class AccessoryServices {

    /**
     * 描述:创建附件
     */
    public static Map<String, Object> createCmsAccessory(DispatchContext dctx,
            Map<String, ? extends Object> context) throws GenericEntityException {

        Delegator delegator = dctx.getDelegator();
        String accessoryId = delegator.getNextSeqId("CmsAccessory");
        GenericValue gv = delegator.makeValue("CmsAccessory",
                UtilMisc.toMap("accessoryId", accessoryId));
        gv.setNonPKFields(context);

        if (UtilValidate.isEmpty(context.get("statusId"))) {
            gv.set("statusId", "CCAS_DEFINED");
        }
        gv.create();

        return ServiceUtil.returnSuccess();
    }

    /**
     * 描述:修改附件信息
     */
    public static Map<String, Object> updateCmsAccessory(DispatchContext dctx,
            Map<String, ? extends Object> context) throws GenericEntityException {

        Delegator delegator = dctx.getDelegator();
        String accessoryId = (String) context.get("accessoryId");

        GenericValue gv = delegator.findByPrimaryKey("CmsAccessory",
                UtilMisc.toMap("accessoryId", accessoryId));

        if (UtilValidate.isNotEmpty(gv)) {
            gv.setNonPKFields(context, false);
            gv.store();
        }

        return ServiceUtil.returnSuccess();

    }

    /**
     * 描述:删除附件信息
     */
    public static Map<String, Object> deleteCmsAccessory(DispatchContext dctx,
            Map<String, ? extends Object> context) throws GenericEntityException {

        Delegator delegator = dctx.getDelegator();
        String accessoryId = (String) context.get("accessoryId");

        GenericValue gv = delegator.findByPrimaryKey("CmsAccessory",
                UtilMisc.toMap("accessoryId", accessoryId));

        if (UtilValidate.isNotEmpty(gv)) {
            gv.remove();
        }

        return ServiceUtil.returnSuccess();
    }

}
