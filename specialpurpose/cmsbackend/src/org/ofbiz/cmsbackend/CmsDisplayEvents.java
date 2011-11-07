package org.ofbiz.cmsbackend;

import java.util.List;
import java.util.Map;

import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.Delegator;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.GenericServiceException;
import org.ofbiz.service.LocalDispatcher;
import org.ofbiz.service.ServiceUtil;

public class CmsDisplayEvents {
	public static final String module = CmsDisplayEvents.class.getName();
	public static final String LABEL_RESOURCE = "CmsBackendUiLabels";
	private final static String HTML_BR ="&lt;br&gt";
	
	
	public static Map<String, Object> createCmsCalendarEvents(DispatchContext dctx,Map<String, ? extends Object> context)throws  GenericEntityException {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator();
        String eventId = delegator.getNextSeqId("CmsCalendarEvents");
        result.put("eventId", eventId);
        GenericValue cmsDisplayEvents = delegator.makeValue("CmsCalendarEvents", UtilMisc.toMap("eventId", eventId));
        cmsDisplayEvents.setNonPKFields(context);
        cmsDisplayEvents.create();
        result.put("startTime", cmsDisplayEvents.getString("startTime"));
        return result;

    }
	public static Map<String, Object> deleteCmsCalendarEvents(DispatchContext dctx,Map<String, ? extends Object> context)throws  GenericEntityException {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator();
        String eventId = (String) context.get("eventId");
        
        GenericValue cmsDisplayEvents = delegator.findByPrimaryKey("CmsCalendarEvents", UtilMisc.toMap("eventId", eventId));
        if(UtilValidate.isNotEmpty(cmsDisplayEvents)){
            result.put("startTime", cmsDisplayEvents.getString("startTime"));
            delegator.removeValue(cmsDisplayEvents);
        }
        return result;

    }


	public static Map<String, Object> checkCmsDisplayEvents(DispatchContext dctx,Map<String, ? extends Object> context)throws  GenericEntityException, GenericServiceException {
	    Map<String, Object> result = ServiceUtil.returnSuccess();
		Delegator delegator = dctx.getDelegator();
        LocalDispatcher dispatcher = dctx.getDispatcher();
		String startTime = (String) context.get("startTime");
		GenericValue userLogin = (GenericValue) context.get("userLogin");
		GenericValue cmsDisplayEvents = delegator.findByPrimaryKey("CmsDisplayEvents", UtilMisc.toMap("startTime", startTime));
        if(UtilValidate.isNotEmpty(cmsDisplayEvents)){
            Map serviceContext= UtilMisc.toMap("userLogin", userLogin);
            serviceContext.put("startTime", startTime);
            dispatcher.runSync("deleteCmsDisplayEvents", serviceContext);
        }
		List  list = delegator.findByAnd("CmsCalendarEvents", UtilMisc.toMap("startTime", startTime));	
		String eventNumber ="";
		String content="";
		if(UtilValidate.isNotEmpty(list)){
		   
		    for(int i=0;i<list.size();i++){
		        GenericValue gv = (GenericValue) list.get(i);
		        eventNumber=String.valueOf(i+1);
		        content+=gv.getString("eventTime");
		        content+=HTML_BR+gv.getString("address");
		        if(UtilValidate.isNotEmpty(gv.get("shortTitle"))){content+=HTML_BR+gv.getString("shortTitle");}
		        if(UtilValidate.isNotEmpty(gv.get("content"))){content+=HTML_BR+gv.getString("content");}
		        
	        }
		    Map serviceContext= UtilMisc.toMap("userLogin", userLogin);
		    serviceContext.put("startTime", startTime);
		    serviceContext.put("eventNumber",eventNumber);
		    serviceContext.put("content",content);
		    dispatcher.runSync("createCmsDisplayEvents", serviceContext);
        }
		
		return result;

	}

	

}
