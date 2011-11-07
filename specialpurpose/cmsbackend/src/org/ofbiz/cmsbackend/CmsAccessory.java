package org.ofbiz.cmsbackend;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javolution.util.FastList;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilProperties;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.Delegator;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.entity.condition.EntityCondition;
import org.ofbiz.entity.condition.EntityExpr;
import org.ofbiz.entity.condition.EntityJoinOperator;
import org.ofbiz.entity.condition.EntityOperator;
import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.LocalDispatcher;
import org.ofbiz.service.ModelService;
import org.ofbiz.service.ServiceUtil;

/**
 * 附件相关服务类
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2011-9-4 下午10:51:36
 */
public class CmsAccessory {
	public static final String MODULE = CmsAccessory.class.getName();
	public static final String LABEL_RESOURCE = "CmsUiLabels";
	private static final String IMAGE_FOLDER = UtilProperties.getPropertyValue(
			"cms.properties", "cms.uploadfile.accessory");

	public static String createCmsAccessory(HttpServletRequest request,
			HttpServletResponse response) {
		LocalDispatcher dispatcher = (LocalDispatcher) request
				.getAttribute("dispatcher");
		GenericValue userLogin = (GenericValue) request.getSession()
				.getAttribute("userLogin");

		try {
			Map<String, Object> context = UtilFileUpload.uploadFile(
					IMAGE_FOLDER, request);
			context.put("file", context.get("filePath"));
			context.put("userLogin", userLogin);

			ModelService pService = dispatcher.getDispatchContext()
					.getModelService("createCmsAccessory");

			context = pService.makeValid(context, ModelService.IN_PARAM);
			dispatcher.runSync("createCmsAccessory", context);
		} catch (Exception e) {
			Debug.logError(e.getMessage(), MODULE);
			return ModelService.RESPOND_ERROR;
		}

		return ModelService.RESPOND_SUCCESS;
	}

	public static String updateCmsAccessory(HttpServletRequest request,
			HttpServletResponse response) {
		LocalDispatcher dispatcher = (LocalDispatcher) request
				.getAttribute("dispatcher");
		GenericValue userLogin = (GenericValue) request.getSession()
				.getAttribute("userLogin");
		try {
			Map<String, Object> context = UtilFileUpload.uploadFile(
					IMAGE_FOLDER, request);
			context.put("file", context.get("filePath"));
			context.put("userLogin", userLogin);
			ModelService pService = dispatcher.getDispatchContext()
					.getModelService("updateCmsAccessory");
			context = pService.makeValid(context, ModelService.IN_PARAM);
			dispatcher.runSync("updateCmsAccessory", context);
		} catch (Exception e) {
			Debug.logError(e.getMessage(), MODULE);
			return ModelService.RESPOND_ERROR;
		}

		return ModelService.RESPOND_SUCCESS;
	}

	/**
	 * 
	 * 描述:创建附件
	 * 
	 * @param dctx
	 * @param context
	 * @return
	 * @throws GenericEntityException
	 * @author liyixing 2011-9-4
	 */
	public static Map<String, Object> createCmsAccessory(DispatchContext dctx,
			Map<String, ? extends Object> context)
			throws GenericEntityException {
		Locale locale = (Locale) context.get("locale");
		Delegator delegator = dctx.getDelegator();
		// 生成附件唯一标示
		String accessoryId = delegator.getNextSeqId("CmsAccessory");
		GenericValue gv = delegator.makeValue("CmsAccessory", UtilMisc.toMap(
				"accessoryId", accessoryId));
		gv.setNonPKFields(context);

		if (UtilValidate.isEmpty(context.get("statusId"))) {
			gv.set("statusId", "CCAS_DEFINED");
		}

		try {
			gv.create();

			String successMessage = UtilProperties.getMessage(LABEL_RESOURCE,
					"cms.createCmsAccessory.success", locale);
			Map<String, Object> result = ServiceUtil
					.returnSuccess(successMessage);

			result.put("accessoryId", accessoryId);

			return result;
		} catch (Exception e) {
			String errorMessage = UtilProperties.getMessage(LABEL_RESOURCE,
					"cms.createCmsAccessory.error", locale);

			Debug.logError(e, "增加附件时发生了错误，错误消息：" + e.getMessage(), MODULE);

			return ServiceUtil.returnError(errorMessage);
		}
	}

	/**
	 * 
	 * 描述:修改附件信息
	 * 
	 * @param dctx
	 * @param context
	 * @return
	 * @throws GenericEntityException
	 * @author liyixing 2011-9-4
	 */
	public static Map<String, Object> updateCmsAccessory(DispatchContext dctx,
			Map<String, ? extends Object> context)
			throws GenericEntityException {
		Locale locale = (Locale) context.get("locale");
		Delegator delegator = dctx.getDelegator();
		String accessoryId = (String) context.get("accessoryId");

		try {
			GenericValue gv = delegator.findByPrimaryKey("CmsAccessory",
					UtilMisc.toMap("accessoryId", accessoryId));

			if (UtilValidate.isNotEmpty(gv)) {
				gv.setNonPKFields(context, false);
				gv.store();
			} else {
				// 未找到该附件
				String notFindCmsAccessory = UtilProperties.getMessage(
						LABEL_RESOURCE,
						"cms.updateCmsAccessory.notFindCmsAccessory", locale);

				return ServiceUtil.returnFailure(notFindCmsAccessory);
			}

			String successMessage = UtilProperties.getMessage(LABEL_RESOURCE,
					"cms.updateCmsAccessory.success", locale);

			return ServiceUtil.returnSuccess(successMessage);
		} catch (Exception e) {
			String errorMessage = UtilProperties.getMessage(LABEL_RESOURCE,
					"cms.updateCmsAccessory.error", locale);

			Debug.logError(e, "修改附件信息时发生了错误，错误消息：" + e.getMessage(), MODULE);

			return ServiceUtil.returnError(errorMessage);
		}
	}

	/**
	 * 
	 * 描述:删除附件信息
	 * 
	 * @param dctx
	 * @param context
	 * @return
	 * @throws GenericEntityException
	 * @author liyixing 2011-9-4
	 */
	public static Map<String, Object> deleteCmsAccessory(DispatchContext dctx,
			Map<String, ? extends Object> context)
			throws GenericEntityException {
		Locale locale = (Locale) context.get("locale");
		Delegator delegator = dctx.getDelegator();
		String accessoryId = (String) context.get("accessoryId");

		try {
			GenericValue gv = delegator.findByPrimaryKey("CmsAccessory",
					UtilMisc.toMap("accessoryId", accessoryId));

			if (UtilValidate.isNotEmpty(gv)) {
				gv.remove();
			} else {
				// 未找到该附件
				String notFindCmsAccessory = UtilProperties.getMessage(
						LABEL_RESOURCE,
						"cms.deleteCmsAccessory.notFindCmsAccessory", locale);

				return ServiceUtil.returnFailure(notFindCmsAccessory);
			}

			String successMessage = UtilProperties.getMessage(LABEL_RESOURCE,
					"cms.deleteCmsAccessory.success", locale);

			return ServiceUtil.returnSuccess(successMessage);
		} catch (Exception e) {
			String errorMessage = UtilProperties.getMessage(LABEL_RESOURCE,
					"cms.deleteCmsAccessory.error", locale);

			Debug.logError(e, "删除附件时发生了错误，错误消息：" + e.getMessage(), MODULE);

			return ServiceUtil.returnError(errorMessage);
		}
	}

	/**
	 * 
	 * 描述:获取指定附件信息
	 * 
	 * @param dctx
	 * @param context
	 * @return
	 * @throws GenericEntityException
	 * @author liyixing 2011-9-4
	 */
	public static Map<String, Object> getCmsAccessoryDetailed(
			DispatchContext dctx, Map<String, ? extends Object> context)
			throws GenericEntityException {
		Locale locale = (Locale) context.get("locale");
		Delegator delegator = dctx.getDelegator();
		String accessoryId = (String) context.get("accessoryId");

		try {
			GenericValue gv = delegator.findByPrimaryKey("CmsAccessory",
					UtilMisc.toMap("accessoryId", accessoryId));

			if (UtilValidate.isNotEmpty(gv)) {
				gv.remove();
			} else {
				// 未找到该附件
				String notFindCmsAccessory = UtilProperties.getMessage(
						LABEL_RESOURCE,
						"cms.getCmsAccessoryDetailed.notFindCmsAccessory",
						locale);

				return ServiceUtil.returnFailure(notFindCmsAccessory);
			}

			Map<String, Object> result = ServiceUtil.returnSuccess();

			result.put("model", gv);

			return result;
		} catch (Exception e) {
			String errorMessage = UtilProperties.getMessage(LABEL_RESOURCE,
					"cms.getCmsAccessoryDetailed.error", locale);

			Debug.logError(e, "删除附件时发生了错误，错误消息：" + e.getMessage(), MODULE);

			return ServiceUtil.returnError(errorMessage);
		}
	}

	/**
	 * 
	 * 描述:条件搜索
	 * 
	 * @param dctx
	 * @param context
	 * @return
	 * @throws GenericEntityException
	 * @author liyixing 2011-9-4
	 */
	public static Map<String, Object> getCmsAccessoryList(DispatchContext dctx,
			Map<String, ? extends Object> context)
			throws GenericEntityException {
		Locale locale = (Locale) context.get("locale");
		Delegator delegator = dctx.getDelegator();
		String accessoryId = (String) context.get("accessoryId");
		String displayName = (String) context.get("displayName");
		List<EntityExpr> exprs = FastList.newInstance();

		if (UtilValidate.isNotEmpty(accessoryId)) {
			accessoryId = accessoryId.trim();
			exprs.add(EntityCondition.makeCondition("accessoryId",
					EntityOperator.EQUALS, accessoryId));
		}

		if (UtilValidate.isNotEmpty(displayName)) {
			displayName = displayName.trim();
			exprs.add(EntityCondition.makeCondition("displayName",
					EntityOperator.LIKE, "%" + displayName + "%"));
		}

		try {
			// 暂时不考虑分页问题
			List<GenericValue> pages = delegator.findList("CmsAccessory",
					EntityCondition
							.makeCondition(exprs, EntityJoinOperator.AND),
					null, UtilMisc.toList("displayLevel"), null, true);
			Map<String, Object> result = ServiceUtil.returnSuccess();

			result.put("pages", pages);

			return result;
		} catch (Exception e) {
			String errorMessage = UtilProperties.getMessage(LABEL_RESOURCE,
					"cms.getCmsAccessoryList.error", locale);

			Debug.logError(e, "搜索附件时发生了错误，错误消息：" + e.getMessage(), MODULE);

			return ServiceUtil.returnError(errorMessage);
		}
	}
}
