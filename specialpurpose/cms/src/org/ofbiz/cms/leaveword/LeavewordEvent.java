package org.ofbiz.cms.leaveword;

import javax.servlet.http.HttpServletRequest;

public class LeavewordEvent {
	public static final String module = LeavewordEvent.class.getName();

	/*public static String createLeaveword(HttpServletRequest request,
			HttpServletResponse response) {
		LocalDispatcher dispatcher = (LocalDispatcher) request
				.getAttribute("dispatcher");
		Map<String, Object> context = FastMap.newInstance();
		Map data = new HashMap();
		String checkCode = request.getParameter("checkCode");
		if (StringUtils.isNotBlank(checkCode)) {
			try {

				context.put("title", request.getParameter("title"));
				context.put("contentMember", request.getParameter("content"));
				ModelService pService = dispatcher.getDispatchContext()
						.getModelService("createLeavewordByJava");// createLeaveword
															// 到services中查�?
				context = pService.makeValid(context, ModelService.IN_PARAM);
				String ip = getIpAddr(request);
				context.put("ip", ip);
				dispatcher.runSync(pService.name, context);

				data.put("success", Boolean.TRUE);
				data.put("msg", "成功");

				JsonUtil.toJsonObject(data, response);

				return ModelService.RESPOND_SUCCESS;
			} catch (Exception e) {
				Debug.logError(e.getMessage(), module);
				e.printStackTrace();
			}

		} else {
			Debug.logError("参数错误－没有验证码", module);
		}
		data.put("success", Boolean.FALSE);
		data.put("msg", "失败");
		try {
			JsonUtil.toJsonObject(data, response);
		} catch (EventHandlerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ModelService.RESPOND_ERROR;
	}

	public static String createLeavewordD(HttpServletRequest request,
			HttpServletResponse response) {
		Delegator delegator = (Delegator) request.getAttribute("delegator");
		Map<String, Object> context = FastMap.newInstance();
		Map data = new HashMap();
		String checkCode = request.getParameter("checkCode");
		if (StringUtils.isNotBlank(checkCode)) {
			try {

				context.put("title", request.getParameter("title"));
				context.put("contentMember", request.getParameter("content"));
				context.put("statusId","CLST_DEFINED");
				String ip = getIpAddr(request);
				context.put("ip", ip);
				String leavewordId = delegator.getNextSeqId("CmsLeaveword");
				GenericValue gv = delegator.makeValue("CmsLeaveword",
						UtilMisc.toMap("leavewordId", leavewordId));
				gv.setNonPKFields(context);
				gv.create();

				data.put("success", Boolean.TRUE);
				data.put("msg", "成功");

				JsonUtil.toJsonObject(data, response);

				return ModelService.RESPOND_SUCCESS;
			} catch (Exception e) {
				Debug.logError(e.getMessage(), module);
				e.printStackTrace();
			}

		} else {
			Debug.logError("参数错误－没有验证码", module);
		}
		data.put("success", Boolean.FALSE);
		data.put("msg", "失败");
		try {
			JsonUtil.toJsonObject(data, response);
		} catch (EventHandlerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ModelService.RESPOND_ERROR;
	}*/
	
	public static String getIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}  
}
