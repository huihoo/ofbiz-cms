package org.ofbiz.cmsbackend.article;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilProperties;
import org.ofbiz.cmsbackend.UtilFileUpload;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.LocalDispatcher;
import org.ofbiz.service.ModelService;

/**
 * 
 * @author hezengyao
 * @since 2010-05
 */
public class ArticleEvents {
    public static final String  module         = ArticleEvents.class.getName();
    private static final String IMAGE_FOLDER   = UtilProperties.getPropertyValue("cmsupload.properties",  "cms.uploadfile.article");
    private static final String OFBIZ_HOME = System.getProperty("ofbiz.home");
    
    public static String createCmsArticle(HttpServletRequest request, HttpServletResponse response) {
        LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");
        GenericValue userLogin = (GenericValue) request.getSession().getAttribute("userLogin");
        try {
            Map<String, Object> context = UtilFileUpload.uploadFile(OFBIZ_HOME+IMAGE_FOLDER, request);
            context.put("contentImg", context.get("filePath"));
            context.put("userLogin", userLogin);
            ModelService pService = dispatcher.getDispatchContext().getModelService("createCmsArticle");
            context = pService.makeValid(context, ModelService.IN_PARAM);
            dispatcher.runSync(pService.name, context);
        }
        catch (Exception e) {
            Debug.logError(e.getMessage(), module);
            return ModelService.RESPOND_ERROR;
        }

        return ModelService.RESPOND_SUCCESS;
    }
    public static String updateCmsArticle(HttpServletRequest request, HttpServletResponse response) {
        LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");
        GenericValue userLogin = (GenericValue) request.getSession().getAttribute("userLogin");
        try {
        	Map<String, Object> context = UtilFileUpload.uploadFile(OFBIZ_HOME+IMAGE_FOLDER, request);
            context.put("contentImg", context.get("filePath"));
            context.put("userLogin", userLogin);
            ModelService pService = dispatcher.getDispatchContext().getModelService("updateCmsArticle");
            context = pService.makeValid(context, ModelService.IN_PARAM);
            dispatcher.runSync(pService.name, context);
        }
        catch (Exception e) {
            Debug.logError(e.getMessage(), module);
            return ModelService.RESPOND_ERROR;
        }

        return ModelService.RESPOND_SUCCESS;
    }

}
