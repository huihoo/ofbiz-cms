package org.ofbiz.cmsbackend.catalog;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilProperties;
import org.ofbiz.cmsbackend.UtilFileUpload;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.LocalDispatcher;
import org.ofbiz.service.ModelService;

public class CatalogEvents {

    


    public static final String  module         = CatalogEvents.class.getName();
    private static final String IMAGE_FOLDER   = UtilProperties.getPropertyValue("cmsupload.properties",
                                                       "cms.uploadfile.catalog");

    public static String createCmsCatalog(HttpServletRequest request, HttpServletResponse response) {
        LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");
        GenericValue userLogin = (GenericValue) request.getSession().getAttribute("userLogin");
        try {
            Map<String, Object> context = UtilFileUpload.uploadFile(IMAGE_FOLDER, request);
            context.put("contentImg", context.get("filePath"));
            context.put("userLogin", userLogin);
            ModelService pService = dispatcher.getDispatchContext().getModelService("createCmsCatalog");
            context = pService.makeValid(context, ModelService.IN_PARAM);
            dispatcher.runSync(pService.name, context);
        }
        catch (Exception e) {
            Debug.logError(e.getMessage(), module);
            return ModelService.RESPOND_ERROR;
        }

        return ModelService.RESPOND_SUCCESS;
    }
    public static String updateCmsCatalog(HttpServletRequest request, HttpServletResponse response) {
        LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");
        GenericValue userLogin = (GenericValue) request.getSession().getAttribute("userLogin");
        try {
            Map<String, Object> context = UtilFileUpload.uploadFile(IMAGE_FOLDER, request);
            context.put("contentImg", context.get("filePath"));
            context.put("userLogin", userLogin);
            ModelService pService = dispatcher.getDispatchContext().getModelService("updateCmsCatalog");
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
