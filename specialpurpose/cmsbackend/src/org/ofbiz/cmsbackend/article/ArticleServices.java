package org.ofbiz.cmsbackend.article;

import java.util.Map;

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
public class ArticleServices {

    public static final String  module         = ArticleServices.class.getName();
    public static final String  LABEL_RESOURCE = "CmsUiLabels";
    
    
    public static Map<String, Object> createCmsArticle(DispatchContext dctx,
            Map<String, ? extends Object> context) throws GenericEntityException {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator();
        String articleId = delegator.getNextSeqId("CmsArticle");
        GenericValue gv = delegator.makeValue("CmsArticle", UtilMisc.toMap("articleId", articleId));
        gv.setNonPKFields(context);
        gv.create();
        //result.put("articleId", articleId);
        return result;

    }

    public static Map<String, Object> updateCmsArticle(DispatchContext dctx,
            Map<String, ? extends Object> context) throws GenericEntityException {
        Delegator delegator = dctx.getDelegator();
        String articleId = (String) context.get("articleId");
        GenericValue gv = delegator.findByPrimaryKey("CmsArticle",
                UtilMisc.toMap("articleId", articleId));
        if (UtilValidate.isNotEmpty(gv)) {
            gv.setNonPKFields(context);
            gv.store();
        }
        return ServiceUtil.returnSuccess();

    }

    public static Map<String, Object> deleteCmsArticle(DispatchContext dctx,
            Map<String, ? extends Object> context) throws GenericEntityException {
        Delegator delegator = dctx.getDelegator();
        String articleId = (String) context.get("articleId");
        GenericValue gv = delegator.findByPrimaryKey("CmsArticle",
                UtilMisc.toMap("articleId", articleId));
        if (UtilValidate.isNotEmpty(gv)) {
            gv.remove();
        }
        return ServiceUtil.returnSuccess();
    }

}
