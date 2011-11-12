package org.ofbiz.cmsbackend.plugin;

import java.util.Map;

import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.Delegator;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.ServiceUtil;



public class FellowshipLinkServices {
	public static final String module = FellowshipLinkServices.class.getName();
	public static final String LABEL_RESOURCE = "CmsBackendUiLabels";
	
	
	public static Map<String, Object> createCmsFellowshipLink(DispatchContext dctx,Map<String, ? extends Object> context)throws  GenericEntityException {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator();
        String linkId = delegator.getNextSeqId("CmsFellowshipLink");
        result.put("linkId", linkId);
        GenericValue cmsDisplayEvents = delegator.makeValue("CmsFellowshipLink", UtilMisc.toMap("linkId", linkId));
        cmsDisplayEvents.setNonPKFields(context);
        cmsDisplayEvents.create();
        return result;

    }
	
	public static Map<String, Object> deleteCmsFellowshipLink(DispatchContext dctx,Map<String, ? extends Object> context)throws  GenericEntityException {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator();
        String linkId = (String) context.get("linkId");
        
        GenericValue cmsDisplayEvents = delegator.findByPrimaryKey("CmsFellowshipLink", UtilMisc.toMap("linkId", linkId));
        if(UtilValidate.isNotEmpty(cmsDisplayEvents)){
            delegator.removeValue(cmsDisplayEvents);
        }
        return result;

    }
	 public static Map<String, Object> updateCmsFellowshipLink(DispatchContext dctx,
	            Map<String, ? extends Object> context) throws GenericEntityException {
	        Delegator delegator = dctx.getDelegator();
	        String linkId = (String) context.get("linkId");
	        GenericValue gv = delegator.findByPrimaryKey("CmsFellowshipLink",
	                UtilMisc.toMap("linkId", linkId));
	        if (UtilValidate.isNotEmpty(gv)) {
	            gv.setNonPKFields(context);
	            gv.store();
	        }
	        return ServiceUtil.returnSuccess();

	    }


}
