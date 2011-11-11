package org.ofbiz.cmsbackend.accessory;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilProperties;
import org.ofbiz.cmsbackend.UtilFileUpload;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.LocalDispatcher;
import org.ofbiz.service.ModelService;

public class AccessoryEvents {

    public static final String  module         = AccessoryEvents.class.getName();
    private static final String IMAGE_FOLDER = UtilProperties.getPropertyValue(
            "cmsupload.properties", "cms.uploadfile.accessory");
    
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
            Debug.logError(e.getMessage(), module);
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
            Debug.logError(e.getMessage(), module);
            return ModelService.RESPOND_ERROR;
        }

        return ModelService.RESPOND_SUCCESS;
    }

}
